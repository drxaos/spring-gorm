package hello

import grails.persistence.*

@Entity
class Person {
    String firstName
    String lastName
    Integer age = 18
    Integer counter = 0

    static constraints = {
        firstName blank:false
        lastName blank:false
        age min: 0, max: 200
        counter min: 0
    }
}