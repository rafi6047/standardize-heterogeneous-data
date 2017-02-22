package se.kth.epe.degreeproject.standardizeheterogeneousdata.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * Created by Rafi on 2017-02-20.
 */
@SpringBootApplication
@ComponentScan("se.kth.epe.degreeproject.standardizeheterogeneousdata")
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
