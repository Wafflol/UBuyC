package project.marketplace.ubuyc;

import org.junit.jupiter.api.Test;
import project.marketplace.models.User;
import project.marketplace.registration.OnRegistrationCompleteEvent;

import java.util.Locale;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RegistrationTests {

    @Test
    public void onRCEInit() {
        User testUser = new User();

        OnRegistrationCompleteEvent event = new OnRegistrationCompleteEvent(testUser, Locale.US);

        assertEquals(testUser, event.getUser());
        assertEquals(Locale.US, event.getLocale());
    }

}