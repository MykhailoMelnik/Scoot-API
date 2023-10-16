
# Scoot API Project

The Scoot API Project is a web service designed to calculate the distance
between two points on Earth based on their coordinates using the Haversine formula. 
It also includes rate limiting for users.

**Prerequisites**

Before you begin, ensure that you have the following components installed:
Docker: Docker [Installation](https://www.docker.com/get-started) 


**Installation Instructions**

1. Clone the Scoot API repository to your local machine.
2. Navigate to the project directory.
cd scoot-api

3. Edit the docker-compose.yml and init.sql files as needed to configure your MySQL database.
4. Start Docker Compose to create the container environment.
   docker-compose up -d
  
 **Usage**

Ensure that the MySQL service is running inside the Docker container.
Start the Scoot API service.
Make an HTTP POST request to 
/api/v1/distance, sending data in JSON format, and include the X-Api-Key header with the user's key.
In default(X-Api-Key = "X-Api-Key", key = "key1").

JSON BODY:
`{
"latitude1": 52.5200,
"longitude1": 13.4050,
"latitude2": 48.8566,
"longitude2": 2.3522
}`


**Authors**

Melnik Mikhaylo

