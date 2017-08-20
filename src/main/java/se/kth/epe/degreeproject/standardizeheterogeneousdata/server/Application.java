package se.kth.epe.degreeproject.standardizeheterogeneousdata.server;

import org.neo4j.ogm.session.SessionFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.neo4j.repository.config.EnableNeo4jRepositories;
import org.springframework.data.neo4j.transaction.Neo4jTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * Created by Rafi on 2017-02-20.
 */
@SpringBootApplication
@EnableNeo4jRepositories(basePackages = "se.kth.epe.degreeproject.standardizeheterogeneousdata.repository")
@EnableTransactionManagement
@ComponentScan("se.kth.epe.degreeproject.standardizeheterogeneousdata")
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    public SessionFactory sessionFactory() {
        // with domain entity base package(s)
        return new SessionFactory("se.kth.epe.degreeproject.standardizeheterogeneousdata.domain");
    }

    @Bean
    public Neo4jTransactionManager transactionManager() {
        return new Neo4jTransactionManager(sessionFactory());
    }
}
