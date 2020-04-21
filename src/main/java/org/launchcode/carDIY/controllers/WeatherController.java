package org.launchcode.carDIY.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("weather")
public class WeatherController {

       @PostMapping()
    public String checkWeatherToday(@RequestParam int qtyDaysForecast, int zip, Model model) throws JsonProcessingException {

        //
            // request url
            String url = "http://api.weatherapi.com/v1/forecast.json?key=1d8ed8e69b0a4617bf9140323200904&q={zip}&days={qtyDaysForecast}";

            // create an instance of RestTemplate
            RestTemplate restTemplate = new RestTemplate();

            // make an HTTP GET request
            String json = restTemplate.getForObject(url, String.class, zip, qtyDaysForecast);

           //create ObjectMapper instance
           ObjectMapper objectMapper = new ObjectMapper();

            //convert json file to map
           Map<?, ?> map = objectMapper.readValue(json, Map.class);
           Object loc = map.get("location");

           //location
           Map<?,?> locationMap = (Map<?, ?>) map.get("location");
           String locationName = (String) locationMap.get("name");
           String locationRegion = (String) locationMap.get("region");
           String locationLocalTime = (String) locationMap.get("localtime");

            //current weather
            Map<?,?> currentMap = (Map<?,?>) map.get("current");
            Double currentTempF =(Double) currentMap.get("temp_f");
            Map<?,?> currentCondition = (Map<?,?>) currentMap.get("condition");
            String conditionText = (String) currentCondition.get("text");

            //forecast
            Map<?,?> forecastMap = (Map<?,?>) map.get("forecast");
            List<?> forecastdayList = (ArrayList<?>) forecastMap.get("forecastday");
            ArrayList<String> dates = new ArrayList<>();
            ArrayList<Double> averageTemp = new ArrayList<>();
            ArrayList<String> conditionForecast = new ArrayList<>();



            for(int i = 0; i<forecastdayList.size(); i++) {
                Map<?,?> entry = (Map<?, ?>) forecastdayList.get(i);
                dates.add((String) entry.get("date"));
                Map<?,?> forecastDay = (Map<?, ?>) entry.get("day");

                averageTemp.add((Double) forecastDay.get("avgtemp_f"));
                Map<?,?> conditionEntry = (Map<?, ?>) forecastDay.get("condition");
                conditionForecast.add((String) conditionEntry.get("text"));


            }

           // print json
            //System.out.println(json);

            // send to model
            model.addAttribute("locationName", locationName);
            model.addAttribute("locationRegion", locationRegion);
            model.addAttribute("currentTempF", currentTempF);
            model.addAttribute("conditionText", conditionText);

            model.addAttribute("locationLocalTime", locationLocalTime);
            model.addAttribute("dates", dates);
            model.addAttribute("averageTemp", averageTemp);
            model.addAttribute("conditionForecast", conditionForecast);


            return "weather/index";
    }
}
