# wb-eos
wb-eos test


GIT location:
=============

https://github.com/gmmehta/wb-eos


NOTES:
======

I have used SpringBoot to expose an endpoint to get the venus for a given place

I have gone with the simple n-tier architecture and layers are separated using Interfaces 

(controller) -->> (service) -->> (repository)

I have decided to use RestTemplate to call FourSquare API. 
When the response is returned I have used JsonPath to only extract the "name" property from venues object 
and not the whole object. 

I could have extracted the whole object and mapped it to an internal domain object and expose this in turn.

Tests:
======

There is a MOCK test for the service

controller test -->> have decided to use RestAssured for validation of REST (http://rest-assured.io/)

repository test -->> simple SpringBoot test


To execute this:
================

Option 1. Simply import it into IDE and run it.

Option 2. Run it from command line by going into the directory and running
  
  mvn clean package
  java -jar target/wbeos-0.0.1-SNAPSHOT.jar


AngularJS Page:
===============

After submitting the code to git, I made some time to do this. 

Open the file src/main/resources/static/index.htm

When the screen displays type in the place e.g. 'holborn' and click Search.


If this doen't work then try postman.


Use Postman:
============

REQUEST (GET) --> http://localhost:8080/api/venues/nearby/holborn

RESPONSE:

{
    "status": "OK",
    "error": null,
    "data": {
        "venues": [
            "National Gallery",
            "British Museum",
            "Somerset House",
            "Dishoom",
            "The Lyceum Theatre",
            "Royal Opera House",
            "Corinthia Hotel",
            "Ronnie Scott's Jazz Club",
            "Apex Temple Court Hotel",
            "Hoppers",
            "Stanfords",
            "Trafalgar Square",
            "Gordon's Wine Bar",
            "National Theatre",
            "Homeslice",
            "Barrafina",
            "Amorino",
            "Hakkasan",
            "Scarfes Bar",
            "Hawksmoor Seven Dials",
            "The Zetter Townhouse",
            "Foyles",
            "Covent Garden Market",
            "National Portrait Gallery",
            "Monmouth Coffee Company",
            "Soho House",
            "Victoria Embankment Gardens",
            "Seven Dials",
            "Ye Olde Mitre Tavern",
            "Apple Covent Garden"
        ]
    }
}