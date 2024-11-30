package project.marketplace.ubuyc;

import org.junit.jupiter.api.Test;
import org.mindrot.jbcrypt.BCrypt;
import project.marketplace.models.Listing;
import project.marketplace.models.Login;
import project.marketplace.models.User;
import project.marketplace.models.VerificationToken;

import java.time.Duration;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

public class ModelTests {

    @Test
    public void UserInitialize() {
        User testUser = new User();

        String testString = "Test";

        testUser.setEmail(testString);
        testUser.setFirstName(testString);
        testUser.setLastName(testString);
        testUser.setId(123);
        testUser.setPasswordHash(testString);
        testUser.setValidated(true);

        assertEquals(testString, testUser.getEmail());
        assertEquals(testString, testUser.getFirstName());
        assertEquals(testString, testUser.getLastName());
        assertNotEquals(testString, testUser.getPasswordHash());
        assertTrue(testUser.getValidation());
        assertEquals(123, testUser.getId());
    }

    @Test
    public void testHash() {
        User peterGao = new User();

        peterGao.setEmail("petergao@gmail.com");
        peterGao.setFirstName("Peter");
        peterGao.setLastName("Gao");
        peterGao.setValidated(true);
        peterGao.setPasswordHash("abc123xyz");

        User u2 = new User();
        u2.setPasswordHash("abc123xyz");

        assertTrue(peterGao.compare("abc123xyz"));
        assertTrue(u2.compare("abc123xyz"));

        assertNotEquals(peterGao.getPasswordHash(), u2.getPasswordHash());
    }


    @Test
    public void loginInit() {

        Login testLogin = new Login();
        testLogin.setEmail("Test");
        testLogin.setPassword("Test");

        assertEquals("Test", testLogin.getEmail());
        assertEquals("Test", testLogin.getPassword());

    }

    @Test
    public void testLogin() {
        User u1 = new User();

        u1.setPasswordHash("ABC");
        String savedHash = u1.getPasswordHash();

        Login login = new Login();
        login.setPassword("ABC");

        String password = login.getPassword();

        assertTrue(BCrypt.checkpw(password, savedHash));
        assertFalse(BCrypt.checkpw("abc", savedHash));

    }

     @Test
    public void listingInit() {
        String ts = "test";
        byte[] image = {1};
        
        Listing testListing = new Listing(ts, ts, ts, 1, image);
        assertEquals(ts, testListing.getEmail());
        assertEquals(ts, testListing.getTitle());
        assertEquals(ts, testListing.getDescription());
        assertEquals(1, testListing.getPrice());
        assertEquals(image, testListing.getImage());

    }

    @Test
    public void listingSetters() {

        Listing testListing = new Listing();

        byte[] image = {1};
        String ts = "Test";
        LocalDateTime time = LocalDateTime.of(1,1,1,1,1);
        testListing.setDescription(ts);
        testListing.setEmail(ts);
        testListing.setImage(image);
        testListing.setListingAge(time);
        testListing.setPrice(1);
        testListing.setTitle(ts);

        assertEquals(ts, testListing.getEmail());
        assertEquals(ts, testListing.getTitle());
        assertEquals(ts, testListing.getDescription());
        assertEquals(1, testListing.getPrice());
        assertEquals(ts, testListing.getDescription());
        assertEquals(time, testListing.getListingAge());
        assertEquals(image, testListing.getImage());
        assertNotNull(testListing.getId());
    }

    @Test
    public void vTokenInit() {
        User testUser = new User();

        String testString = "Test";

        testUser.setEmail(testString);
        testUser.setFirstName(testString);
        testUser.setLastName(testString);
        testUser.setId(123);
        testUser.setPassword(testString);
        testUser.setValidated(true);

        VerificationToken t = new VerificationToken(testUser);

        assertEquals(testUser, t.getUser());
        assertNotNull(t.getOtp());
        assertTrue(Duration.between(LocalDateTime.now().plusMinutes(10), t.getExpiryDate()).toMillis() < 1000);

    }
}
