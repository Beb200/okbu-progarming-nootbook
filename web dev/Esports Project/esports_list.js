const express = require("express"); // ALWAYS
const bodyParser = require("body-parser");
const app = express(); // ALWAYS

const cors = require('cors'); // MIDDLEWARE TO HANDLE CROSS DOMAIN ERRORS
app.use(cors());

app.use(express.json());
app.use(bodyParser.json());


let list = {
    games : [
        {name : "overwatch" , esport : "yes", console : "pc, xbox, playstation, switch", genre : "hero shoter"},
        {name :"fortnight" , esport : "yes", console : "pc, xbox, playstation", genre : "battle rolayl"}
    ]
}
 

app.get('/', (req, res) => {
  res.send('Hello from the default endpoint!');
});


app.get("/get-list", function(req,res){ //showing the list
    console.log("getting list");
    console.log(list);
    let the_return_data = {
        status :"ok",
        the_games : list
    }
    res.json(the_return_data);
})


app.post("/add-game", function(req,res){ //add a game to the list
    console.log("Adding a game")
    console.log(req.body.name); // test the name
    console.log(req.body.esport); //test esport
    console.log(req.body.console); //test console
    console.log(req.body.genre); // test genre

    let name_val = req.body.name;
    let esport_val = req.body.esport;
    let console_val = req.body.console;
    let genre_val = req.body.genre;

    let new_game = {//formates the vals into a map
        games : [
            {name : name_val,  esport : esport_val, console : console_val, genre : genre_val}
        ]
    }
    console.log(new_game);//test new_game
    console.log("received a request to add game");

    list.games.push(new_game);

    let r_d = {
        status : "ok",
        game : new_game
    }
    res.json(r_d);
})

app.listen(3000, function() {
    console.log("Listening on port 3000");
});
