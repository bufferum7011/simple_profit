package simple_profit;
import static simple_profit.Bot.*;
import java.util.Scanner;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Properties;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.api.methods.polls.StopPoll;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.meta.generics.TelegramBot;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

public class Main {
    // get properties
    private static Properties properties = new Properties();
    public static String get_prop(String code) { return properties.getProperty(code); }

    public static void main(String[] args) throws TelegramApiException, SQLException, ClassNotFoundException {
        System.out.println("[main] - ON");

        // get properties
        try { properties.load(new FileInputStream(".vscode//application.properties")); }
        catch(IOException e) { System.out.println("[properties - ERROR]"); }

        // stimulation of waiting for commands in the console in my thread
        try(Scanner scanner = new Scanner(System.in)) {
            boolean key = true;
            while(key) {
                String s = scanner.nextLine();

                if(s.equals("mailing_on")) {
                    Mailing_flow mailing_flow = new Mailing_flow();
                    System.out.println("[Mailing_flow] - ON");
                }
                else if(s.equals("mailing_off")) {
                    
                    System.out.println("[Mailing_flow] - OFF");
                }
                else if(s.equals("bot_on")) {
                    // new Management_bot().start();
                    System.out.println("[Management_bot] - ON");
                }
                else if(s.equals("bot_off")) {
                    // 
                    System.out.println("[Management_bot] - OFF");
                }
                else if(s.equals("exit_prog")) { key = false; }
                else { System.out.println("ECHO - " + s); }
            }
        }
        
        System.out.println("[main] - OFF");
    }
}