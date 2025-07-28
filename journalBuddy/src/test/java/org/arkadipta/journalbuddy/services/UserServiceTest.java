package org.arkadipta.journalbuddy.services;

import org.arkadipta.journalbuddy.Repository.UserRepo;
import org.arkadipta.journalbuddy.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest
public class UserServiceTest {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private UserService userService;

    @MockBean
    private PasswordEncoder passwordEncoder;

    @Test
    public void testAdd(){
        assertEquals(4,6-2);
    }

    @Test
    public void testFindUserByUserName(){
        assertNotNull(userRepo.findUserByUserName("arka"));
    }

    @Test
    public void testSaveNewUser() {
        User user = new User();
        user.setUserName("testuser");
        user.setPassword("plaintextpassword");

        // Mock password encoding
        when(passwordEncoder.encode("plaintextpassword")).thenReturn("encodedpassword");

        userService.saveNewUser(user);

        assertEquals("encodedpassword", user.getPassword());
        assertEquals(1, user.getRoles().size());
        assertTrue(user.getRoles().contains("USER"));
    }
}
