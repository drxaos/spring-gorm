package hello.domain

import grails.persistence.Entity
import groovy.transform.EqualsAndHashCode
import groovy.transform.ToString

@Entity
@ToString
@EqualsAndHashCode
class Person {
    String firstName
    String lastName
    Integer age = 18
    Integer counter = 0

    static constraints = {
        firstName blank: false
        lastName blank: false
        age min: 0, max: 200
        counter min: 0
    }
}