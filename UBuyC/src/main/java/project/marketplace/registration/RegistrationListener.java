package project.marketplace.registration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.MessageSource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

import project.marketplace.models.User;
import project.marketplace.daos.AccountDao;

/**
 * Creates a registration listener class that listens to /signup for an OnRegistrationCompleteEvent.
 * Sends an email containing the OTP linked to the registering user.
 */
@Component
public class RegistrationListener implements ApplicationListener<OnRegistrationCompleteEvent> {
 
    @Autowired
    private AccountDao dao;
 
    @Autowired
    private MessageSource messages;
 
    @Autowired
    private JavaMailSender mailSender;

    /**
     * Calls confirmRegistration once is hears the registration event occur
     */
    @Override
    public void onApplicationEvent(OnRegistrationCompleteEvent event) {
        this.confirmRegistration(event);
    }
    
    /**
     * Creates a new verification token linked to the user. Sends an email with the generated OTP
     * to the user's email.
     * 
     * @param event the registration event heard by this
     */
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
