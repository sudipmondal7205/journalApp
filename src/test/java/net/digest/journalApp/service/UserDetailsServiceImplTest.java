package net.digest.journalApp.service;

import net.digest.journalApp.entity.User;
import net.digest.journalApp.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.core.userdetails.UserDetails;


import java.util.ArrayList;

import static org.bson.assertions.Assertions.assertNotNull;
import static org.mockito.Mockito.when;



public class UserDetailsServiceImplTest {

    @InjectMocks
    private UserDetailsServiceImpl userDetailsService;

    @Mock
    private UserRepository userRepository;

    @BeforeEach
    void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Disabled
    @Test
    public void loadUserByUsernameTest() {
        when(userRepository.findByUserName(ArgumentMatchers.anyString()))
        .thenReturn(User.builder().userName("Saksham").password("9uhr274fin")
        .roles(new ArrayList<>()).build());
        UserDetails user = userDetailsService.loadUserByUsername("Ram");
        assertNotNull(user);
    }
}
