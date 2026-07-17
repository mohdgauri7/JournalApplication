package com.mamg.journalApp.service;


import com.mamg.journalApp.entity.User;
import com.mamg.journalApp.repository.UserRepository;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
public class UserServiceTests {

    @Autowired
    private UserRepository userRepository;

    @BeforeAll
    public static void setUp() {

    }

    @BeforeEach
    public void testBeforeEach() {

    }
    @AfterEach
    public void testAfterEach() {

    }

    @AfterAll
    public static void testAfterAll() {

    }

    @Test
    public void testFindByUsername() {
        User user = userRepository.findByUsername("user1");
        assertNotNull(user);
    }

    @ParameterizedTest
    @CsvSource({
            "1,1,2",
            "2,2,4",
            "3,3,9"
    })
    public void test(int a, int b, int expected) {
        assertEquals(a + b, expected);
    }

}
