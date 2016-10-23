package controllers;

import java.util.*;



import play.cache.Cache;
import play.mvc.BodyParser;
import play.mvc.Controller;
import play.mvc.Result;
import java.util.Collections;
import views.html.index;
import play.Logger;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.*;
import com.fasterxml.jackson.core.ObjectCodec;

/**
 * Application controller.
 */
public class Application extends Controller {

    public static Result index() {
        return ok(index.render("Your new application is ready."));
    }

     * Returns all the data
     * 
     * @return a list of dataObjects.
     */
    public static Result getAllData() {
    	try{
    	List<Insteract> insteract = (List<Insteract>) Cache.get("insteract");
    	ObjectMapper mapper = new ObjectMapper();
    	JsonNode node;
    	node = mapper.convertValue(insteract, JsonNode.class);
    	return ok(node);
    	}catch (Throwable t) {
    	  Logger.error("Exception with getAllData", t);
    	  ObjectNode objectNode = returnErrorMessage("Error",0,"Reason Unknown");
      	  return ok(objectNode);
    	}
    }
    

}
