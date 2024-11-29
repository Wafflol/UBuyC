package project.marketplace.ubuyc;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Locale;
import project.marketplace.models.User;

import project.marketplace.registration.OnRegistrationCompleteEvent;
import project.marketplace.registration.RegistrationListener;

import org.junit.jupiter.api.Test;

public class RegistrationTests {

    @Test
    public void onRCEInit() {
        User testUser = new User();
        
        OnRegistrationCompleteEvent t = new OnRegistrationCompleteEvent(testUser, Locale.US);

        assertEquals(testUser, t.getUser());
        assertEquals(Locale.US, t.getLocale());
    }

    @Test
    public void regListenerInit() {

        User testUser = new User();

        OnRegistrationCompleteEvent regEvent = new OnRegistrationCompleteEvent(testUser, Locale.US);

        RegistrationListener t = new RegistrationListener();
        t.onApplicationEvent(regEvent);

    }

}