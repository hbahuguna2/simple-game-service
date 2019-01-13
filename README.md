# simple-game-service
This is very simple service that returns an id and text for a game that is requested in the 'name' query parameter.
e.g. http://localhost:8080/game?name=Chess should return the following json:
{"id":1,"text":"Playing Chess is fun!"}

If there is no query parameter then it returns Sudoku by default, e.g.  http://localhost:8080/game should return the following json:
{"id":1,"text":"Playing Sudoku is fun!"}

id is a counter that increases by 1 every time a request is made to the server.

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
    a. Verify that the text is "Playing Sudoku is fun!", when no query parameter is provided.
    b. Verify that the id increases by 1 every time we hit the server.
    c. Verify that the text is "Playing Chess is fun!", when Chess is set as the name query parameter. 
3. Open a pull request for code review.
