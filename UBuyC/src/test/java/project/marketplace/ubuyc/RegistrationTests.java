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
        
        OnRegistrationCompleteEvent event = new OnRegistrationCompleteEvent(testUser, Locale.US);

        assertEquals(testUser, event.getUser());
        assertEquals(Locale.US, event.getLocale());
    }

}