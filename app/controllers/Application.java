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

/**
 * Application controller.
 */
public class Application extends Controller {

    public static Result index() {
        return ok(index.render("Your new application is ready."));
    }

    /**
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
    public static Result getInitialData() {
      try{
    	List<Insteract> insteract = (List<Insteract>) Cache.get("insteract");
    	int totalRecords = insteract.size();
    	int customerCount = 0;
    	int totalValue = 0;
    	HashSet noDupSet = new HashSet();
    	for (Insteract tmpElement : insteract) {
    		String companyId = tmpElement.getCompanyId();
    		Data data = tmpElement.getData();
    		ListI list = data.getList();
    		Item item = list.getItem();
    		List<Integer> suggested = item.getSuggested(); 
    		noDupSet.add(companyId);
    		totalValue = totalValue + suggested.get(0);
    		}
    	customerCount = noDupSet.size();
    	InitialValue initialValue = new InitialValue();
    	initialValue.setCustomerCount(customerCount);
    	initialValue.setTotalValue(totalValue);
    	initialValue.setTotalRecords(totalRecords);
    	ObjectMapper mapper = new ObjectMapper();
    	JsonNode node;
    	node = mapper.convertValue(initialValue, JsonNode.class);
    	return ok(node);
      }catch (Throwable t) {
    	  Logger.error("Exception with getInitialData", t);
    	  ObjectNode objectNode = returnErrorMessage("Error",0,"Reason Unknown");
      	  return ok(objectNode);
    	}
    }
    public static Result getGraphData() {
    	try{
        List<Insteract> insteract = (List<Insteract>) Cache.get("insteract");
        HashMap<String, Integer> hmapForUniqueRecords = new HashMap<String, Integer>();
    	for (Insteract tmpElement : insteract) {
    		String dateIs = tmpElement.getCreated();
    		Integer recordCountFromHmap = hmapForUniqueRecords.get(dateIs);
    		Logger.info("recordCountFromHmap :: " + recordCountFromHmap + " for date ::" + dateIs );
    		if (recordCountFromHmap == null){
    			recordCountFromHmap = 1;
    		}
    		else{
    			recordCountFromHmap = recordCountFromHmap + 1;
    		}
    		hmapForUniqueRecords.put(dateIs,recordCountFromHmap);
    	}
    	ObjectMapper mapper = new ObjectMapper();
    	String json = new ObjectMapper().writeValueAsString(hmapForUniqueRecords);
    	return ok(json);
    	}catch (Throwable t) {
      	  Logger.error("Exception with getGraphData", t);
      	  ObjectNode objectNode = returnErrorMessage("Error",0,"Reason Unknown");
        	  return ok(objectNode);
      	}
    }
    public static Result getSortedData(String order,String objectId)throws JsonProcessingException{
    	try{
    	List<Insteract> insteract = (List<Insteract>) Cache.get("insteract");
    	int pageSize  = 10;
    	int nonOrderFlag = -1;
    	if (order.equals("ASC")){
    		nonOrderFlag = 1;  		
     	}
    	final int orderFlag = nonOrderFlag;
    	Collections.sort(insteract, new Comparator<Insteract>() {
            @Override
            public int compare(Insteract lhs, Insteract rhs) {
            	try{
            	DateFormat iso8601 = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
            	Date dateLhsTime = iso8601.parse(lhs.getCreated());
            	Date dateRhsTime = iso8601.parse(rhs.getCreated());
            	long lhsTime = dateLhsTime.getTime();
            	long rhsTime = dateRhsTime.getTime();
            	if (lhsTime > rhsTime)
            		return -1 * orderFlag;
            	else if (lhsTime < rhsTime)
            		return 1 * orderFlag;
            	else
            		return 0;
            	}catch(Exception ex){
            		Logger.info(ex.getMessage());
           		 	return 0;
            	}
            }
        });
    	int index = 0;
    	if (objectId != null){
    	for (Insteract tmpElement : insteract) {
    		if (tmpElement.getId().equals(objectId)) {
    			Logger.info("Element found at position :: " + index);
    			break;
    		}
    		index++;
    	}
    	}
    	//now use a subList to getData
    	int sizeOfInsteract = insteract.size();
    	int fromIndex = index;
    	int toIndex = fromIndex + pageSize;
    	//for last pageSizeElements
    	if (toIndex > sizeOfInsteract){
    		toIndex = sizeOfInsteract;
    	}
    	List<Insteract> insteractNew = insteract.subList(fromIndex, toIndex);
    	ObjectMapper mapper = new ObjectMapper();
    	JsonNode node;
    	node = mapper.convertValue(insteractNew, JsonNode.class);
    	return ok(node);
    	}catch (Throwable t) {
        	  Logger.error("Exception with getSortedData", t);
        	  ObjectNode objectNode = returnErrorMessage("Error",0,"Reason Unknown");
          	  return ok(objectNode);
        	}
    }

    public static Result getAllSortedData(String order)throws JsonProcessingException{
    	try{
    	List<Insteract> insteract = (List<Insteract>) Cache.get("insteract");
    	Collections.sort(insteract, new Comparator<Insteract>() {
            @Override
            public int compare(Insteract lhs, Insteract rhs) {
            	try {
            	DateFormat iso8601 = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
            	Date dateLhsTime = iso8601.parse(lhs.getCreated());
            	Date dateRhsTime = iso8601.parse(rhs.getCreated());
            	long lhsTime = dateLhsTime.getTime();
            	long rhsTime = dateRhsTime.getTime();
            	if (order.equals("ASC")){
            	if (lhsTime > rhsTime)
            		return -1;
            	else if (lhsTime < rhsTime)
            		return 1;
            	else
            		return 0;
            	}
            	else if (order.equals("DESC")){
            		if (lhsTime > rhsTime)
                		return 1;
                	else if (lhsTime < rhsTime)
                		return -1;
                	else
                		return 0;
                	}
            	else{
            		return 0;
            	}
            }
            	catch (Exception e) {
        		 Logger.info(e.getMessage());
        		 return 0;
        		}
            }
        });
    	
    	
    	ObjectMapper mapper = new ObjectMapper();
    	JsonNode node;
    	node = mapper.convertValue(insteract, JsonNode.class);
    	return ok(node);
    	
    }catch (Throwable t) {
    	  Logger.error("Exception with getAllSortedData", t);
    	  ObjectNode objectNode = returnErrorMessage("Error",0,"Reason Unknown");
      	  return ok(objectNode);
    	}
    }
    public static Result getObject(String objectId)throws JsonProcessingException{
    	try{
    	List<Insteract> insteract = (List<Insteract>) Cache.get("insteract");
        Insteract singleInsteract = new Insteract();
        for(Insteract tmpElement:insteract){
        	String objId = tmpElement.getId();
        	if (objId.equals(objectId)){
        		Logger.info("Element found with objectId ::  " + objectId);
        		singleInsteract = tmpElement;
        		break;
        	}
        }
        ObjectMapper mapper = new ObjectMapper();
    	JsonNode node;
    	node = mapper.convertValue(singleInsteract, JsonNode.class);
    	return ok(node);
    	
    }catch (Throwable t) {
  	  Logger.error("Exception with getObject", t);
  	  ObjectNode objectNode = returnErrorMessage("Error",0,"Reason Unknown");
    	  return ok(objectNode);
  	}
    }

    /**
     * Adds a new object to the cache.
     * 
     * @return HTTP 201, if the object was added to the cache insteract. 
     * @throws JsonProcessingException
     */
    @BodyParser.Of(BodyParser.Json.class)
    public static Result addData() throws JsonProcessingException {
    	try{
    	JsonNode node = request().body().asJson();
    	ObjectMapper mapper = new ObjectMapper();
        Insteract newInsteract = mapper.treeToValue(node, Insteract.class);
        
        // id cannot be null 
        if (newInsteract.getId() == null) {
        	return badRequest("Invalid insteract data");
        }
        
        // check if a data with same id  already exists
        List<Insteract> insteract = (List<Insteract>) Cache.get("insteract");
        for(Insteract tmpObject : insteract) {
        	Logger.info("inside for loop");
        	Logger.info("tmpObj :: " + tmpObject.getId());
        	Logger.info("new Insteract :: " + newInsteract.getId());
        	if(tmpObject.getId().equals(newInsteract.getId())) {
        		Logger.info("Already exists !!! objectId :: " + tmpObject.getId());
        		ObjectNode objectNode1 = mapper.createObjectNode();
                objectNode1.put("Type", "Failure");
                objectNode1.put("Code",0);
                objectNode1.put("id",newInsteract.getId());
                objectNode1.put("message","Id conflict");
        		return ok(objectNode1);
        	}
        }
        
        insteract.add(newInsteract);
        Cache.set("insteract", insteract, 0);
        ObjectNode objectNode1 = mapper.createObjectNode();
        objectNode1.put("Type", "Success");
        objectNode1.put("Code",1);
        objectNode1.put("id",newInsteract.getId());
    	return ok(objectNode1);
    	}catch (Throwable t) {
    	  	  Logger.error("Exception with addData", t);
    	  	  ObjectNode objectNode = returnErrorMessage("Error",0,"Reason Unknown");
    	    	  return ok(objectNode);
    	  	}
    	
    }

    /**
     * Updates the insteract object that matches the specified objectId.
     * 
     * @param objectId
     * 			id of object.
     * 
     * @return HTTP 200, if the object was updated.
     * @throws JsonProcessingException
     */
    @BodyParser.Of(BodyParser.Json.class)
    public static Result updateData(String objectId) throws JsonProcessingException {
    	try{
    	JsonNode node = request().body().asJson();
    	ObjectMapper mapper = new ObjectMapper();
    	Insteract newInsteract = mapper.treeToValue(node, Insteract.class);
    	// id can not be empty
    	if (objectId == null) {
    		return badRequest("Invalid insteract data.");
    	}

    	// check if the objectId exists
    	boolean found = false;
    	List<Insteract> insteract = (List<Insteract>) Cache.get("insteract");
    		for(Insteract tmpObject : insteract) {
    			if(tmpObject.getId().equals(newInsteract.getId())) {
    				insteract.remove(tmpObject);
    				found = true;
    				break;
    			}
    		}

    		if(!found) {
    			ObjectNode objectNode1 = mapper.createObjectNode();
                objectNode1.put("Type", "Failure");
                objectNode1.put("Code",0);
                objectNode1.put("id",newInsteract.getId());
                objectNode1.put("message","Object Not Found");
        		return ok(objectNode1);
    		} else {
    			insteract.add(newInsteract);
    			Cache.set("insteract", insteract, 0);
    			ObjectNode objectNode1 = mapper.createObjectNode();
                objectNode1.put("Type", "Success");
                objectNode1.put("Code",1);
                objectNode1.put("id",newInsteract.getId());
                objectNode1.put("message","Update successfull");
        		return ok(objectNode1);
    			
    		}
    	}catch (Throwable t) {
  	  	  Logger.error("Exception with updateData", t);
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
