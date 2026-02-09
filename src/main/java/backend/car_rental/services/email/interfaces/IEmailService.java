package backend.car_rental.services.email.interfaces;

public interface IEmailService {

    void sendEmail(String filename, String to, String subject, String body, byte[] attachment) throws Exception;
}
