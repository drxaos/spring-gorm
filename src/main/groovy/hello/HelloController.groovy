package hello

import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class HelloController {

    @RequestMapping("/home")
    String home() {
        def p = Person.findByFirstName("Homer")
        if (!p) {
            Person.withTransaction {
                p = new Person(firstName: "Homer", lastName: "Simpson", age: 42, counter: 0)
                p.save(flush: true, failOnError: true)
            }
        } else {
            Person.withTransaction {
                p.counter++
                p.save(flush: true, failOnError: true)
            }
        }
        return "Hello ${p.firstName}! You stared here ${p.counter} times."
    }
}
