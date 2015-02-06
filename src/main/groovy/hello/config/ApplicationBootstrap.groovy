package hello.config

import groovy.util.logging.Log4j
import hello.domain.Book
import hello.domain.Person
import org.springframework.context.ApplicationListener
import org.springframework.context.event.ContextRefreshedEvent
import org.springframework.stereotype.Component

@Log4j
@Component
public class ApplicationBootstrap implements ApplicationListener<ContextRefreshedEvent> {

    private boolean initialized = false;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        if (initialized) {
            return;
        }
        initialized = true;
        log.info("Bootstrap")
        bootstrap()
    }

    public static void bootstrap() {
        Person.withTransaction {
            new Person(firstName: "John", lastName: "Johnson", age: 33).save(flush: true, failOnError: true)
            new Person(firstName: "Homer", lastName: "Simpson", age: 38).save(flush: true, failOnError: true)
        }

        Book.withTransaction {
            new Book(author: "John Johnson", title: "Spring for teapots", pages: 2500).save(flush: true, failOnError: true)
        }
    }
}