package simple_profit;
import static simple_profit.Exec_sql.*;
import static simple_profit.User.*;
import static simple_profit.Data.*;
import java.util.ArrayList;
import java.util.List;
import org.telegram.telegrambots.meta.api.methods.ParseMode;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;

public class Handler {

    // Заполнение данных / fill data / autorization
    public static SendMessage get_menu_login() {
        System.out.print("[get_menu_login]");

        InlineKeyboardButton in1 = new InlineKeyboardButton();
        in1.setText("Мне понятно👌");
        in1.setCallbackData("ok_1");

        List<InlineKeyboardButton> row1 = new ArrayList<>();
        row1.add(in1);
        List<List<InlineKeyboardButton>> rowList = new ArrayList<>();
        rowList.add(row1);
        InlineKeyboardMarkup im = new InlineKeyboardMarkup(rowList);
        
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(Data.get_chat_id());
        sendMessage.setParseMode(ParseMode.MARKDOWN);
        sendMessage.setText(
            "🥳Привет, " + Data.get_o().getFrom().getFirstName() + "\n" +
            "Я бот для рассылок заказов на работу" + "\n" +
            "Я буду оценивать твой рейтинг чтобы твои доходы росли" + "\n" +
            "И оплачивать сразу после работы" + "\n" +
            "Правила работы:" + "\n" +
            "1. Необходимо добросовестно выполнять работу на заказе" + "\n" +
            "2. За пропуск грозит блокировка бота"
        );
        sendMessage.setReplyMarkup(im);
        return sendMessage;
    }

    // Отправка номера телефона
    public static SendMessage send_my_contact() {
        KeyboardButton kButton = new KeyboardButton();
        kButton.setText("📞Отправить контакты");
        kButton.setRequestContact(true);
        KeyboardRow row1 = new KeyboardRow();
        row1.add(kButton);
        List<KeyboardRow> keyboard = new ArrayList<>();
        keyboard.add(row1);
        ReplyKeyboardMarkup replyKeyBoard = new ReplyKeyboardMarkup();
        replyKeyBoard.setOneTimeKeyboard(false);
        replyKeyBoard.setKeyboard(keyboard);
        replyKeyBoard.setResizeKeyboard(true);
        replyKeyBoard.setOneTimeKeyboard(true);

        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(Data.get_chat_id());
        sendMessage.enableMarkdown(true);
        sendMessage.setReplyMarkup(replyKeyBoard);
        sendMessage.setText("Чтобы Вас подтвердил оператор и вы начали получать заявки оставте свои контакты");
        return sendMessage;
    }




}