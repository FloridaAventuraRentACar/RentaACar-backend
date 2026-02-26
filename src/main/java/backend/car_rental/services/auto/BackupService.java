package backend.car_rental.services.auto;

import java.io.ByteArrayOutputStream;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import backend.car_rental.dto.car.ResponseCarDto;
import backend.car_rental.dto.rental.CurrentRentalsResponseDto;
import backend.car_rental.services.car.interfaces.IFindCarService;
import backend.car_rental.services.email.interfaces.IEmailService;
import backend.car_rental.services.rental.interfaces.IFindRentalService;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class BackupService {

    private final IFindCarService findCarService;
    private final IFindRentalService findRentalService;
    private IEmailService emailService;
    
    // Se ejecuta todos los lunes a las 3:00 AM
    @Scheduled(cron = "0 0 3 * * MON")
    public void generateBackup() {
        try {
            // Crear el libro de Excel
            Workbook workbook = new XSSFWorkbook();
            Sheet sheet = workbook.createSheet("Disponibilidad " + LocalDate.now());

            // Estilo para celdas ocupadas (Rojo)
            CellStyle occupiedStyle = workbook.createCellStyle();
            occupiedStyle.setFillForegroundColor(IndexedColors.RED.getIndex());
            occupiedStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);

            // Ventana de 180 días
            LocalDate startDate = LocalDate.now();
            LocalDate endDate = startDate.plusDays(180);

            long daysTotal = ChronoUnit.DAYS.between(startDate, endDate);

            // Creo primer columna
            Row headerRow = sheet.createRow(0);
            headerRow.createCell(0).setCellValue("Auto / Fecha");
            sheet.setColumnWidth(0, 5000);
            // Bucle que crea una columna por cada día real
            for (int i = 0; i <= daysTotal; i++) {
                LocalDate columnDate = startDate.plusDays(i);

                // En el Excel mostramos "02/02", "03/02"... "01/03"
                String columnTitle = columnDate.getDayOfMonth() + "/" + columnDate.getMonthValue();

                headerRow.createCell(i + 1).setCellValue(columnTitle);
                sheet.setColumnWidth(i + 1, 1500); // Un poco más ancho
            }

            // 4. Obtener datos
            List<ResponseCarDto> cars = findCarService.findAll().getBody();

            List<CurrentRentalsResponseDto> rentals = findRentalService.findCurrentRentals().getBody();

            //Mapeo el id de un auto a todos sus alquileres
            Map<Long, List<CurrentRentalsResponseDto>> rentalsByCar = rentals.stream()
                    .collect(Collectors.groupingBy(r -> r.getCarId()));

            // 5. Lógica de "Dibujado" del Calendario
            int rowNum = 1;
            for (ResponseCarDto car : cars) {
                Row row = sheet.createRow(rowNum++);
                row.createCell(0).setCellValue(car.getName());

                for (int day = 1; day <= daysTotal; day++) {
                    LocalDate currentDate = startDate.plusDays(day - 1);
                    Cell cell = row.createCell(day);

                    // Verificamos si este auto está alquilado en 'currentDate'
                    CurrentRentalsResponseDto foundRental = findRentalForCarOnDate(rentalsByCar, car, currentDate);

                    if (foundRental != null) {
                        cell.setCellStyle(occupiedStyle);
                         
                        if (day == 1 || foundRental.getStart().isEqual(currentDate)) {
                            cell.setCellValue(foundRental.getClientName());
                        }
                    }
                }
            }

            // 6. Convertir Excel a Bytes (para no guardar en disco)
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            workbook.write(bos);
            byte[] excelBytes = bos.toByteArray();
            workbook.close();

            // 7. Enviar Email
            emailService.sendEmail(
                "Backup_Disponibilidad_" + LocalDate.now() + ".xlsx", 
                "floridaaventuraok@gmail.com", 
                "🚨 Backup Semanal de Disponibilidad - " + LocalDate.now(), 
                "Adjunto se encuentra un excel con la disponibilidad de los autos los proximos 6 meses.", 
                excelBytes);

        } catch (Exception e) {
            throw new RuntimeException("Error al generar el backup: " + e.getMessage());
        }
    }

    private CurrentRentalsResponseDto findRentalForCarOnDate(Map<Long, List<CurrentRentalsResponseDto>> rentalsByCar, ResponseCarDto car, LocalDate date) {
        
        List<CurrentRentalsResponseDto> rentalsOfCar = rentalsByCar.get(car.getId());
    
        if (rentalsOfCar == null) return null;

        return rentalsOfCar.stream()
                .filter(r -> !date.isBefore(r.getStart()) && !date.isAfter(r.getEnd()))
                .findFirst()
                .orElse(null);
    }


}
