package com.asu.ser531.SparQL;


import java.io.OutputStream;

import org.apache.jena.query.QueryExecution;
import org.apache.jena.query.QueryExecutionFactory;
import org.apache.jena.query.QuerySolution;
import org.apache.jena.query.ResultSet;
import org.apache.jena.query.ResultSetFormatter;
import org.apache.jena.rdf.model.RDFNode;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
 


public class SemanticWeb {

	public static ResultSet execSelectAndPrint(String serviceURI, String query) {
		QueryExecution q = QueryExecutionFactory.sparqlService(serviceURI, query);
		ResultSet results = q.execSelect();
//		OutputStream j = new OutputStream();
		ResultSetFormatter.outputAsJSON(results);
//		ResultSetFormatter.out(System.out, results);
		return results;
	}

//	while (results.hasNext()) {
//			QuerySolution soln = results.nextSolution();
//			RDFNode PropertyCrimevalue = soln.get("PropertyCrimevalue");
//			System.out.println(PropertyCrimevalue);
//		}
//	}
	
	

	public static void main(String[] args) {
		execSelectAndPrint(  "http://34.72.161.198:3030/dataset1/query",
				"\n"
				+ "PREFIX movie1: <http://www.semanticweb.org/imdb/mve.owl/>\n"
				+ "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>\n"
				+ "PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>\n"
				+ "PREFIX owl: <http://www.w3.org/2002/07/owl#>\n"
				+ "PREFIX xsd: <http://www.w3.org/2001/XMLSchema#>\n"
				+ "\n"
				+ "\n"
				+ "SELECT ?title  ?duration ?mid ?genre ?country ?language\n"
				+ "WHERE {\n"
				+ "  ?movieid movie1:is_id ?mid1.\n"
				+ "  ?movieid movie1:has_title ?title.\n"
				+ "	?movieid  movie1:has_duration ?duration.\n"
				+ "  ?movieid movie1:has_genres ?genre.\n"
				+ "  ?movieid movie1:has_language ?language.\n"
				+ "    ?movieid movie1:has_year ?year.\n"
				+ "    ?movieid movie1:has_release_country ?country.\n"
				+ "\n"
				+ "     SERVICE <http://localhost:3030/ds/query>\n"
				+ "        {\n"
				+ "        SELECT ?mid1 WHERE { \n"
				+ "          ?movieid movie1:is_id ?mid1.\n"
				+ "          ?movieid movie1:is_title ?title1.\n"
				+ "          FILTER ((?title1)= \"TurboÂ \")\n"
				+ "          }     \n"
				+ "  }\n"
				+ "  \n"
				+ "}"	);
	}
}