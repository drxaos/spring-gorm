package hello.controller.mvc

import hello.domain.Person
import hello.service.PeopleService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.validation.BindingResult
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.servlet.ModelAndView
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter

import javax.validation.Valid

@Controller
public class PersonController extends WebMvcConfigurerAdapter {

    @Autowired
    PeopleService peopleService

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/congratulations").setViewName("person/congratulations")
    }

    @RequestMapping(value = "/person/add", method = RequestMethod.GET)
    public String add(Person person) {
        return "person/form"
    }

    @RequestMapping(value = "/person/add.do", method = RequestMethod.POST)
    public ModelAndView doAdd(@Valid Person person, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return new ModelAndView("person/form", bindingResult.getModel() + [error: true])
        }
        Person.withTransaction {
            person.save(flush: true, failOnError: true)
        }
        return new ModelAndView("redirect:/results")
    }

    @RequestMapping(value = "/person/list", method = RequestMethod.GET)
    public ModelAndView list() {
        def list = peopleService.findPeople()
        def ages = list.collectEntries { [it.id, peopleService.getBirthdayYear(it)] }
        return new ModelAndView("person/list", [list: list, ages: ages])
    }

}