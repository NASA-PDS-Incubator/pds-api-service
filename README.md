# PDS-API-SERVICE

## Overview

This project is a template implementation of the PDS federated API using the standard libraries automatically generated from the swagger definition of the API (see repository https://github.com/nasa-pds/pds-api-core/).

It implements a very simple collections end-point complying with the specification (see https://app.swaggerhub.com/organizations/PDS_APIs)

Update controllers as shown as an example in `src/main/java/gov/nasa/pds/api/engineering/controllers/MyCollectionsApiController.java`


## Deployment

If needed change port in `src/main/resources/applications.properties`


    mvn clean
    mvn install
    mvn spring-boot:run
    
    
## Usage

Go to rest api documentation:

    http://localhost:8080/
    
    
Test the simple collection end-point:

    http://localhost:8080/collections
    
## Demo server

Demo server is moved to the actual API implementation built on top of the PDS registry, see https://github.com/NASA-PDS/registry-api-service
    

