package simple_profit;
import java.util.Scanner;
import static simple_profit.Bot.*;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Properties;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

public class Main {
    public static void main(String[] args) throws TelegramApiException, SQLException, ClassNotFoundException {
        System.out.println("[main] - ON");

        // get properties
        try { properties.load(new FileInputStream(".vscode//application.properties")); }
        catch(IOException e) { System.out.println("[properties - ERROR]"); }

        // stimulation of waiting for commands in the console in my thread
        Scanner scanner = new Scanner(System.in);
        boolean key = true;
        while(key) {
            String s = scanner.nextLine();

            if(s.equals("mailing_on")) { mailing_on(); }
            else if(s.equals("mailing_off")) { mailing_off(); }
            else if(s.equals("bot_on")) { bot_on(); }
            else if(s.equals("bot_off")) { bot_off(); }
            else if(s.equals("prog_off")) {
                mailing_off();
                bot_off();
                key = false;
            }
            else { System.out.println("ECHO - " + s); }
        }
        System.out.println("[main] - OFF");
    }

    // get properties
    private static Properties properties = new Properties();
    public static String get_prop(String code) { return properties.getProperty(code); }

    // management_bot
    public static void bot_on() {
        management_bot.start();
        System.out.println("[Management_bot] - ON");
    }
    public static void bot_off() {
        management_bot.interrupt();
        System.out.println("[Management_bot] - OFF");
    }
    public static void bot_restart() {
        long l1 = System.currentTimeMillis();
        bot_off();
        bot_on();
        System.out.println("[restart] - " + (l1 - System.currentTimeMillis()));
    }
    public static void mailing_on() {
        key_single = true;
        System.out.println("[satellite] - ON");
    }
    public static void mailing_off() {
        key_single = false;
        System.out.println("[satellite] - OFF");
    }

    public static final boolean key_global = true;
    public static boolean key_single = false;
    public static Management_bot management_bot = new Management_bot(); // одноразовый

}
class Management_bot extends Thread {
    @Override public void start() {
        try {
            TelegramBotsApi bot = new TelegramBotsApi(DefaultBotSession.class);
            bot.registerBot(new Bot());
        }
        catch(TelegramApiException e) { System.out.println("[Bot - ERROR]"); }
    }

    @Override public void interrupt() {
        ThreadGroup rootGroup = Thread.currentThread().getThreadGroup();
        ThreadGroup parentGroup;
        while((parentGroup = rootGroup.getParent()) != null) { rootGroup = parentGroup; }
        Thread[] threads = new Thread[rootGroup.activeCount()];
        while(rootGroup.enumerate(threads, true) == threads.length) {
            threads = new Thread[threads.length * 2];
        }

        for(int i = 0; i < threads.length; i++) {
            System.out.print("[" + i + "/" + threads.length + "]");
            if(!threads[i].getName().equals(null)) {
                Thread thread = threads[i];

                System.out.println(thread.getName());
                if(thread.getName().equals(Main.get_prop("bot.username") + " Telegram Executor")) {
                    thread.interrupt();
                    thread.getStackTrace().toString();
                    System.out.println("[STATE] = " + thread.getState().toString() + "[KILLING] = " + thread.getName());
                    // break;
                }
                if(thread.getName().equals(Main.get_prop("bot.username") + " Telegram Connection")) {
                    thread.interrupt();
                    thread.getStackTrace().toString();
                    System.out.println("[STATE] = " + thread.getState().toString() + "[KILLING] = " + thread.getName());
                }
            }
        }
    }
}