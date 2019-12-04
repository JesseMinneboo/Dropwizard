[![LinkedIn][linkedin-shield]][linkedin-url]



<!-- PROJECT LOGO -->
<br />
<p align="center">
  <a href="https://github.com/JesseMinneboo/Dropwizard">
    <img src="https://adfs.hsleiden.nl/adfs/portal/logo/logo.jpg?id=45F39A10B144CA81073A73EA67131C55FC9ED34BC069E4D2CE6424F67E9D7877" alt="Logo" width="350" height="125">
  </a>

  <h3 align="center">A Dropwizard Back-end</h3>

  <p align="center">
    A Dropwizard RESTful API for a webshop in Angular.
    <br />
    <a href="https://github.com/othneildrew/Dropwizard"><strong>Explore the docs »</strong></a>
    <br />
    <br />
    <a href="https://github.com/JesseMinneboo/Dropwizard">View Demo</a>
    ·
    <a href="https://github.com/JesseMinneboo/Dropwizard/issues">Report Bug</a>
    ·
    <a href="https://github.com/JesseMinneboo/Dropwizard/issues">Request Feature</a>
  </p>
</p>



<!-- TABLE OF CONTENTS -->
## Table of Contents

* [About the Project](#about-the-project)
* [Getting Started](#getting-started)
* [Roadmap](#roadmap)
* [License](#license)
* [Contact](#contact)
* [Acknowledgements](#acknowledgements)



<!-- ABOUT THE PROJECT -->
## About The Project

For a subject at my Universiy we were asked to build a webshop. Here can buy products (obviously), login, register, logout and have some kind of shopping cart.

A list of commonly used resources that I find helpful are listed in the acknowledgements.

### Built With
This RESTful API is build with the following:
* [Dropwizard](https://www.dropwizard.io/en/stable/)
* [Java](https://www.java.com/nl/download/)
* [Intellij IDEA](https://www.jetbrains.com/idea/)
* [Maven](https://maven.apache.org/)



<!-- GETTING STARTED -->
## Getting Started

Before you can run the API there are a few steps you need to do

### Prerequisites

If you would like to run the project follow the steps below

* npm
```sh
npm install npm@latest -g
```
* download Postman (optional)
```sh
https://www.getpostman.com/
```
* download Xampp or use your own MySql server
```sh
https://www.apachefriends.org/index.html
```
* MYSQL database: Update the main.yml if your configuration differs. In this example, we use
```sh
database: database_name
user: database_user
pass: database_password
```


### Installation

1. Clone the repo
```sh
git clone https://github.com/JesseMinneboo/Dropwizard.git
```
2. Install NPM packages if needed
```sh
npm install
```


### Build
* Compiling
```sh
mvn clean package
```


* Creating the database
```sh
java -jar target/PROJECTNAME.jar db migrate main.yml
```


* Run
```sh
java -jar target/PROJECTNAME.jar server main.yml
```


* Open API in browser
```sh
http://localhost:9000
```


<!-- ROADMAP -->
## Roadmap

See the [open issues](https://github.com/JesseMinneboo/Dropwizard/issues) for a list of proposed features (and known issues).


<!-- LICENSE -->
## License

Distributed under the MIT License. See `LICENSE` for more information.


<!-- CONTACT -->
## Contact

Jesse Minneboo - [My LinkedIn](https://www.linkedin.com/in/jesseminneboo) - info@minnict.nl

Project Link: [https://github.com/JesseMinneboo/Dropwizard](https://github.com/JesseMinneboo/Dropwizard)



<!-- ACKNOWLEDGEMENTS -->
## Acknowledgements
* [Img Shields](https://shields.io)


<!-- MARKDOWN LINKS & IMAGES -->
[linkedin-shield]: https://img.shields.io/badge/-LinkedIn-black.svg?style=flat-square&logo=linkedin&colorB=555
[linkedin-url]: https://linkedin.com/in/JesseMinneboo
