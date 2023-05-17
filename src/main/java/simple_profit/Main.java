package simple_profit;
import static simple_profit.Bot.*;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Properties;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

public class Main {
    // get properties
    private static Properties properties = new Properties();
    public static String get_prop(String code) { return properties.getProperty(code); }

    public static void main(String[] args) throws TelegramApiException, SQLException, ClassNotFoundException {
        System.out.println("==Start init");
        try { properties.load(new FileInputStream(".vscode//application.properties")); }
        catch(IOException e) { System.out.println("Ошибка в файле свойств"); }

        Wait_flow wait_flow = new Wait_flow();
        wait_flow.start();


    }    
}
class Wait_flow extends Thread {
    @Override public void start() {
        int attempt = 0;

        // Подразумеваю перезагрузку бота при каждой ошибки в регистрации
        // Но это вовсе не так
        // Он просто будет постоянно перерегистрировать бота
        // while(true) {
            attempt++;

            try {
                new TelegramBotsApi(DefaultBotSession.class).registerBot(new Bot());
                System.out.println("==Bot init=" + attempt);
            }
            catch(TelegramApiException e) { System.out.println("[ERROR Wait_flow]"); }


        // }
    }
}