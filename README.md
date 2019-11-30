IPSEN2 Java Backend Project
=============================

### Case 
This project was a required assignment for my second year Software Engineering Bachelors degree.

The case was to make a CRUD backend for uploading student projects. It was required to use Dropwizard, a Java backend framework.

For project reasons we used the waterfall method for developing this software, to understand why it's not used anymore in software development anymore.

A java backend for this case is total overkill in my opinion, there are far more better solutions to solve this case.  
#


### Pre-requisites 
If u would like to run the project follow the steps below.

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

