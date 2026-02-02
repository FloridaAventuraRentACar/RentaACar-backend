package backend.car_rental.controlers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import backend.car_rental.services.auto.BackupService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;


@RestController
@AllArgsConstructor
@RequestMapping("/test")
public class TestController {
    
    private final BackupService backupService;
    @GetMapping("/excel-backup")
    public void generateExcelBackup() {
    
        backupService.generateBackup();
    }
    
}
