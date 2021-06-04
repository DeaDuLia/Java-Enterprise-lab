package App.config;

import App.model.Table1;
import App.model.Train;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;
import java.sql.DriverManager;

@Configuration
public class AppConfiguration {

    @Bean
    public Table1 table1() {
        return new Table1();
    }

    @Bean
    public Train train() {
        return new Train();
    }

    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("org.postgresql.Driver");
        dataSource.setUrl("jdbc:postgresql://ec2-54-155-35-88.eu-west-1.compute.amazonaws.com:5432/d4c9d2n7narnpk?sslmode=require");
        dataSource.setUsername("sjdklptpubnxzm");
        dataSource.setPassword("ec53a22996dee3cf55a7754050771d5e00c81155f5b8b339517a95e61ace6ddc");
        return dataSource;
    }

    @Bean
    public JdbcTemplate jdbcTemplate () {
        return new JdbcTemplate(dataSource());
    }
}
