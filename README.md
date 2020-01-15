IPRWC 2: A hsleiden project
=============================

### Pre-requisites 
If you would like to run the project follow the steps below.

MYSQL database: Update the main.yml if your configuration differs. In this example, we use

	database: database_name
	user: database_user
	pass: database_password

### Build:

	mvn clean package
	

### Database creation:

	java -jar target/dropwizard-1.0.0.jar db migrate main.yml
	
### Database deletion:

	java -jar target/dropwizard-1.0.0.jar db drop-all --confirm-delete-everything main.yml
	
### Run:
    
    java -jar target/dropwizard-1.0.0.jar server main.yml
	
### Watch changes
    
    mvn fizzed-watcher:run
	
	
### Open browser pointing at

	http://localhost:9000

