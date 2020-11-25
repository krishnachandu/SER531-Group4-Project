package com.asu.ser531.SparQL;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

//
import org.apache.jena.query.QueryExecution;
import org.apache.jena.query.QueryExecutionFactory;
import org.apache.jena.query.QuerySolution;
import org.apache.jena.query.ResultSet;
import org.apache.jena.query.ResultSetFormatter;
import org.apache.jena.rdf.model.RDFNode;
import java.io.ByteArrayOutputStream;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class Rating {

	
	public JSONObject get_rating_from_movie(String name) {
	
    JSONObject rating_obj = new JSONObject();

	String set1_query1 = QueryConstants.get_movie_rating_query(name);
	QueryExecution q = QueryExecutionFactory.sparqlService("http://35.232.77.207:3030/Movie2/sparql", set1_query1);
	ResultSet results = q.execSelect();		
	ByteArrayOutputStream os = new ByteArrayOutputStream();
    ResultSetFormatter.outputAsJSON(os, results);        
    String json  = os.toString();
	
	try {
		
		
        JSONObject obj = (JSONObject) new JSONParser().parse(json);
        JSONObject res = (JSONObject) obj.get("results");          
        JSONArray rating_info = (JSONArray) res.get("bindings");
        
        
        JSONObject head = (JSONObject) rating_info.get(0);
        
        JSONObject imdbobj = (JSONObject)head.get("imdblink");
        rating_obj.put("imdblink", (String)imdbobj.get("value"));
       
       JSONObject numusersobj = (JSONObject)head.get("numusers");
       rating_obj.put("numusers", (String)numusersobj.get("value"));
       
       
       JSONObject numvotedobj = (JSONObject)head.get("numvoted");
       rating_obj.put("numvoted",  (String)numvotedobj.get("value"));
       
       JSONObject imdbscoreobj = (JSONObject)head.get("imdbscore");
       rating_obj.put("imdbscore",  (String)imdbscoreobj.get("value"));
       
       return rating_obj;
       
    } catch (Exception e) {
        e.printStackTrace();
    }
	return rating_obj;
	
    
}
	
	
	
	public ArrayList<String> get_movies_with_minratings(String rating) {
		String set1_query1 = QueryConstants.get_movie_rating(rating);
		QueryExecution q = QueryExecutionFactory.sparqlService("http://35.232.77.207:3030/Movie2/sparql", set1_query1);
		ResultSet results = q.execSelect();		
		ByteArrayOutputStream os = new ByteArrayOutputStream();
	    ResultSetFormatter.outputAsJSON(os, results);        
	    String json  = os.toString();
	    ArrayList<String> ids = new ArrayList<String>();

	    try {
				    	
	        JSONObject obj = (JSONObject) new JSONParser().parse(json);
	        JSONObject res = (JSONObject) obj.get("results");          
	        JSONArray rating_info = (JSONArray) res.get("bindings");
	        
	        for(int j=0 ; j<rating_info.size();j++) {
	        	
	        	JSONObject head =  (JSONObject) rating_info.get(j);
	        	JSONObject idobj = (JSONObject)head.get("mid1");
	        	String	 id =    (String) idobj.get("value");
	        	ids.add(id);
	        
	        }

	        return ids;
	        
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
		
		// TODO Auto-generated method stub
		return null;
	}
	
	
	public ArrayList<JSONObject> get_movies_with_min_max_ratings(String rating) {

//		As we have a fixed dataset,we store this instead of triggering spaqrl 
//		and retutn the sublist of ratings which is greater than the minimum rating.
		ArrayList<String> ratings = new ArrayList<String>(Arrays.asList("7", "36", "115", "326", "972", "1811", "1450", "319" , "9")); 	
		ArrayList<JSONObject> temp =new ArrayList<JSONObject>();		
		for(int i=Integer.parseInt(rating)-1; i<ratings.size();i++) {
			JSONObject t = new JSONObject();
			t.put("x",String.valueOf(i+1));
			t.put("y",ratings.get(i));
			temp.add(t);
		
		}
		return temp;
		 
	
	};
	
}
