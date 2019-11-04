package com.test.Persons.Controllers;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
public class PersonsController {

    @RequestMapping("/")
    public String index() {
        return "Person List";
    }
}
