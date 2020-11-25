package com.asu.ser531.SparQL;


import java.util.ArrayList;
import java.util.Iterator;
import java.util.concurrent.atomic.AtomicLong;

import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.apache.jena.atlas.json.JSON;
import org.json.simple.JSONObject;


@RestController
public class Controller {

	private static final String template = "Hello, %s!";
	private final AtomicLong counter = new AtomicLong();

	public String set1_query1=null;


	 
	
	@SuppressWarnings("unchecked")
	@GetMapping("/getMovieInfo")
	public JSONObject getMovieInfo(@RequestParam(value = "name", defaultValue = "World") String name, HttpServletResponse response) {
		
        String json = null;
        JSONObject movie_obj = new JSONObject();
        Rating rc = new Rating();
        Crew cc = new Crew();
        MovieInfo mi = new MovieInfo();
        
        JSONObject rating_obj = rc.get_rating_from_movie(name);
        
        for (Object keyStr : rating_obj.keySet()) {
            Object keyvalue = rating_obj.get((String)keyStr);
            movie_obj.put((String)keyStr,keyvalue);
            
        }
        
        JSONObject crew_obj =cc.get_crew_of_movie(name);

        for (Object keyStr : crew_obj.keySet()) {
            Object keyvalue = crew_obj.get((String)keyStr);
            movie_obj.put((String)keyStr,keyvalue);
            
        }
        
        JSONObject movieinfo_obj = mi.get_movie_info(name);

        for (Object keyStr : movieinfo_obj.keySet()) {
            Object keyvalue = movieinfo_obj.get((String)keyStr);
            movie_obj.put((String)keyStr,keyvalue);
            
        }

        response.setHeader("Access-Control-Allow-Origin", "*");   
		return movie_obj;
	
	}
	
	
	
	@SuppressWarnings("unchecked")
	@GetMapping("/getDirectorMovieInfo")
	
public ArrayList<JSONObject>   getDirectorMovieInfo(@RequestParam(value = "name", defaultValue = "World") String name, HttpServletResponse response) {
		
        String json = null;
        JSONObject movie_obj = new JSONObject();
        Rating rc = new Rating();
        Crew cc = new Crew();
        MovieInfo mi = new MovieInfo();        
        ArrayList<String> arr = cc.get_movies_of_directors(name);
        ArrayList<JSONObject>  ids = mi.get_titles_from_movies(arr);
        response.setHeader("Access-Control-Allow-Origin", "*");
		return ids;
	
	}
	
	
	@SuppressWarnings("unchecked")
	@GetMapping("/getMovieWithMinRating")
	
public JSONObject  getMovieWithMinRating(@RequestParam(value = "rating", defaultValue = "5") String rating, HttpServletResponse response) {
		
        String json = null;
        JSONObject movie_obj = new JSONObject();
        Rating rc = new Rating();        
        MovieInfo mi = new MovieInfo();
        
        ArrayList<String> arr = rc.get_movies_with_minratings(rating);
        ArrayList<JSONObject>  ids  = mi.get_titles_from_movies(arr);
        ArrayList<JSONObject>  graphdata = rc.get_movies_with_min_max_ratings(rating);
        movie_obj.put("data", ids);
        movie_obj.put("graph", graphdata);
        response.setHeader("Access-Control-Allow-Origin", "*");
		return movie_obj;
	
	}
	
	
	
	
	
	
	
}