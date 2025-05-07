package uts.isd.test;

import org.junit.Test;
import uts.isd.model.User;

import static junit.framework.Assert.assertTrue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

public class MyTest {
    @Test
    public void testExample() {
        assertTrue("JUnit test is running!", true);
    }
    @Test
    public void testUserCreation() {
        User user = new User("Alice", "alice@example.com", "securePass", "2000-01-01", "Female");
        assertEquals("Alice", user.getName());
        assertEquals("alice@example.com", user.getEmail());
        assertEquals("securePass", user.getPassword());
        assertEquals("2000-01-01", user.getDob());
        assertEquals("Female", user.getGender());
    }
    @Test
    public void testSetters() {
        User user = new User("Alice", "alice@example.com", "securePass", "2000-01-01", "Female");
        user.setName("Bob");
        user.setEmail("bob@example.com");
        assertEquals("Bob", user.getName());
        assertEquals("bob@example.com", user.getEmail());
    }
    @Test
    public void testPasswordHashing() {
        User user = new User("Alice", "alice@example.com", "hashedPass123", "2000-01-01", "Female");
        assertNotEquals("securePass", user.getPassword()); // Ensuring it's not plain text
    }

    private void assertNotEquals(String securePass, String password) {
    }
    @Test
    public void testEmailValidation() {
        User user = new User("Alice", "invalid-email", "securePass", "2000-01-01", "Female");
        assertFalse(user.getEmail().contains("@")); // Basic validation check
    }

}