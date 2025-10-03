```
import express from "express"
import bodyParser from "body-parser"
import { getExample, computeSum } from "./api.js"

const app = express(); // ALWAYS

app.use(express.json());
app.use(bodyParser.json());

app.get("/get-example", function(req, res) {
    console.log("/get-example");
    getExample();

    let the_return_data = {
        status: "OK"
    }
    res.json(the_return_data);
})

app.post("/post-example", function(req, res) {
    console.log("/post-example");
    console.log(req.body);

    let the_return_data = {
        status: "OK"
    }
    res.json(the_return_data);
})

// Start a server
app.listen(3000, function() {
    console.log("Listening on port 3000");
});
```
```
async function get_example() {
    let response = await fetch("http://localhost:3000/get-example", {
        method: "GET",
        headers: {
            'Accept' : 'application/json',
            'Content-Type' : 'application/json'
        }
    });
    let the_data = await response.json();
    console.log(the_data);
}
get_example();

async function post_example(x_val, y_val) {
    let response = await fetch("http://localhost:3000/post-example", {
        method: "POST",
        headers: {
            'Accept' : 'application/json',
            'Content-Type' : 'application/json'
        },
        body: JSON.stringify({
            x: x_val,
            y: y_val
        })
    });
    let the_data = await response.json();
    console.log(the_data);
}
post_example(10, 10);
```
