const express = require("express"); // ALWAYS
const bodyParser = require("body-parser");
const app = express(); // ALWAYS

const cors = require('cors'); // MIDDLEWARE TO HANDLE CROSS DOMAIN ERRORS
app.use(cors());

app.use(express.json());
app.use(bodyParser.json());

app.post("/function-001", function(req, res) {
    console.log(req.body)
    console.log("The data is " + req.body.name + ", " + req.body.hometown + ", " + req.body.age);

    let the_return_data = {
        status: "OK",
        the_description : req.body.name + " is " + req.body.age + " years old and is from " + req.body.hometown
    }
    res.json(the_return_data);
});

app.listen(3000, function() {
    console.log("Listening on port 3000");
});
