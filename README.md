# simple-game-service
This is very simple service that returns an id and text for a game that is requested in the 'name' query parameter.
e.g. http://localhost:8080/game?name=Chess should return json containing following fields:
{"id":some number,"text":"some string"}

If there is no query parameter then the game endpoint by default sets game to Sudoku, e.g.  http://localhost:8080/game should return json containing following fields:
{"id":some number,"text":"some string"}

id is a counter that increases by a certain amount every time a request is made to the server.

Prerequisites:
1. Git
2. Java 8
3. Access to commandline to run commands.
4. Internet connection to download dependencies.
5. An IDE such as Eclipse.

Steps:
1. Clone the repo.
2. Use a build tool such as maven or gradle (binaries are provided in the repo for help but its not necessary to use them) to produce a jar file. 
3. Run the jar using java cmd. e.g. java -jar target/simple-game-service-0.1.0.jar
4. Access the server using an http client. 

Tasks:
1. Create a branch using Git.
2. Write the follwong functional tests in your favorite programming language :
    1. Find out the text that is displayed when the game endpoint is accessed without a query parameter. Write a test to verify that this text is displayed when the game endpoint is accessed without a query parameter.  
    2. Find out the id after hitting game endpoint 7 times. If id increases in a certain pattern, write a test to verify that the game endpoint follows the pattern you have determined.
    3. Find out the text that is displayed when game endpoint is accessed with a name query parameter. Write a test to verify that this text is displayed when the game endpoint is accessed with the name query parameter.
3. Open a pull request for code review.
