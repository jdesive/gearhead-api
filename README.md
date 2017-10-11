# GearHead API
Backend REST API to store data for the GearHead frontend.

Documentation at [**`http://localhost:8080/swagger-ui.html`**](http://localhost:8080/swagger-ui.html).

## Installation

### Prerequisites
* Apache Maven - [download](https://maven.apache.org/download.cgi) - [website](https://maven.apache.org/)
* Java Development Kit 8^ - [download](http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html) - [website](http://www.oracle.com/technetwork/java/javase/downloads/index.html)
* Docker *(Optional)* - [download](https://github.com/jdesive/docker_help/tree/master/Install) - [website](https://www.docker.com/)
* Git - [download](https://git-scm.com/downloads) - [website](https://git-scm.com/)

For Linux users, run all commands sith `sudo`

1. Clone the source repository to your local machine `git clone https://github.com/jdesive/gearhead-api.git`

    *NOTE: If you are planning on deploying in Docker, make sure you clone to the Docker host machine*
2. Navigate to the `gearhear-api` folder that was created
3. Run the `mvnw package` command from the root directory  

    *NOTE: You might have to change the permissions on the `mvnw` file*  
    
    *NOTE: If you are deploying to Docker, make sure you are signed in under a user with permission for Dockers commands.*  
    
    *NOTE: If you are deploying to Docker, you can append `docker:build` to the end of the command to build the image automatically(`mvnw package docker:build` Skip step #4)*
4. Run the `java -jar target/gearhead-api-0.0.1-SNAPSHOT.jar` from the root directory to start the application
 
 
5. (Optional) To run the docker image you can run this command `docker run -d -p "8080:8080" gearhead-api`