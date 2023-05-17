package simple_profit;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;

public class Data {

    private static Message o = null;
    private static CallbackQuery callback = null;
    private static String cmd = "none";
    private static String chat_id = "none";
    private static int msg_id = -100;
    private static String data = "none";
    private static char[] c_data = null;

    // getters
    public static Message get_o() { return o; }
    public static CallbackQuery get_callback() { return callback; }
    public static String get_cmd() { return cmd; }
    public static String get_chat_id() { return chat_id; }
    public static int get_msg_id() { return msg_id; }
    public static String get_data() { return data; }
    public static char get_c_data(int i) { return c_data[i]; }
    
    // constuctor
    public Data(Update update) {
        try {
            // setters
            if(update.hasMessage()) { o = update.getMessage(); }
            else {
                o = update.getCallbackQuery().getMessage();
                callback = update.getCallbackQuery();
                data = callback.getData();
                c_data = get_data().toCharArray();
            }

            cmd = o.getText();
            chat_id = o.getChatId().toString();
            msg_id = o.getMessageId();
        }
        catch(Exception e) { System.out.println("\n[ERROR DATA]"); }
    }
}