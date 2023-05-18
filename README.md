# EKAASAA - (GraphQL + Spring Boot + WebFlux + Redis)

- Checkout the code.
- Resolve maven dependency.
- Execute maven clean install 

## Start Redis Server

- Go to the location /redis
- Run redis-server.exe file.
- As per the below screen window will open that confirm your redis server 

<img width="835" alt="Redis-Server-Running" src="https://github.com/dwivna/ekaasaa/assets/22236663/af45db82-3eae-4092-b7d7-bac598904432">

## Start Spring Boot Application

- Run EkaasaaApplication.java as spring boot application.
- Application will start running on port 8080

## Open GraphQL Endpoint

- Open graphql endpoint http://localhost:8080/graphiql?path=/graphql from your browser
- Refer below image for execting query on graphql

<img width="924" alt="Graphql-Sample-Query" src="https://github.com/dwivna/ekaasaa/assets/22236663/fa0d8aa3-9550-400a-bcf1-d3ab002fed7b">

## Docker Integration

- Install Docker Desktop
- Execute below command to pull latest images
  ```
   docker pull redis
   docker pull openjdk:21-slim
  
  ```
- Execute below command to run redis server on docker
  ```
   docker run --rm -p 6379:6379 -d --name redis-es1 redis redis-server
  ```
- Execute below command to create network on docker and connect it with redis container
  ```
   docker network create spring-redis-network
   docker network connect spring-redis-network redis-es1
  ``` 
- Change ip address in application-docker.yml as shown below
  ```
   redis:
     host: localhost #change it to your system ip
  ```
- Execute below command to build the application and create image of service
  ```
   mvn clean install
   docker build -t ekaasaa-service .
  ```    
- Execute below command to start service in a container
  ```
   docker run -p 8082:8082 -it --rm --name ekaasaa ekaasaa-service
  ``` 

## Contact Me

- For contribution/suggestion/information contact me @dwivna
