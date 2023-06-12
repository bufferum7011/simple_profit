package simple_profit;
import static simple_profit.Data.*;
import static simple_profit.Handler.*;
import static simple_profit.Main.*;
import java.io.IOException;
import java.net.URL;
import javax.net.ssl.HttpsURLConnection;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.DeleteMessage;
import org.telegram.telegrambots.meta.api.objects.Update;


public class Bot extends TelegramLongPollingBot {

    @Override public void onUpdateReceived(Update update) {
        try {
            System.out.print("\n[UPDATE]");
            new Data(update);
 
            // обработка кнопок
            if(update.hasCallbackQuery()) {
                System.out.print("[has_callback=" + Data.get_data() + "]");
                if(Data.get_data().equals("ok_1")) {
                    execute(new DeleteMessage(get_chat_id(), Data.get_msg_id() - 1));
                    execute(send_my_contact());
                }
                if(Data.get_data().equals("catch_contact")) {
                    // execute();
                }
                
                
            }

            // обработка сообшений
            if(update.getMessage().hasText()) {
                System.out.print("[has_text]");

                if(get_cmd().equals("/start")) { execute(get_menu_login()); }
                else if(get_cmd().equals("/stop")) { System.out.println("Stop message"); }
                else { System.out.println("Anythink message"); }



            }

            // взятие номера телефона
            if(Data.get_o().hasContact()) {
                System.out.print("[expected_num_phone]");
                // new User(update);
                execute(new DeleteMessage(Data.get_chat_id(), Data.get_msg_id()));
                execute(new DeleteMessage(Data.get_chat_id(), Data.get_msg_id() - 1));
                // set_num_phone(update.getMessage().getContact().getPhoneNumber(), getChat_id());
                // set_status("[authorized_moment_ago]", getChat_id());
                // set_status("[authorized]", getChat_id());
                execute(new SendMessage(Data.get_chat_id(), "Ждите звонка"));
            }

        }
        catch(Exception e) { System.out.println("\n[ERROR UPDATE]"); }
    }
    @Override public String getBotUsername() { return main.username; }
    public Bot() { super(main.token); }

    public void bot_special_mailings(String text, String method, String[] chat_id) {
        for(int i = 0; i < chat_id.length; i++) {
            try {
                URL url = new URL("https://api.telegram.org/bot" + main.token + "/" + method + "?chat_id=" + chat_id[i] + "&text=" + text);
                HttpsURLConnection huc = (HttpsURLConnection) url.openConnection();
                System.out.println("[Bot] - Выполнено. ResponseCode: " + huc.getResponseCode() + ".");
            }
            catch(IOException e) { e.printStackTrace(); }
        }
    }
}