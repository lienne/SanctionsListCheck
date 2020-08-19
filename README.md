# Sanctions Screening

Sanctions Screening is a simple rest endpoint that receives a GET request with a name to be matched against a pre-set Sanctions list.
If the name provided matches by more than or equal to 75% of any item in the Sanctions list, the program returns a Hit.
This service uses Spring Boot and JUnit for unit testing.


## Execution Instructions

1. Extract the zip file and load it in IntelliJ or your IDE of choice.
    - JDK 11 or higher needed.
    - Make sure you have Maven installed and in your path variable.
2. Open IntelliJ command line and run:
    `mvn clean install.`
    `java -jar target/sanctions-0.0.1-SNAPSHOT.jar`
3. On your browser, go to [http://localhost:8080/screen?input=KristopherDoe]
4. Test cases can be written directly into the URL after `input=`, including spaces.
5. Additional information, such as Levenshtein Distance Score and percentage match, is printed directly
    to terminal.


## Unit Testing Instructions

1. On your IDE, open the file:

	`sanctions > src > test >java > com.screening.sanctions > ScreeningControllerTest.java`

2.  Run the test file to run all tests


## Troubleshooting

#### Error: Port is in use
`Web server failed to start. Port 8080 was already in use.`

1. Go to sanctions > src > main > resources > application.properties
2. Add a line to change your port number, like so:
		`server.port=9090`
3. Alternatively, clear any program currently using your port.