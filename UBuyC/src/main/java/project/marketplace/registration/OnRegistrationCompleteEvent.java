package project.marketplace.registration;

import java.util.Locale;
import java.util.Random;

import org.springframework.context.ApplicationEvent;

import project.marketplace.models.User;

public class OnRegistrationCompleteEvent extends ApplicationEvent {
    private Locale locale;
    private User user;

    public OnRegistrationCompleteEvent(User user, Locale locale) {
        super(user);
        this.user = user;
        this.locale = locale;
    }
    
    public Locale getLocale() {
        return this.locale;
    }

    public User getUser() {
        return this.user;
    }
}