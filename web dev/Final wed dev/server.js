const express = require("express"); // ALWAYS
const bodyParser = require("body-parser");
const app = express(); // ALWAYS

const cors = require('cors'); // MIDDLEWARE TO HANDLE CROSS DOMAIN ERRORS
app.use(cors());

app.use(express.json());
app.use(bodyParser.json());


//Start a server
app.listen(3000, function(){
    console.log("listening on port 3000");
})