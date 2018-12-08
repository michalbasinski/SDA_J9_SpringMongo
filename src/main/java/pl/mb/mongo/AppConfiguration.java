package pl.mb.mongo;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfiguration {

    @Bean
    UserService userService(UserRepository userRepository) {
        return new UserService(userRepository);
    }

}
