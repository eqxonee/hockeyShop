package com.example.hockeyshop.bot;

import com.example.hockeyshop.utils.RedisUtil;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

@AllArgsConstructor
@Service
public class BotInitializer extends TelegramLongPollingBot {

    private RedisUtil redisUtil;


    @Override
    public String getBotUsername() {
        return "QuestsAS_bot";
    }

    @Override
    public String getBotToken() {
        return "5681958302:AAHH_lXr3_cnSnVcnZypm4tsiNbhRZXZ4w4";
    }

    @Override
    public void onUpdateReceived(Update update) {

    }


    public void sendCodeToChat(String phoneNumber,String smsCode) throws TelegramApiException {
        String outputText = String.format(
                redisUtil.get(phoneNumber),
                redisUtil.get(smsCode)
        );

        SendMessage message = new SendMessage();
        message.setChatId("732314018");
        message.setText(outputText);

        this.execute(message);


    }
}
