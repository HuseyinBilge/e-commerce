package kodlama.io.ecommerce.business.abstracts;

public interface EMailSenderService {
    void sendEmail(String toEmail, String subject, String body);
}
