package controllers;

import java.util.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import model.Insteract;
import model.Data;
import model.Item;
import model.ListI;
import model.GraphData;
import model.InitialValue;
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
public class LambdaApplication extends Controller{
	public static Result getInitialData() {
	      try{
	    	List<Insteract> insteract = (List<Insteract>) Cache.get("insteract");
	    	int totalRecords = insteract.size();
	    	int customerCount = 0;
	    	int totalValue = 0;
	    	HashSet noDupSet = new HashSet();
	    	insteract.forEach(tmpElement->{
	    		Data data = tmpElement.getData();
	    		ListI list = data.getList();
	    		Item item = list.getItem();
	    		List<Integer> suggested = item.getSuggested(); 
	    		noDupSet.add(tmpElement.getCompanyId());
	    		totalValue = totalValue + suggested.get(0);
	    	});
	    	customerCount = noDupSet.size();
	    	InitialValue initialValue = new InitialValue();
	    	initialValue.setCustomerCount(customerCount);
	    	initialValue.setTotalValue(totalValue);
	    	initialValue.setTotalRecords(totalRecords);
	    	ObjectMapper mapper = new ObjectMapper();
	    	JsonNode node;
	    	node = mapper.convertValue(initialValue, JsonNode.class);
	    	return ok(node);
	      }
	      catch (Throwable t) {
	    	  Logger.error("Exception with getInitialData", t);
	    	  ObjectNode objectNode = returnErrorMessage("Error",0,"Reason Unknown");
	      	  return ok(objectNode);
	    	}
	    }
	public static ObjectNode returnErrorMessage(String type,int code,String message){
    	ObjectMapper mapper = new ObjectMapper();
  	  	ObjectNode objectNode = mapper.createObjectNode();
        objectNode.put("Type", type);
        objectNode.put("Code",code);
        objectNode.put("Message",message);
        return objectNode;
    }
}
