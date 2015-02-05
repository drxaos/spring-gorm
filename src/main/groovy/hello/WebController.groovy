package hello

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
public class WebController extends WebMvcConfigurerAdapter {

    @Autowired
    def peopleService

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/results").setViewName("results")
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String showForm(Person person) {
        return "form"
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public ModelAndView checkPersonInfo(@Valid Person person, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return new ModelAndView("form", bindingResult.getModel() + [error: true])
        }
        Person.withTransaction {
            person.save(flush: true, failOnError: true)
        }
        return new ModelAndView("redirect:/results")
    }

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public ModelAndView listPeople() {
        def list = peopleService.findPeople()
        return new ModelAndView("list", [list: list])
    }

}