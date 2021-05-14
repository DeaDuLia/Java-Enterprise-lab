package App.config;

import App.model.Table;
import App.model.Train;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfiguration {

    @Bean
    public Table table() {
        return new Table();
    }

    @Bean
    public Train train() {
        return new Train();
    }
}
