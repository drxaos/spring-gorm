package hello.controller

import hello.domain.Person
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestMapping

@Controller
class IndexController {

    @RequestMapping(["/", "/index"])
    String index() {
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
        return "Hello ${p.firstName} ${p.lastName}! You have been here ${p.counter} times."
    }
}
