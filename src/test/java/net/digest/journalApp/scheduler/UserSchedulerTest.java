package net.digest.journalApp.scheduler;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import net.digest.journalApp.scheduler.UserScheduler;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class UserSchedulerTest {

    @Autowired
    private UserScheduler userScheduler;

    @Disabled
    @Test
    void testFetchAndSendMail() {
        userScheduler.fetchUsersAndSendMail();
    }
}
