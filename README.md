# Bonify coding challenge

"You have a user database and multiple external news servers. Each news server serves a region, e.g. Berlin, Hamburg, Munich.
At login the user gets data from the news channels he subscribed to. Apart from that, channels are not personalized. 
Since there is a constant data stream from different sources, the system needs to be asynchronous by nature.
Additionally the users should be notified, when new news arrive on a channel they registered on."

# Synopsis

The project was created as a Maven project using the Spring Boot framework for RESTful Web service implementation. 
Data is stored in a MySQL database, which has its model and generation script included.
The implementation consists of a backend service which receives the news articles as input JSON files via REST, and then saves them to the database. Each user is subscribed to a server, or news repository, and one news repository can have many subscribers. The articles are bound to a server in the database. 
The resulting articles can be passed as JSON data to the front-end via simple REST requests. 
Asynchronous functionality is implemented via the CompletableFuture interface in the service classes and Async property of the Spring framework.
Push notifications or email sending are simulated via Aspects, to be triggered upon execution of an Article POST method, thus simulating a subscription notification. 

# Testing

Testing was done with the integrated Tomcat server and Postman standalone app.

# Built with

* Eclipse Neon
* Apache Tomcat 8.0
* Apache Maven
* Spring Boot
* Spring JPA
