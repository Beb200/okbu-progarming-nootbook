const express = require("express"); // ALWAYS
const bodyParser = require("body-parser");
const app = express(); // ALWAYS
//const cors = require('cors'); // MIDDLEWARE TO HANDLE CROSS DOMAIN ERRORS

app.use(cors());
app.use(express.json());
app.use(bodyParser.json());

app.post("/add-numbers", function(req, res) {
    let x = req.body.x;
    let y = req.body.y;

    // TODO Check that these are valid numbers

    console.log("received a request to add " + x + " and " + y);
    let sum = x + y;
    let the_return_data = {
        status: "OK",
        the_sum: sum
    }
    res.json(the_return_data);
});

app.post("/sub-numbers", function(req, res) {
    let x = req.body.x;
    let y = req.body.y;

    // TODO Check that these are valid numbers

    console.log("received a request to sub " + x + " and " + y);
    let anwser = x - y;
    let the_return_data = {
        status: "OK",
        the_sum: anwser
    }
    res.json(the_return_data);
});


// Start a server
app.listen(3000, function() {
console.log("Listening on port 3000");
});
