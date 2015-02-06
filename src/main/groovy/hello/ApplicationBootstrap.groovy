package hello

import groovy.util.logging.Log4j
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

        Person.withTransaction {

            new Person(firstName: "John", lastName: "Johnson", age: 33).save(flush: true, failOnError: true)
            new Person(firstName: "Homer", lastName: "Simpson", age: 38).save(flush: true, failOnError: true)

        }
    }
}