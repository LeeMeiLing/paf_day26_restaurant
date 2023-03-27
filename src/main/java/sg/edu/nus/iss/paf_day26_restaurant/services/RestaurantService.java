package sg.edu.nus.iss.paf_day26_restaurant.services;

import java.util.List;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sg.edu.nus.iss.paf_day26_restaurant.models.Restaurant;
import sg.edu.nus.iss.paf_day26_restaurant.repositories.RestaurantRepo;

@Service
public class RestaurantService {

    @Autowired
    RestaurantRepo resRepo;
    
    public List<String> showTypeOfFood(){

        return resRepo.showTypeOfFood();
    }

     public List<Document> findRestaurantByFoodType(String type){
        
        return resRepo.findRestaurantByFoodType(type);
    }

    public List<Restaurant> findRestaurantListByFoodType(String type){

        return resRepo.findRestaurantListByFoodType(type);

    }
}

