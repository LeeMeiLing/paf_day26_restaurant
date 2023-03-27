package sg.edu.nus.iss.paf_day26_restaurant.controllers;

import java.util.List;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import sg.edu.nus.iss.paf_day26_restaurant.models.Restaurant;
import sg.edu.nus.iss.paf_day26_restaurant.repositories.RestaurantRepo;
import sg.edu.nus.iss.paf_day26_restaurant.services.RestaurantService;

@Controller
@RequestMapping("/restaurant")
public class RestaurantController {
    
    @Autowired
    RestaurantService resSvc;

    @GetMapping("/typeoffood")
    public String showTypeOfFood(Model model){

        List<String> type = resSvc.showTypeOfFood();
        model.addAttribute("type", type);
        return "typeoffood";
    }

    // @GetMapping("/findrestaurant/{type}")
    // public String showRestaurant(@PathVariable("type") String type,Model model){

    //     List<Document> restaurants = resSvc.findRestaurantByFoodType(type);
    //     model.addAttribute("restaurants", restaurants);
    //     return "restaurant";
    // }

    
    // parse data into Restaurant object and return List<Restaurant>
     @GetMapping("/findrestaurant/{type}")
    public String showRestaurantList(@PathVariable("type") String type,Model model){

        List<Restaurant> restaurants = resSvc.findRestaurantListByFoodType(type);
        model.addAttribute("restaurants", restaurants);
        return "restaurant";
    }
}
