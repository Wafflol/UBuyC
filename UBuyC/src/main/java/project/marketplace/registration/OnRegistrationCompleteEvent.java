package project.marketplace.registration;

import java.util.Locale;

import org.springframework.context.ApplicationEvent;

import project.marketplace.models.User;

/**
 * Creates a registration event object that is listened to by the Registration Listener
 */
public class OnRegistrationCompleteEvent extends ApplicationEvent {
    private final Locale locale;
    private final User user;

    /**
     * Creates the registration complete event instance
     * 
     * @param user The user linked to the registration event
     * @param locale The locale of the user
     */
    public OnRegistrationCompleteEvent(User user, Locale locale) {
        super(user);
        this.user = user;
        this.locale = locale;
    }
    
    /**
     * Returns the locale of this event
     * 
     * @return the locale of this event
     */
    public Locale getLocale() {
        return this.locale;
    }

    /**
     * Returns the user linked to this event
     * 
     * @return the user linked to this event
     */
    public User getUser() {
        return this.user;
    }
}