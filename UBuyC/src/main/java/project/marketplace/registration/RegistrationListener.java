package project.marketplace.registration;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.MessageSource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

import project.marketplace.models.User;
import project.marketplace.daos.AccountDao;

@Component
public class RegistrationListener implements ApplicationListener<OnRegistrationCompleteEvent> {
 
    @Autowired
    private AccountDao dao;
 
    @Autowired
    private MessageSource messages;
 
    @Autowired
    private JavaMailSender mailSender;

    @Override
    public void onApplicationEvent(OnRegistrationCompleteEvent event) {
        this.confirmRegistration(event);
    }

    private void confirmRegistration(OnRegistrationCompleteEvent event) {
        User user = event.getUser();
        int otp = dao.createVerificationToken(user);
        
        String recipientAddress = user.getEmail();
        String subject = "Your UBuyC One Time Password";
        String message = messages.getMessage("message.registrationSuccessful", null, event.getLocale());
        
        SimpleMailMessage email = new SimpleMailMessage();
        email.setTo(recipientAddress);
        email.setSubject(subject);
        email.setText(message + "\r\n" + otp);
        mailSender.send(email);
    }
}
