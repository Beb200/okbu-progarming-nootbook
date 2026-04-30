const express = require("express"); // ALWAYS
const bodyParser = require("body-parser");
const app = express(); // ALWAYS

const cors = require('cors'); // MIDDLEWARE TO HANDLE CROSS DOMAIN ERRORS
app.use(cors());

app.use(express.json());
app.use(bodyParser.json());

const Data = require('./data v2.json');

app.post("/getNumJobPeople", function(req, res) {
    console.log("/post-example");
    console.log(req.body);

    let name = req.body.Name
    console.log(name);
    console.log(Data[name].length);

    let the_return_data = {
        status: "OK",
        num : Data[name].length
    }
    res.json(the_return_data);
})


//Start a server
app.listen(3000, function(){
    console.log("listening on port 3000");
})