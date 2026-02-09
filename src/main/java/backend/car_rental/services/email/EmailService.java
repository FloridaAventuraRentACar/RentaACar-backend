package backend.car_rental.services.email;

import org.springframework.core.io.ByteArrayResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import backend.car_rental.services.email.interfaces.IEmailService;
import jakarta.mail.internet.MimeMessage;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class EmailService implements IEmailService {
    
    private final JavaMailSender mailSender;
    
    @Override
    public void sendEmail(String filename, String to, String subject, String body, byte[] attachment) throws Exception {
        try {     
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
    
            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(body);
            
            if (attachment != null) {
                helper.addAttachment(filename, new ByteArrayResource(attachment));
            }
    
            mailSender.send(message);
        } catch (Exception e) {
            System.err.println("Error enviando email: " + e.getMessage());
        }
    }
}
