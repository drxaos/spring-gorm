package hello.domain

import grails.persistence.Entity
import groovy.transform.EqualsAndHashCode
import groovy.transform.ToString

@Entity
@ToString
@EqualsAndHashCode
class Book {
    String author
    String title
    Integer pages

    static constraints = {
        author blank: false
        title blank: false
        pages nullable: true, min: 1

    }

    static mapping = {
        table name: "books"
    }
}