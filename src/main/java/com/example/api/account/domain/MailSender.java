package com.example.api.account.domain;

import com.example.api.account.controller.dto.request.EmailRequestDto;
import com.example.api.exception.BusinessException;
import com.example.api.exception.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MailSender {
    private final JavaMailSender mailSender;

    @Value("${spring.mail.username}")
    private String fromEmail;

    public Code sendEmail(final EmailRequestDto emailRequest){
        String code = CodeGenerator.generateCode();

        try {
            SimpleMailMessage message = createEmail(emailRequest.email(), code);
            mailSender.send(message);

            return new Code(emailRequest.email(), code);
        } catch (Exception e) {
            throw new BusinessException(ErrorCode.FAIL_SEND_EMAIL);
        }
    }

    private SimpleMailMessage createEmail(final String to, final String code) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject("단팥의 인증 이메일입니다.");
        message.setText(String.format("단팥 인증코드 %s 입니다.", code));
        message.setFrom(fromEmail);
        return message;
    }
}
