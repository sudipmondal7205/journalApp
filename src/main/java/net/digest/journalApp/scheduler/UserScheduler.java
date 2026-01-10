package net.digest.journalApp.scheduler;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import net.digest.journalApp.entity.JournalEntry;
import net.digest.journalApp.entity.User;
import net.digest.journalApp.enums.Sentiment;
import net.digest.journalApp.repository.UserRepositoryImpl;
import net.digest.journalApp.service.EmailService;


@Component
public class UserScheduler {

    @Autowired
    private EmailService emailService;

    @Autowired
    private UserRepositoryImpl userRepository;

//    @Scheduled(cron = " 0 0 9 * * SUN")
    public void fetchUsersAndSendMail() {
        List<User> users = userRepository.getUserForSA();
        for (User user : users) {
            List<JournalEntry> journalEntries = user.getJournalEntries();
            List<Sentiment> sentiments = journalEntries.stream().filter(x -> x.getDate().isAfter(LocalDateTime.now().minus(30, ChronoUnit.DAYS))).map(x -> x.getSentiment()).toList();
            Map<Sentiment, Integer> sentimentCounts = new EnumMap<>(Sentiment.class);
            for (Sentiment sentiment : sentiments) {
                if(sentiment != null) {
                    sentimentCounts.put(sentiment, sentimentCounts.getOrDefault(sentiment, 0) + 1);
                }
            }
            Sentiment mostFreqSentiment = null;
            int max = 0;
            for (Map.Entry<Sentiment, Integer> sentiment : sentimentCounts.entrySet()) {
                if(sentiment.getValue() > max) {
                    max = sentiment.getValue();
                    mostFreqSentiment = sentiment.getKey();
                }
            }
            if (mostFreqSentiment != null) {
                emailService.sendMail(user.getEmail(), "Sentiment for last 7 days", mostFreqSentiment.toString());
            }

        }
    }
}
