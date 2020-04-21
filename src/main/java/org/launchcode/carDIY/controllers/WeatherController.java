package org.launchcode.carDIY.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;

@Controller
@RequestMapping("/weather")
public class WeatherNow {

    @GetMapping()
    public String checkWeatherToday(Model model) {

        

        //Example how to pul JSON
        // request url
        String url = "https://jsonplaceholder.typicode.com/posts/1";

        // create an instance of RestTemplate
        RestTemplate restTemplate = new RestTemplate();

        // make an HTTP GET request
        String json = restTemplate.getForObject(url, String.class);

        // print json
        System.out.println(json);
    }
}
