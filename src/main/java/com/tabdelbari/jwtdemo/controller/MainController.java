package com.tabdelbari.jwtdemo.controller;


import com.tabdelbari.jwtdemo.entity.User;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


/**
 * Main Controller class for the API
 */
@RestController
@CrossOrigin("*")
@RequestMapping("/api")
public class MainController {

    /***
     * simple API endpoint for test (only authenticated users can access this endpoint)
     * see the SecurityConfiguration class
     * @return simple hello msg
     */
    @RequestMapping(value = "index", method = RequestMethod.GET)
    public ResponseEntity<String> index(){
        User user = null;

        return ResponseEntity.ok("Hello user");
    }
}
