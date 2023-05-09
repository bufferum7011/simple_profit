package simple_profit;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Properties;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

public class Bot extends TelegramLongPollingBot {
    @Override public void onUpdateReceived(Update update) {
        System.out.print("\n[UPDATE]");
        try {

        }
        catch(Exception e) { System.out.println("\nОШИБОЧКА->" + e); }
    }

    @Override public String getBotToken() { return properties.getProperty("bot.token"); }
    @Override public String getBotUsername() { return properties.getProperty("bot.username"); }

    private static Properties properties = new Properties();
    public static void main(String[] args) throws TelegramApiException, SQLException, ClassNotFoundException {
        System.out.println("==Start init");
        try { properties.load(new FileInputStream(".vscode\\application.properties")); }
        catch(IOException e) { System.out.println("Ошибка в файле свойств"); }
        new TelegramBotsApi(DefaultBotSession.class).registerBot(new Bot());
        System.out.println("==Finish init");
    }
}