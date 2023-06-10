package simple_profit;
import static simple_profit.Exec_sql.*;
import static simple_profit.Main.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.telegram.telegrambots.meta.api.objects.Update;

public class User {
    private static int users_id;
    private static String chat_id;
    private static String tag;
    private static String name;
    private static String adress;
    private static String num_phone;
    private static String status;
    private static int msg_id;
    private static double latitude;
    private static double longitude;
    private static String receipt;

    // getters
    public static int getUsers_id() { return users_id; }
    public static String getChat_id() { return chat_id; }
    public static String getTag() { return tag; }
    public static String getName() { return name; }
    public static String getAdress() { return adress; }
    public static String getNum_phone() { return num_phone; }
    public static String getStatus() { return status; }
    public static int getMsg_id() { return msg_id; }
    public static double getLatitude() { return latitude; }
    public static double getLongitude() { return longitude; }
    public static String getReceipt() { return receipt; }

    // setters to bd
    public static void set_chat_id(String chat_id) {
        sql_update("INSERT users(chat_id) VALUES(" + chat_id + ");");
    }
    public static void set_tag(String tag, String chat_id) {
        sql_update("UPDATE users SET tag = '" + tag + "' WHERE chat_id = " + chat_id + ";");
    }
    public static void set_name(String name, String chat_id) {
        sql_update("UPDATE users SET name = '" + name + "' WHERE chat_id = " + chat_id + ";");
    }
    public static void set_adress(String adress, String chat_id) {
        sql_update("UPDATE users SET adress = '" + adress + "' WHERE chat_id = " + chat_id + ";");
    }
    public static void set_num_phone(String num_phone, String chat_id) {
        sql_update("UPDATE users SET num_phone = '" + num_phone + "' WHERE chat_id = " + chat_id + ";");
    }
    public static void set_status(String status, String chat_id) {
        sql_update("UPDATE users SET status = '" + status + "' WHERE chat_id = " + chat_id + ";");
    }
    public static void set_msg_id(int msg_id, String chat_id) {
        sql_update("UPDATE users SET msg_id = '" + msg_id + "' WHERE chat_id = " + chat_id + ";");
    }
    public static void set_geolocation(double latitude, double longitude, String chat_id) {
        sql_update("UPDATE users SET latitude = '" + latitude + "' WHERE chat_id = " + chat_id + ";");
        sql_update("UPDATE users SET longitude = '" + longitude + "' WHERE chat_id = " + chat_id + ";");
    }
    public static void set_receipt(String receipt, String chat_id) {
        sql_update("UPDATE users SET receipt = '" + receipt + "' WHERE chat_id = " + chat_id + ";");
    }
    
    // constructor
    public User(Update update) {
        System.out.print("[Users]");
        try {
            String chat_id_given = "";
            if(update.hasMessage()) { chat_id_given = update.getMessage().getChatId().toString(); }
            else { chat_id_given = update.getCallbackQuery().getMessage().getChatId().toString(); }
            ResultSet resultSet = sql_callback("SELECT * FROM users WHERE chat_id = " + chat_id_given + ";");
            while(resultSet.next()) {
                users_id = resultSet.getInt("users_id");
                chat_id = resultSet.getString("chat_id");
                tag = resultSet.getString("tag");
                name = resultSet.getString("name");
                adress = resultSet.getString("adress");
                num_phone = resultSet.getString("num_phone");
                status = resultSet.getString("status");
                msg_id = resultSet.getInt("msg_id");
                latitude = resultSet.getDouble("latitude");
                longitude = resultSet.getDouble("longitude");
                if(chat_id.equals(chat_id_given)) { break; }
            }
        } catch (SQLException e) { System.out.println("[ERROR USERS]"); }
    }
}