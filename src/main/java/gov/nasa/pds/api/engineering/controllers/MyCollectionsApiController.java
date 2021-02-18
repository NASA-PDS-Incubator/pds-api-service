package gov.nasa.pds.api.engineering.controllers;


import gov.nasa.pds.api.base.CollectionsApi;

import gov.nasa.pds.model.Products;
import gov.nasa.pds.model.Summary;
import gov.nasa.pds.model.Product;
import gov.nasa.pds.model.ErrorMessage;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.annotations.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.*;
import javax.validation.Valid;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;
import java.util.Arrays;
import java.util.Map;


@Controller
public class MyCollectionsApiController implements CollectionsApi {

    private static final Logger log = LoggerFactory.getLogger(MyCollectionsApiController.class);

    private final ObjectMapper objectMapper;

    private final HttpServletRequest request;
    
 
    public MyCollectionsApiController(ObjectMapper objectMapper, HttpServletRequest request) {
        this.objectMapper = objectMapper;
        this.request = request;
    }
    
    public ResponseEntity<Product> collectionsByLidvid(@ApiParam(value = "lidvid (urn)",required=true) @PathVariable("lidvid") String lidvid
    		) {
    		        String accept = request.getHeader("Accept");
    		        if (accept != null && accept.contains("application/json")) {
    		            try {
    		                return new ResponseEntity<Product>(objectMapper.readValue("{\n  \"stop_date_time\" : \"stop_date_time\",\n  \"observing_system_components\" : [ null, null ],\n  \"metadata\" : {\n    \"creation_date_time\" : \"creation_date_time\",\n    \"version\" : \"version\",\n    \"update_date_time\" : \"update_date_time\",\n    \"label_url\" : \"label_url\"\n  },\n  \"description\" : \"description\",\n  \"investigations\" : [ {\n    \"ref\" : \"ref\",\n    \"description\" : \"description\",\n    \"title\" : \"title\",\n    \"type\" : \"type\"\n  }, {\n    \"ref\" : \"ref\",\n    \"description\" : \"description\",\n    \"title\" : \"title\",\n    \"type\" : \"type\"\n  } ],\n  \"id\" : \"id\",\n  \"type\" : \"type\",\n  \"title\" : \"title\",\n  \"start_date_time\" : \"start_date_time\",\n  \"targets\" : [ null, null ],\n  \"properties\" : {\n    \"key\" : { }\n  }\n}", Product.class), HttpStatus.NOT_IMPLEMENTED);
    		            } catch (IOException e) {
    		                log.error("Couldn't serialize response for content type application/json", e);
    		                return new ResponseEntity<Product>(HttpStatus.INTERNAL_SERVER_ERROR);
    		            }
    		        }

    		        return new ResponseEntity<Product>(HttpStatus.NOT_IMPLEMENTED);
    }


    public ResponseEntity<Products> getCollection(@ApiParam(value = "offset in matching result list, for pagination", defaultValue = "0") @Valid @RequestParam(value = "start", required = false, defaultValue="0") Integer start
    		,@ApiParam(value = "maximum number of matching results returned, for pagination", defaultValue = "100") @Valid @RequestParam(value = "limit", required = false, defaultValue="100") Integer limit
    		,@ApiParam(value = "search query, complex query uses eq,ne,gt,ge,lt,le,(,),not,and,or. Properties are named as in 'properties' attributes, literals are strings between \" or numbers. Detailed query specification is available at https://bit.ly/393i1af") @Valid @RequestParam(value = "q", required = false) String q
    		,@ApiParam(value = "returned fields, syntax field0,field1") @Valid @RequestParam(value = "fields", required = false) List<String> fields
    		,@ApiParam(value = "sort results, syntax asc(field0),desc(field1)") @Valid @RequestParam(value = "sort", required = false) List<String> sort
    		,@ApiParam(value = "only return the summary, useful to get the list of available properties", defaultValue = "false") @Valid @RequestParam(value = "only-summary", required = false, defaultValue="false") Boolean onlySummary
    		) {
    	String accept = request.getHeader("Accept");
        
        // Example of implementation of a controller that one can use to implement the PDS API
        
        log.info("accept value is " + accept);
        if (accept != null 
        		&& (accept.contains("application/json") || accept.contains("text/html"))) {
            	
        	Products collections = new Products();
        	
        	Summary summary = new Summary();
        	
        	summary.setQ("");
        	summary.setStart(0);
        	summary.setLimit(100);
        	List<String> sortFields = Arrays.asList();
        	summary.setSort(sortFields);
        	
        	collections.setSummary(summary);
        	
        	Product collection = new Product();
        	collection.id("urn:nasa:pds:orex.ocams:data_raw");
        	collection.title("OSIRIS-REx OCAMS raw science image data products");
        	collection.description("This collection contains the raw (processing level 0) science image data products produced by the OCAMS instrument onboard the OSIRIS-REx spacecraft.");
        	
        	collections.addDataItem(collection);
        	
        			
        	
            return new ResponseEntity<Products>(collections, HttpStatus.OK);
        
        }
        else return new ResponseEntity<Products>(HttpStatus.NOT_IMPLEMENTED);
    }

}