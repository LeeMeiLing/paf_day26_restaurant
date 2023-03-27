package sg.edu.nus.iss.paf_day26_restaurant.repositories;

import java.util.Collections;
import java.util.List;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

@Repository
public class RestaurantRepo {
    
    @Autowired
    private MongoTemplate mongoTemplate;

    // db.restaurant.distinct("type_of_food")
    public List<String> showTypeOfFood(){

        // mongo distinct() no sorting, use java list sorting
        List<String> list = mongoTemplate.findDistinct(new Query(),"type_of_food","restaurant",String.class);
        Collections.sort(list);
        return list;
    }

    // db.restaurant.find({"type_of_food": "Chinese"})
    public List<Document> findRestaurantByFoodType(String type){

        // Create the filter - the Criteria
        Criteria criteria = Criteria.where("type_of_food").is(type);

        // Create the query
        Query query = Query.query(criteria);

        // Execute the query
        List<Document> result = mongoTemplate.find(query,Document.class,"restaurant");



        return result;
    }
}
