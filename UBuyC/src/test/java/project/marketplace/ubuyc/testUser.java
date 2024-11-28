package project.marketplace.ubuyc;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import project.marketplace.models.User;

import org.junit.jupiter.api.Test;

import org.mindrot.jbcrypt.BCrypt;

public class testUser {

    // @Test
    // public void bcrypt() {

    //     String password = "abc";

    //     // assertEquals(BCrypt.hashpw(password, BCrypt.gensalt(12), );

    // }


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
}
