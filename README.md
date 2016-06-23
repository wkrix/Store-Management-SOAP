# Store-Management-SOAP
The Store Management web application is modified into SOAP web service with Apache CXF.

Summary
-------
The primary goal of this project is to make a fully functioned SOAP web service with client and server side along with Spring Boot.
The initial application was my previous project: [Store-Management](https://github.com/wkrix/Store-Management). 


Installation
------------

To install the archetype in your local repository execute following commands:

```bash
    git clone https://github.com/wkrix/Store-Management-SOAP.git
    cd Store-Management-SOAP
    mvn clean install
```

Run the project
----------------

```bash
	java -jar Service/target/store-management-soap-service.war
	
	java -jar Client/target/store-management-soap-client.war --server.port=8081
```
*They will run on the embedded tomcat, but you could easily deploy them on your favorite web container too.The server side will use an embedded hsql database but you could easily change it in application.properties into your own postgresql database.

Test on the browser
-------------------

    Client:
	http://localhost:8081/
	
	Server:
	http://localhost:8080/ws 
