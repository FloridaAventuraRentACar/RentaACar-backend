package backend.car_rental.services.notifications;

import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import jakarta.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;

@Service
@RequiredArgsConstructor
public class WhatsAppService {

    @Value("${twilio.account.sid}")
    private String accountSid;

    @Value("${twilio.auth.token}")
    private String authToken;

    @Value("${twilio.whatsapp.number}")
    private String fromNumber;

    @Value("${admin.whatsapp.number}")
    private String adminPhone;

    private final ObjectMapper objectMapper = new ObjectMapper();
        
    @PostConstruct
    public void init() {
        // Inicializar el SDK de Twilio al arrancar el servicio
        Twilio.init(accountSid, authToken);
    }

    @Async
    public void sendAdminNotification(String clientName, String carName, String startDate, String endDate,
            int rentalId) {
        try {
            Map<String, String> variables = new HashMap<>();

            variables.put("1", String.valueOf(rentalId)); 
            variables.put("2", clientName); 
            variables.put("3", carName); 
            variables.put("4", startDate); 
            variables.put("5", endDate); 

            String contentVariables = objectMapper.writeValueAsString(variables);

            Message message = Message.creator(
                    new PhoneNumber(adminPhone),
                    new PhoneNumber(fromNumber),
                    "")
                    .setContentSid("HXe2b8c3f5a7556b1843553a2c4a449824") // ID de la plantilla de mensaje
                    .setContentVariables(contentVariables)

                    .create();

        } catch (Exception e) {
            System.err.println("Error enviando WhatsApp: " + e.getMessage());
        }
    }
}
