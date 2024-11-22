package com.example.api.auth.verification;

import lombok.RequiredArgsConstructor;
import net.nurigo.sdk.message.model.Message;
import net.nurigo.sdk.message.request.SingleMessageSendingRequest;
import net.nurigo.sdk.message.service.DefaultMessageService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class VerificationService {

    private final DefaultMessageService messageService;

    @Value("${coolsms.api.number}")
    private String fromNumber;

    private final Map<String, String> verificationCodes = new HashMap<>();

    public void sendVerificationCode(String phoneNumber) {
        String verificationCode = generateVerificationCode();

        verificationCodes.put(phoneNumber, verificationCode);

        Message message = new Message();
        message.setFrom(fromNumber);
        message.setTo(phoneNumber);
        message.setText("인증번호는 " + verificationCode + "입니다.");

        messageService.sendOne(new SingleMessageSendingRequest(message));
    }


    public boolean verifyCode(String phoneNumber, String enteredCode) {
        String storedCode = verificationCodes.get(phoneNumber);
        return enteredCode.equals(storedCode);
    }


    private String generateVerificationCode() {
        Random random = new Random();
        return String.valueOf(100000 + random.nextInt(900000));
    }
}
