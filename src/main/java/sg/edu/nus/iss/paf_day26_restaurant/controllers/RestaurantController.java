package sg.edu.nus.iss.paf_day26_restaurant.controllers;

import java.util.List;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import sg.edu.nus.iss.paf_day26_restaurant.repositories.RestaurantRepo;

@Controller
@RequestMapping("/restaurant")
public class RestaurantController {
    
    @Autowired
    RestaurantRepo resRepo;

    @GetMapping("/typeoffood")
    public String showTypeOfFood(Model model){

        List<String> type = resRepo.showTypeOfFood();
        model.addAttribute("type", type);
        return "typeoffood";
    }

    @GetMapping("/findrestaurant/{type}")
    public String showRestaurant(@PathVariable("type") String type,Model model){

        List<Document> restaurants = resRepo.findRestaurantByFoodType(type);
        // System.out.println("=============================");
        // System.out.println(restaurants.get(0).get("address line 2"));
        model.addAttribute("restaurants", restaurants);
        return "restaurant";
    }
}
