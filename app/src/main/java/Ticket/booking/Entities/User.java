package Ticket.booking.Entities;

import java.util.List;

public class User {
    private String userName;
    private String hashedPassword;
    private List<Ticket> ticketsBooked;
    private String UserId;

    public User(String name, String hasPassword, List<Ticket> ticketBooked, String userId) {
        this.userName = name;
        this.hashedPassword = hasPassword;
        this.ticketsBooked = ticketBooked;
        UserId = userId;
    }

    public User() {}

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getHashedPassword() {
        return hashedPassword;
    }

    public void setHashedPassword(String hashedPassword) {
        this.hashedPassword = hashedPassword;
    }

    public List<Ticket> getTicketsBooked() {
        return ticketsBooked;
    }

    public void setTicketsBooked(List<Ticket> ticketsBooked) {
        this.ticketsBooked = ticketsBooked;
    }

    public String getUserId() {
        return UserId;
    }

    public void setUserId(String userId) {
        UserId = userId;
    }

    public void printTicket(){
        for (Ticket ticket : ticketsBooked) {
            System.out.println(ticket.trainDetails());
        }
    }

}