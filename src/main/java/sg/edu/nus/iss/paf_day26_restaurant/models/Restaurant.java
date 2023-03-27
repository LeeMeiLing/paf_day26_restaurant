package sg.edu.nus.iss.paf_day26_restaurant.models;

import java.io.StringReader;

import jakarta.json.Json;
import jakarta.json.JsonObject;
import jakarta.json.JsonReader;

public class Restaurant {

    private String name;
    private String address;
    private String addressLine2;
    private Float rating;
    private String typeOfFood;

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }
    public String getAddressLine2() {
        return addressLine2;
    }
    public void setAddressLine2(String addressLine2) {
        this.addressLine2 = addressLine2;
    }
    public Float getRating() {
        return rating;
    }
    public void setRating(Float rating) {
        this.rating = rating;
    }
    public String getTypeOfFood() {
        return typeOfFood;
    }
    public void setTypeOfFood(String typeOfFood) {
        this.typeOfFood = typeOfFood;
    }
    

    @Override
	public String toString() {
		return "Restaurant{name=%s, address=%s, cuisine=%s}"
			.formatted(name, address, typeOfFood);
	}

	public static Restaurant toRestaurant(String jsonStr) {

		Restaurant restaurant = new Restaurant();
		JsonReader reader = Json.createReader(new StringReader(jsonStr));
		JsonObject o = reader.readObject();
		restaurant.setName(o.getString("name"));
		restaurant.setAddress(o.getString("address"));
        restaurant.setAddressLine2(o.getString("address line 2"));
		restaurant.setTypeOfFood(o.getString("type_of_food"));
		try {
			restaurant.setRating((float)o.getJsonNumber("rating").doubleValue());
		} catch (Exception ex) {
            // some data contain "not yet rated" which is a string instead of float value
			// set rating as -1 if it is a string
			restaurant.setRating(-1f);
		}
		return restaurant;
	}
  
    
    // "_id" : ObjectId("55f14312c7447c3da7051b26"),
    // "URL" : "http://www.just-eat.co.uk/restaurants-cn-chinese-cardiff/menu",
    // "address" : "228 City Road",
    // "address line 2" : "Cardiff",
    // "name" : ".CN Chinese",
    // "outcode" : "CF24",
    // "postcode" : "3JH",
    // "rating" : NumberInt(5),
    // "type_of_food" : "Chinese"
}
