package simple_profit;
import static simple_profit.Main.*;
import static simple_profit.Exec_sql.*;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

public class Bot extends TelegramLongPollingBot {
    @Override public void onUpdateReceived(Update update) {
        try {
            System.out.print("\n[UPDATE]");

            


            execute(new SendMessage("314167571851", "3"));



        }
        catch(Exception e) { System.out.println("\nОШИБОЧКА->" + e); }
    }

    @Override public String getBotToken() { return get_prop("bot.token"); }
    @Override public String getBotUsername() { return get_prop("bot.username"); }
}