package com.asu.ser531.SparQL;


import java.util.ArrayList;
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


public class MovieInfo {

	
	public JSONObject  get_movie_info(String name) {
		
	String set1_query2 = QueryConstants.get_movie_query(name);
	QueryExecution q1 = QueryExecutionFactory.sparqlService("http://18.191.196.100:3030/Movie3/query", set1_query2);
	ResultSet crew_results = q1.execSelect();		
	ByteArrayOutputStream bs1 = new ByteArrayOutputStream();
    ResultSetFormatter.outputAsJSON(bs1, crew_results);        
    String json  = bs1.toString();
    JSONObject movie_obj = new JSONObject();
    try {
		
    			
        JSONObject obj = (JSONObject) new JSONParser().parse(json);
        JSONObject res = (JSONObject) obj.get("results");          
        JSONArray rating_info = (JSONArray) res.get("bindings");
        
        
        JSONObject head = (JSONObject) rating_info.get(0);
        
        JSONObject titleobj = (JSONObject)head.get("title");
        movie_obj.put("title", (String)titleobj.get("value"));
       
       JSONObject durationobj = (JSONObject)head.get("duration");
       movie_obj.put("duration", (String)durationobj.get("value"));
              
       
       
       JSONObject midobj = (JSONObject)head.get("mid");
       movie_obj.put("id",  (String)midobj.get("value"));
       
       
       
       JSONObject genreobj = (JSONObject)head.get("genre");
       movie_obj.put("genre",  (String)genreobj.get("value"));
       
       JSONObject countryobj = (JSONObject)head.get("country");
       movie_obj.put("country",  (String)countryobj.get("value"));
      
       JSONObject languageobj = (JSONObject)head.get("language");
       movie_obj.put("language",  (String)languageobj.get("value"));
             
      
     
              
       return movie_obj;
       
       
       
    } catch (Exception e) {
        e.printStackTrace();
    }
    return movie_obj;

	}
	
	public ArrayList<JSONObject>  get_titles_from_movies(ArrayList<String> ids) {
		String set1_query1 = QueryConstants.get_movie_titles_query(ids);
		QueryExecution q = QueryExecutionFactory.sparqlService("http://18.191.196.100:3030/Movie3/query", set1_query1);
		ResultSet results = q.execSelect();		
		ByteArrayOutputStream os = new ByteArrayOutputStream();
	    ResultSetFormatter.outputAsJSON(os, results);        
	    String json  = os.toString();
	    ArrayList<JSONObject> ids1 = new ArrayList<JSONObject>();

	    try {
				    	
	        JSONObject obj = (JSONObject) new JSONParser().parse(json);
	        JSONObject res = (JSONObject) obj.get("results");          
	        JSONArray rating_info = (JSONArray) res.get("bindings");
	        
	        for(int j=0 ; j<rating_info.size();j++) {
	        	JSONObject temp = new JSONObject();
	        	JSONObject head =  (JSONObject) rating_info.get(j);
	        	JSONObject idobj = (JSONObject)head.get("title");
	        	String	 id =    (String) idobj.get("value");
	        	temp.put("id", j+1);
	        	temp.put("movie", id);
	        	ids1.add(temp);
	        
	        }

	        return ids1;
	        
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
		
		// TODO Auto-generated method stub
		return null;		
		
		
	};
	
	
	
	}

