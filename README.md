# SER531-Group4-Project

- Two of fuseki servers are deployed on google cloud instances and other is running on AWS Instance.
- Please ensure all the three Fuseki servers are up and running hosting Data triples.


## Setting up the Backend Server:

- This is a maven project. Please refer to :  Maven Installation -> https://maven.apache.org/install.html
  for  detailed steps of maven installation.

### Steps for building jar (server):
- Clone the git repo  
- Run mvn clean install at the root path of project (path: Backend/SparQL)
- A folder called target is generated at root path, and jar naming SparQL-0.0.1-SNAPSHOT.jar will be created 


### Running the Jar:
 Please use the following command to start server from the root path
 
 ```
     java -jar target/SparQL-0.0.1-SNAPSHOT.jar
```

- The server will be running on  the port 8080


## Setting up front end goes here
-  --
