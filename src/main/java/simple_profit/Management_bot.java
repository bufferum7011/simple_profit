package simple_profit;
import static simple_profit.Main.*;
import static simple_profit.Handler.*;
import static simple_profit.Exec_sql.*;
import static simple_profit.Data.*;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

public class Management_bot extends Thread {
    
    @Override public void run() {
        try {
            TelegramBotsApi bot = new TelegramBotsApi(DefaultBotSession.class);
            bot.registerBot(new Bot());
        }
        catch(TelegramApiException e) { System.out.println("[Bot - ERROR]"); }
    }

    @Override public void interrupt() {
        ThreadGroup rootGroup = Thread.currentThread().getThreadGroup();
        ThreadGroup parentGroup;
        while((parentGroup = rootGroup.getParent()) != null) {
            rootGroup = parentGroup;
        }
        Thread[] threads = new Thread[rootGroup.activeCount()];
        while(rootGroup.enumerate(threads, true) == threads.length) {
            threads = new Thread[threads.length * 2];
        }

        for(Thread thread : threads) {
            if(thread.getName().equals(get_prop("bot.username") + " Telegram Executor")) {
                thread.interrupt();
                thread.getStackTrace().toString();
                System.out.println("[STATE] = " + thread.getState().toString() + "[KILLING] = " + thread.getName());
                break;
            }
            if(thread.getName().equals(get_prop("bot.username") + " Telegram Connection")) {
                thread.interrupt();
                thread.getStackTrace().toString();
                System.out.println("[STATE] = " + thread.getState().toString() + "[KILLING] = " + thread.getName());
                
            }
        }
    }

}
class Mailing_flow extends Thread {
    
    private boolean is_active;
    void disable() { is_active = false; }

    int attempt = 0;

    @Override public void run() {
        System.out.println("[Mailing_flow] - Я запущен");
        is_active = true;
        attempt++;

        while(is_active) {
            System.out.println("[Mailing_flow] - Я живой");
            try {
                Mailing_flow.sleep(3000);
                // Тут он что-то делает
            }
            catch(InterruptedException e) { System.out.println("[Mailing_flow] - ERROR"); }
        }
    }
}