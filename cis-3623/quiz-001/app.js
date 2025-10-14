import express from "express"
import bodyParser from "body-parser"
import {addgame} from "./API.js"

const app = express(); // ALWAYS

app.use(express.json());
app.use(bodyParser.json());
/*
app.get("/get-example", function(req, res) {
    console.log("/get-example");
    getExample();

    let the_return_data = {
        status: "OK"
    }
    res.json(the_return_data);
})*/

app.post("/add-game", function(req, res) {
    console.log("/add-game");
    console.log(req.body)
    addgame(req.body.round,req.body.series,req.body.date,req.body.score, req.body.winning_pitcher,req.body.losing_pitcher);

    let the_return_data = {
        status: "OK"
    }
    res.json(the_return_data);
})

// Start a server
app.listen(3000, function() {
    console.log("Listening on port 3000");
});