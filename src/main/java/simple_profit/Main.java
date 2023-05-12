package simple_profit;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Properties;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

public class Main {
    public static void main(String[] args) throws TelegramApiException, SQLException, ClassNotFoundException {
        System.out.println("==Start init");
        try { properties.load(new FileInputStream(".vscode\\application.properties")); }
        catch(IOException e) { System.out.println("Ошибка в файле свойств"); }

        new TelegramBotsApi(DefaultBotSession.class).registerBot(new Bot());
        System.out.println("==Finish init");
    }

    // get properties
    private static Properties properties = new Properties();
    public static String get_prop(String code) { return properties.getProperty(code); }
    
}