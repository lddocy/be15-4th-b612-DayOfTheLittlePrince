package littleprince.utils;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import littleprince.common.exception.BusinessException;
import littleprince.common.exception.GlobalErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class EmailUtils {
    private final JavaMailSender javaMailSender;

    public void sendEmail(String content, String title, String targetEmail) {
        MimeMessage message = javaMailSender.createMimeMessage();
        try {
            MimeMessageHelper messageHelper = new MimeMessageHelper(
                    message,
                    true,
                    "UTF-8"
            );

            messageHelper.setTo(targetEmail);
            messageHelper.setText(content, true);
//            messageHelper.setFrom("b612");
            messageHelper.setSubject(title);

            javaMailSender.send(message);
        } catch (MessagingException e) {
            throw new BusinessException(GlobalErrorCode.SEND_EMAIL_FAILED);
        }
    }
}
