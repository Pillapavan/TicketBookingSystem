package Ticket.booking.utils;


import org.mindrot.jbcrypt.BCrypt;

public class UserServiceUtil {
    public static String hashPassword(String plainPassword){
        return BCrypt.hashpw(plainPassword,BCrypt.gensalt());
    }

    public static Boolean checkPassword(String plainPassword,String hashedPassword){
        return BCrypt.checkpw(plainPassword,hashedPassword);
    }

    public static String generateTicketId() {
        return System.currentTimeMillis() + "-" + (int)(Math.random() * 10000);
    }
}
