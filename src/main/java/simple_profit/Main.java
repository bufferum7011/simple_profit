package simple_profit;
import static simple_profit.Management_bot.*;
import java.sql.SQLException;
import java.util.Scanner;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public class Main {
    // variables
    public static AnnotationConfigApplicationContext context;
    public static Management_bot management_bot;
    public static TelegramBotsApi bot_main;

    public static void main(String[] args) throws TelegramApiException, SQLException, ClassNotFoundException {
        System.out.println("[main] - ON");

        // pulling out bean from the pool
        context = new AnnotationConfigApplicationContext(Spring_config.class);
        management_bot = context.getBean("management_bot", Management_bot.class);
        bot_main = context.getBean("telegramBotsApi", TelegramBotsApi.class);
        
        // stimulation of waiting for commands in the console in my thread
        boolean key = true;
        while(key) {
            switch(new Scanner(System.in).nextLine()) {
                case "bot_on":      { bot_on(); break; }
                case "bot_off":     { bot_off(); break; }
                case "mailing_on":  { mailing_on(); break; }
                case "mailing_off": { mailing_off(); break; }
                case "prog_off":    { mailing_off(); bot_off(); context.close(); key = false; System.out.println("[main] - OFF"); break; }
                default: { System.out.println("[main] - unclear"); break; }
            }
        }
    }
}