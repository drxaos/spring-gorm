package hello.controller.api

import hello.domain.Person
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

import static org.springframework.web.bind.annotation.RequestMethod.GET
import static org.springframework.web.bind.annotation.RequestMethod.POST

@RestController
class PersonApiController {

    @RequestMapping(value = "/api/person/greet", method = GET)
    String greet(String firstName) {
        def p = Person.findByFirstName(firstName)
        return p ? "Hello ${p.firstName}!" : "Person not found"
    }

    @RequestMapping(value = '/api/person/add', method = POST)
    ResponseEntity addPerson(String firstName, String lastName) {
        Person.withTransaction {
            def p = new Person(firstName: firstName, lastName: lastName).save()
            if (p) {
                return new ResponseEntity(HttpStatus.CREATED)
            } else {
                return new ResponseEntity(HttpStatus.BAD_REQUEST)
            }
        }
    }

}