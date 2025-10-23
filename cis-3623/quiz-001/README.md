# CIS 3623 Quiz 001
The API and server described below provide access to JSON data that stores information on the 2025 Major League Baseball playoffs.
# API
Implement a Javascript API with the following functions:
* ```createGame```
    * Inputs 
        * round ("nl-wildcard", "al-wildcard", etc.)
        * series ("reds-dodgers", "padres-cubs", etc.)
        * date 
        * score 
        * winning pitcher
        * losing pitcher
    * Outputs 
        * On success: A JSON object representing the game 
        * On failure: some indication of the reason for the failure
* ```getGame```
    * Inputs
        * round ("nl-wildcard", "al-wildcard", etc.)
        * series ("reds-dodgers", "padres-cubs", etc.)
        * date
    * Outputs
        * On success: A JSON object representing the game 
        * On failure: some indication of the reason for the failure
* ```updateGameScore```
    * Inputs: 
        * round ("nl-wildcard", "al-wildcard", etc.)
        * series ("reds-dodgers", "padres-cubs", etc.)
        * date 
        * score 
    * Outputs: 
        * On success: A JSON object representing the updated game 
        * On failure: some indication of the reason for the failure
* ```deleteGame```
    * Inputs 
        * round ("nl-wildcard", "al-wildcard", etc.)
        * series ("reds-dodgers", "padres-cubs", etc.)
        * date 
    * Outputs
        * Success or failure indicator 
# Server
Using the API above, create server endpoints / routes for the following URL addresses
* ```/create-game```
    * Type: ```POST```
    * Input / request body (values in <> will be provided by the client)
        ```
        {
            round: <the name of the round>,
            series: <the name of the series>,
            date: <the date of the game>,
            score: <the score of the game>,
            winning-pitcher: <the name of the winning pitcher>,
            losing-pitcher:  <the name of the losing pitcher>
        }
        ```
    * Output / response (values in <> will be provided by the server)
        * On success
            ```
            {
                status: "OK",
                the-new-game : {
                    round: <the name of the round>,
                    series: <the name of the series>,
                    date: <the date of the game>,
                    score: <the score of the game>,
                    winning-pitcher: <the name of the winning pitcher>,
                    losing-pitcher:  <the name of the losing pitcher>
                }
            }
            ```
        * On failure
            ```
            {
                status: "FAIL",
                message : "<A message explaining the reason for the error>."
            }
            ```
* ```/get-game```
    * Type: ```GET```
    * Input / request body (values in <> will be provided by the client)
        ```
        {
            round: <the name of the round>,
            series: <the name of the series>,
            date: <the date of the game>
        }
        ```
    * Output / response (the value of game is the actual game data in JSON. what is shown is an example only. )
        * On success
            ```
            {
                status: "OK"
                game: {
                    "date": "2025-09-30",
                    "score": "1-3",
                    "winning-pitcher" : "Palencia",
                    "losing-pitcher" : "Pivetta"
                }
            }
            ```
        * On failure
            ```
            {
                status: "FAIL",
                message : "<A message that explains the reason for the failure>."
            }
            ```
* ```/update-game-score```
    * Type: ```POST```
    * Input / request body (values in <> will be provided by the client)
        ```
        {
            round: <the name of the round>,
            series: <the name of the series>,
            date: <the date of the game>,
            score: <the new score of the game>
        }
        ```
    * Output / response (values in <> will be provided by the server)
        * On success
            ```
            {
                status: "OK"
            }
            ```
        * On failure
            ```
            {
                status: "FAIL",
                message : "<Informative message explaining the reason for the error>."
            }
            ```
* ```/delete-game```
    * Type: ```POST```
    * Input / request body (values in <> will be provided by the client)
        ```
        {
            round: <the name of the round>,
            series: <the name of the series>,
            date: <the date of the game>
        }
        ```
    * Output / response (values in <> will be provided by the server)
        * On success
            ```
            {
                status: "OK"
            }
            ```
        * On failure
            ```
            {
                status: "FAIL",
                message : "<A message explaining the reason for the error>."
            }
            ```
# Client
Implement a client to test your code.