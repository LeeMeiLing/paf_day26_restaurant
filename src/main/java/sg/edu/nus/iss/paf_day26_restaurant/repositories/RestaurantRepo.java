package sg.edu.nus.iss.paf_day26_restaurant.repositories;

import java.util.Collections;
import java.util.List;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import sg.edu.nus.iss.paf_day26_restaurant.models.Restaurant;

@Repository
public class RestaurantRepo {

    private static final String C_RESTAURANT = "restaurant";

    @Autowired
    private MongoTemplate mongoTemplate;

    // db.restaurant.distinct("type_of_food")
    public List<String> showTypeOfFood() {

        // mongo distinct() no sorting, use java list sorting
        List<String> list = mongoTemplate.findDistinct(new Query(), "type_of_food", C_RESTAURANT, String.class);
        Collections.sort(list);
        return list;

    }


    // db.restaurant.find({"type_of_food": "Chinese"})
    public List<Document> findRestaurantByFoodType(String type) {

        Criteria criteria = Criteria.where("type_of_food").is(type);

        Query query = Query.query(criteria);

        List<Document> result = mongoTemplate.find(query, Document.class, C_RESTAURANT);

        return result;
    }


    // db.restaurant.find({
	// 	type_of_food: { $regex: 'cuisine', $options: "i" }
	// })
	// .sort({ name: -1 })
	// .projection({ name: 1, address: 1, type_of_food: 1, _id: 0 })
    public List<Restaurant> findRestaurantListByFoodType(String type) {

        Criteria criteria = Criteria.where("type_of_food").regex(type, "i"); //option "i" => Case insensitivity to match upper and lower cases

        Query query = Query.query(criteria)
                .with(Sort.by(Direction.ASC, "name"));
                
        query.fields()
                .include("name", "address", "address line 2", "type_of_food", "rating")
                .exclude("_id");

        return mongoTemplate.find(query, Document.class, C_RESTAURANT)
                .stream()
                .map(doc -> doc.toJson())
                .map(Restaurant::toRestaurant)
                .toList();

    }

}
