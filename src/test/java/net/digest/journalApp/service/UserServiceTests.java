package net.digest.journalApp.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import net.digest.journalApp.entity.User;
import net.digest.journalApp.repository.UserRepository;



@SpringBootTest
public class UserServiceTests {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    @Disabled
    @CsvSource({
        "Ram",
        "Sudip",
        "Sam",
        "Avik"
    })
    @ParameterizedTest
    public void testFindByUserName(String userName) {
        assertNotNull(userRepository.findByUserName(userName));
    }

    @Disabled
    @CsvSource({
        "1, 1, 2",
        "2, 10, 12",
        "3, 3, 6"
    })
    @ParameterizedTest
    public void test(int a, int b, int expected){
        assertEquals(expected, a + b);
    }

    @Disabled
    @ParameterizedTest
    @ArgumentsSource(UserArgumentsProvider.class)
    public void saveNewUser(User user) {
        assertTrue(userService.saveNewUser(user));
    }
}
