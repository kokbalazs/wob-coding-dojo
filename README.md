#Getting Started

This Spring Batch application reads a .csv file and uploads its contents to a Postgres database.

Requirements 
* Java 11 
* Docker 
* docker-compose

###Running application on your local machine Start the database on your local machine and then you simply will able to debug / run the code.

Start docker-compose to init database
* docker-compose up -d
  
If you want to purge the database
* docker-compose down -v

If you simply want to stop the database
* docker-compose down