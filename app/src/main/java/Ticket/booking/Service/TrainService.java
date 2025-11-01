package Ticket.booking.Service;

import Ticket.booking.Entities.Train;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.SerializationFeature;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.OptionalInt;
import java.util.stream.IntStream;

public class TrainService {

    private final static String TRAIN_URL = "app/src/main/java/Ticket/booking/LocalDb/Train.json";

    private Train train;

    private final List<Train> trainList;

    private final ObjectMapper objectMapper;

    public TrainService() throws IOException {
        objectMapper = new ObjectMapper();
        objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
        trainList = loadTrains();
    }

    public List<Train> loadTrains() throws IOException {
        return objectMapper.readValue(new File(TRAIN_URL), new TypeReference<List<Train>>() {});
    }

    public void saveTrainListToFile() throws IOException{
        objectMapper.writeValue(new File(TRAIN_URL),trainList);
    }


    public List<Train> searchTrains(String source,String destination){
        try{
            return trainList.stream().filter(train1 -> validTrain(train1,source,destination)).toList();
        }catch (Exception ex){
            System.out.println("Error in searchTrains: " + ex.getMessage());
            return null;
        }
    }


    public boolean validTrain(Train train,String source,String destination){
        List<String> stations = train.getStations();

        int sourceIndex = stations.indexOf(source);
        int destinationIndex = stations.indexOf(destination);
        return  sourceIndex!=-1 && destinationIndex!=-1 && sourceIndex<destinationIndex;
    }


    public void updateTrain(Train train) throws IOException{
        OptionalInt index = IntStream.range(0, trainList.size())
                .filter(i -> trainList.get(i).getTrainId().equalsIgnoreCase(train.getTrainId()))
                .findFirst();
        if (index.isPresent()){
            trainList.set(index.getAsInt(), train);
            saveTrainListToFile();
        }else{
            throw new IOException("Train is not present");
        }
    }

    public void updateSeats(List<Integer> seat,String trainId) throws IOException {
        Optional<Train> train1 = trainList.stream().filter(train2 -> train2.getTrainId().equals(trainId)).findFirst();
        if (train1.isPresent()){
            List<List<Integer>> seats = train1.get().getSeats();
            seats.get(seat.get(0)).set(seat.get(1),0);
            saveTrainListToFile();
        }
    }
}
