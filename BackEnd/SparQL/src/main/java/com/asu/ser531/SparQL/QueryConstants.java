package com.asu.ser531.SparQL;

import java.util.ArrayList;

public class  QueryConstants {
	
	public static String get_movie_rating_query(String title) {
		return "\n"
				+ "PREFIX movie3: <http://www.semanticweb.org/imdb/movie3.owl#>\n"
				+ "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>\n"
				+ "PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>\n"
				+ "PREFIX owl: <http://www.w3.org/2002/07/owl#>\n"
				+ "PREFIX xsd: <http://www.w3.org/2001/XMLSchema#>\n"
				+ "PREFIX movie: <http://www.semanticweb.org/imdb/mve.owl/>\n"
				+ "\n"
				+ "SELECT ?mid1 ?imdblink  ?imdbscore ?numusers ?numvoted\n"
				+ "  WHERE {\n"
				+ "    ?movieid movie3:is_id ?mid1.\n"
				+ "    ?movieid movie3:is_imdb_link ?imdblink.\n"
				+ "    ?movieid movie3:is_imdb_score ?imdbscore.\n"
				+ "    ?movieid movie3:is_num_user_for_reviews ?numusers.\n"
				+ "    ?movieid movie3:is_num_voted_users ?numvoted.\n"
				+ "\n"
				+ "      SERVICE <http://18.191.196.100:3030/Movie3/query>{\n"
				+ "       SELECT ?mid1 WHERE {\n"
				+ "            ?movieid movie:is_id ?mid1.\n"
				+ "            ?movieid movie:is_title ?title1.\n"
				+ "      FILTER (CONTAINS ((?title1), "+ title +"))\n"
				+ "            }\n"
				+ "   }\n"
				+ "}\n"
				+ "LIMIT 25\n"
				+ "";
 }
	
	
	public static String get_movie_crew_query(String title) {
			
		
		return  "PREFIX movie1: <http://www.semanticweb.org/imdb/movie2.owl#>\n"
				+ "PREFIX movie: <http://www.semanticweb.org/imdb/mve.owl/>\n"
				+ "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>\n"
				+ "PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>\n"
				+ "PREFIX owl: <http://www.w3.org/2002/07/owl#>\n"
				+ "PREFIX xsd: <http://www.w3.org/2001/XMLSchema#>\n"
				+ "\n"
				+ "\n"
				+ "SELECT ?actor1  ?actor2 ?actor3 ?director\n"
				+ "  WHERE {\n"
				+ "    ?movieid movie1:is_id ?mid1.\n"
				+ "    ?movieid movie1:is_actor_1 ?actor1.\n"
				+ "  	?movieid movie1:is_actor_2 ?actor2.\n"
				+ "    ?movieid movie1:is_actor_3 ?actor3.\n"
				+ "    ?movieid movie1:is_director ?director.\n"
				+ "\n"
				+ "      SERVICE <http://18.191.196.100:3030/Movie3/query>{\n"
				+ "       SELECT ?mid1 WHERE {\n"
				+ "            ?movieid movie:is_id ?mid1.\n"
				+ "            ?movieid movie:is_title ?title1.\n"
				+ "      FILTER (CONTAINS ((?title1), "+ title +"))\n"
				+ "            }\n"
				+ "   }\n"
				+ "}\n"
				+ "      LIMIT 25 \n"
				+"";
	}
	
	
	public static String get_movie_query(String title) {
		return "      PREFIX movie1: <http://www.semanticweb.org/imdb/mve.owl/>\n"
				+ "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>\n"
				+ "PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>\n"
				+ "PREFIX owl: <http://www.w3.org/2002/07/owl#>\n"
				+ "PREFIX xsd: <http://www.w3.org/2001/XMLSchema#>\n"
				+ "PREFIX : <undefined>\n"
				+ "SELECT ?title  ?duration ?mid ?genre ?country ?language\n"
				+ "WHERE {\n"
				+ "  ?movieid movie1:is_id ?mid.\n"
				+ "  ?movieid movie1:is_title ?title.\n"
				+ "  ?movieid  movie1:is_duration ?duration.\n"
				+ "  ?movieid movie1:is_genre ?genre.\n"
				+ "  ?movieid movie1:is_language ?language.\n"
				+ "  ?movieid movie1:is_release_year ?year.\n"
				+ "  ?movieid movie1:is_release_country ?country.\n"
				+ "\n"
				+ "          FILTER (CONTAINS((?title), "+ title +"))\n"
				+ "\n"
				+ "  }\n"
				+ "";
	}
	
	public static String get_dir_movie_qeury(String director) {
		
		return "PREFIX movie1: <http://www.semanticweb.org/imdb/movie2.owl#>\n"
				+ "PREFIX movie: <http://www.semanticweb.org/imdb/mve.owl/>\n"
				+ "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>\n"
				+ "PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>\n"
				+ "PREFIX owl: <http://www.w3.org/2002/07/owl#>\n"
				+ "PREFIX xsd: <http://www.w3.org/2001/XMLSchema#>\n"
				+ "\n"
				+ "\n"+
				"SELECT ?mid1 WHERE {\n"
				+ "      ?movieid movie1:is_id ?mid1.\n"
				+ "      ?movieid movie1:is_director ?director.\n"
	            +"FILTER (CONTAINS((?director),"+ director  +"))"
				+ "      }\n"
				+ "";
	}
	
	
	public static String get_movie_titles_query(ArrayList<String> arr) {
		String ids="(";
		int i =0;
		for (i = 0; i < arr.size()-1; i++) {
			ids = ids +"'"+ (String)arr.get(i) +"'"  + ",";
			}
		ids = ids + (String)arr.get(i);
		ids=ids+")";
		
		return 
				"      PREFIX movie1: <http://www.semanticweb.org/imdb/mve.owl/>\n"
				+ "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>\n"
				+ "PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>\n"
				+ "PREFIX owl: <http://www.w3.org/2002/07/owl#>\n"
				+ "PREFIX xsd: <http://www.w3.org/2001/XMLSchema#>\n"
				+ "PREFIX : <undefined>\n"+
				"    SELECT ?title\n"
				+ "    WHERE {\n"
				+ "      ?movieid movie1:is_id ?mid1.\n"
				+ "      ?movieid movie1:has_title ?title.\n"
				+ "\n"
				+ "  FILTER ((?mid1) IN"+ ids + ")\n"
				+ "  }\n"
				+ "  LIMIT 25\n"
				+ "";
		
	}
	
	public static String get_movie_rating(String minrating) {
		return "PREFIX movie3: <http://www.semanticweb.org/imdb/movie3.owl#>\n"
				+ "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>\n"
				+ "PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>\n"
				+ "PREFIX owl: <http://www.w3.org/2002/07/owl#>\n"
				+ "PREFIX xsd: <http://www.w3.org/2001/XMLSchema#>\n"
				+ "PREFIX movie: <http://www.semanticweb.org/imdb/mve.owl/>\n"
				+ "\n"
				+ "SELECT ?mid1 ?imdblink  ?imdbscore ?numusers ?numvoted\n"
				+ "  WHERE {\n"
				+ "    ?movieid movie3:is_id ?mid1.\n"
				+ "    ?movieid movie3:is_imdb_link ?imdblink.\n"
				+ "    ?movieid movie3:is_imdb_score ?imdbscore.\n"
				+ "    ?movieid movie3:is_num_user_for_reviews ?numusers.\n"
				+ "    ?movieid movie3:is_num_voted_users ?numvoted.\n"
				+ "  FILTER (?imdbscore>= "+ "'"+minrating +"'"+")\n"
				+ "  \n"
				+ "}\n"
				+ "LIMIT 25";
	}
	
	public static String get_movie_rating_gt(String minrating) {
		return "PREFIX movie3: <http://www.semanticweb.org/imdb/movie3.owl#>\n"
				+ "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>\n"
				+ "PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>\n"
				+ "PREFIX owl: <http://www.w3.org/2002/07/owl#>\n"
				+ "PREFIX xsd: <http://www.w3.org/2001/XMLSchema#>\n"
				+ "PREFIX movie: <http://www.semanticweb.org/imdb/mve.owl/>\n"
				+ "\n"
				+ "SELECT ?mid1 ?imdblink  ?imdbscore ?numusers ?numvoted\n"
				+ "  WHERE {\n"
				+ "    ?movieid movie3:is_id ?mid1.\n"
				+ "    ?movieid movie3:is_imdb_link ?imdblink.\n"
				+ "    ?movieid movie3:is_imdb_score ?imdbscore.\n"
				+ "    ?movieid movie3:is_num_user_for_reviews ?numusers.\n"
				+ "    ?movieid movie3:is_num_voted_users ?numvoted.\n"
				+ "  FILTER (?imdbscore>= "+ "'"+minrating +"'"+")\n"
				+ "  \n"
				+ "}\n"
				+ "LIMIT 25";
	}
	
	
	
	

}
