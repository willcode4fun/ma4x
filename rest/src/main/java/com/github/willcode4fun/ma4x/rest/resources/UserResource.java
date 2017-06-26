package com.github.willcode4fun.ma4x.rest.resources;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by DECOSTT on 23/06/2017.
 */
@RestController
public class UserResource {
    @GetMapping("/greeting")
    public String greeting(){
        return "Hi !";
    }
}
