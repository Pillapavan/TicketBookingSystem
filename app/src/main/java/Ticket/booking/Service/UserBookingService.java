package Ticket.booking.Service;

import Ticket.booking.Entities.Ticket;
import Ticket.booking.Entities.Train;
import Ticket.booking.Entities.User;
import Ticket.booking.utils.UserServiceUtil;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import java.io.File;
import java.io.IOException;
import java.util.*;

public class UserBookingService {
    private User user;

    private final static String USER_PATH = "app/src/main/java/Ticket/booking/LocalDb/User.json";

    private final TrainService trainService;

    List<User> userList;

//    ObjectMapper is used for serialization and deserialization (converting java objects to json and vice versa)
//    This ObjectMapper is imported by jackson
    private final ObjectMapper ObjectMapper;

//    Default constructor
    public UserBookingService() throws IOException {
        ObjectMapper = new ObjectMapper();
        ObjectMapper.enable(SerializationFeature.INDENT_OUTPUT);
        userList = loadFile();
        trainService = new TrainService();
    }

//    Creating LoadFile method for loading user data to the userList
    public List<User> loadFile() throws IOException{
        return ObjectMapper.readValue(new File(USER_PATH), new TypeReference<List<User>>() {
        });
    }

//    Signup Method
    public Boolean signUp(User user1){
        try{
            Optional<User> existUser = userList.stream()
                    .filter(user -> user.getUserName().equals(user1.getUserName()))
                    .findFirst();
            if (existUser.isPresent()){
                // If a user with the same username exists,this will print an error message
                System.out.println("Username already taken!");
                return Boolean.FALSE;
            }
            userList.add(user1);
            saveUserListToFile();
        }
        catch (IOException ex){
            System.out.println("saving user list to file failed " + ex.getMessage());
            return Boolean.FALSE;
        }
        return Boolean.TRUE;
    }

//    Login Method
    public Optional<User> loginUser(String loginUser,String loginPassword) {
        return userList.stream().filter(user1 -> {
                 return user1.getUserName().equalsIgnoreCase(loginUser) &&
                         UserServiceUtil.checkPassword(loginPassword,user1.getHashedPassword());
        })
                .findFirst();
    }


    public void fetchBookings(){
        user.printTicket();
    }


//    Cancelling Ticket Method
    public Boolean cancelBooking(String ticketId) throws IOException {
        if(ticketId==null || ticketId.isEmpty()){
            System.out.println("Ticket ID cannot be null or empty.");
            return Boolean.FALSE;
        }
        Optional<Ticket> ticketFound = user.getTicketsBooked().stream()
                .filter(ticket -> ticket.getTicketId().equals(ticketId)).findFirst();
        if (ticketFound.isPresent()){
            user.getTicketsBooked().remove(ticketFound);
            trainService.updateSeats(ticketFound.get().getSeatNo(),ticketFound.get().getTrain().getTrainId());
            saveUserListToFile();
            System.out.println("Ticket with ID " + ticketId + " has been canceled.");
            return Boolean.TRUE;
        }else{
            System.out.println("No ticket found with ID " + ticketId);
            return Boolean.FALSE;
        }
    }

//    Booking a Ticket
    public boolean getTicket(Train train, int row, int seat) throws IOException {
        List<List<Integer>> seats = train.getSeats();
        try{
            if(row>=0 && row<seats.size() && seat>=0 &&  seat<seats.get(row).size()){
                if(seats.get(row).get(seat)==0){
                    seats.get(row).set(seat,1);
                    train.setSeats(seats);
                    trainService.updateTrain(train);

                    Ticket ticket = new Ticket();

                    ticket.setSource(train.getStations().getFirst());
                    ticket.setDestination(train.getStations().getLast());
                    ticket.setUserId(user.getUserId());
                    ticket.setSeatNo(List.of(row,seat));
                    ticket.setTrain(train);
                    ticket.setTicketId(UserServiceUtil.generateTicketId());
                    ticket.setDateOfTravel("2025-11-02");

                    user.getTicketsBooked().add(ticket);
                    System.out.println("Seat booked successfully  !  ");
                    ticket.trainDetails();
                    saveUserListToFile();
                    return true;
                }else{
                    return false;
                }
            }else{
                return false;
            }
        }catch (IOException ex){
            System.out.println(ex.getMessage());
            return false;
        }
    }

//    Searching the train
    public List<Train> getTrains(String source,String destination){
        try{
            return trainService.searchTrains(source,destination);
        }catch (Exception ex){
            System.out.println("There is something wrong!");
            // return empty list if there is an exception
            System.out.println(Arrays.toString(ex.getStackTrace()));
            return Collections.emptyList();
        }
    }

    public void saveUserListToFile() throws IOException{
        File userFile = new File(USER_PATH);
        ObjectMapper.writeValue(userFile,userList);
    }

    public List<List<Integer>> fetchSeats(Train train){
        return train.getSeats();
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
