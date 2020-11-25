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


public class Crew {

	
	public JSONObject  get_crew_of_movie(String name) {
	String set1_query2 = QueryConstants.get_movie_crew_query(name);
	QueryExecution q1 = QueryExecutionFactory.sparqlService("http://34.72.161.198:3030/Movie1/query", set1_query2);
	ResultSet crew_results = q1.execSelect();		
	ByteArrayOutputStream bs1 = new ByteArrayOutputStream();
    ResultSetFormatter.outputAsJSON(bs1, crew_results);        
    String json  = bs1.toString();
    JSONObject crew_obj = new JSONObject();
    try {
		
		
        JSONObject obj = (JSONObject) new JSONParser().parse(json);
        JSONObject res = (JSONObject) obj.get("results");          
        JSONArray rating_info = (JSONArray) res.get("bindings");
        
        
        JSONObject head = (JSONObject) rating_info.get(0);
        
        JSONObject actor1obj = (JSONObject)head.get("actor1");
        crew_obj.put("actor1", (String)actor1obj.get("value"));
       
       JSONObject actor2obj = (JSONObject)head.get("actor2");
       crew_obj.put("actor2", (String)actor2obj.get("value"));
       
       
       JSONObject actor3obj = (JSONObject)head.get("actor3");
       crew_obj.put("actor3",  (String)actor3obj.get("value"));
       
       JSONObject directorobj = (JSONObject)head.get("director");
       crew_obj.put("director",  (String)directorobj.get("value"));
       
              
       return crew_obj;
       
       
       
    } catch (Exception e) {
        e.printStackTrace();
    }
    return crew_obj;

	}
	
	public ArrayList<String> get_movies_of_directors(String name) {
		String set1_query1 = QueryConstants.get_dir_movie_qeury(name);
		QueryExecution q = QueryExecutionFactory.sparqlService("http://34.72.161.198:3030/Movie1/query", set1_query1);
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
	        	id = id.trim();
	        	ids.add(id);
	        
	        }

	        return ids;
	        
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
		
		// TODO Auto-generated method stub
		return null;
	}
	
	 
}
