package project.marketplace.ubuyc;

import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

import project.marketplace.models.User;
import project.marketplace.models.Login;

import org.junit.jupiter.api.Test;

import org.mindrot.jbcrypt.BCrypt;

public class TestUser {

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
}
