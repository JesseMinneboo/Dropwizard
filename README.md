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
	
	
### Run:
    
    * if using a intelligent IDE u should configure the 
        <MainService> class 
    as the running class.
    
	* if not run -> java -jar target/dropwizard-1.0.0.jar server main.yml
	
### Watch changes
    
    mvn fizzed-watcher:run
	
	
### Open browser pointing at

	http://localhost:9000

