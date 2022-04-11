package com.trackerservice.ProgressPal.Workout;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class WorkoutConfig {
    @Bean
    CommandLineRunner commandLineRunner(WorkoutRepository repository) {
        return args -> {
           //Workout w1 = new Workout("Push", LocalDateTime.now(),25.4,"fun", true);
           //Workout w2 = new Workout("Pull", LocalDateTime.now(),235.4,"fun", false);
           //repository.saveAll(List.of(w1,w2));
        };
    }
}
