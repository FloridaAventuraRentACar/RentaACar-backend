package backend.car_rental.controlers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import backend.car_rental.services.auto.BackupService;
import backend.car_rental.services.notifications.WhatsAppService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;


@RestController
@AllArgsConstructor
@RequestMapping("/test")
public class TestController {
    
    private final BackupService backupService;
    private final WhatsAppService whatsappService;
    
    @GetMapping("/excel-backup")
    public void generateExcelBackup() {
    
        backupService.generateBackup();
    }
    
    @GetMapping("/whatsapp")
    public void sendWhatsAppNotification() {
        whatsappService.sendAdminNotification("Felipe Del Zoppo", "Volkswagen Tiguan", "2 de febrero de 2026", "10 de febrero de 2026", 3);
    }

}
