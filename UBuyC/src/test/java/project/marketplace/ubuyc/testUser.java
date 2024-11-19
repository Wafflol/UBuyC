package project.marketplace.ubuyc;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.example.User;
import org.junit.jupiter.api.Test;

public class testUser {
    @Test
    public void testHash() {
        User peterGao = new User("peter", "gao", "petergao@student.ubc.ca", "a");
        
        assertEquals("ca978112ca1bbdcafac231b39a23dc4da786eff8147c4e72b9807785afee48bb", peterGao.getPasswordHash());
    }
}
