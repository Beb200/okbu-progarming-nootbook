let data = {
    "nl-wildcard": {
        "reds-dodgers": [
            {
                "date": "2025-09-30",
                "score": "5-10",
                "winning-pitcher" : "Snell",
                "losing-pitcher" : "Greene"
            },
            {
                "date": "2025-10-01",
                "score": "4-8",
                "winning-pitcher" : "Yamamoto",
                "losing-pitcher" : "Littell"
            }
        ],
        "padres-cubs" : [
            {
                "date": "2025-09-30",
                "score": "1-3",
                "winning-pitcher" : "Palencia",
                "losing-pitcher" : "Pivetta"
            },
            {
                "date": "2025-10-01",
                "score": "3-0",
                "winning-pitcher" : "Morejon",
                "losing-pitcher" : "Kittredge"
            },
            {
                "date": "2025-10-02",
                "score": "1-3",
                "winning-pitcher" : "Palencia",
                "losing-pitcher" : "Darvish"
            }
        ]
    },
    "al-wildcard": {
        "rays-guardians": [
            {
                "date": "2025-09-30",
                "score": "2-1",
                "winning-pitcher" : "McClanahan",
                "losing-pitcher" : "Keller"
            },
            {
                "date": "2025-10-01",
                "score": "3-1",
                "winning-pitcher" : "McClanahan",
                "losing-pitcher" : "Keller"
            }
        ],
        "blue-jays-tigers" : [
            {
                "date": "2025-09-30",
                "score": "3-13",
                "winning-pitcher" : "Canning",
                "losing-pitcher" : "Wentz"
            },
            {
                "date": "2025-10-01",
                "score": "1-8",
                "winning-pitcher" : "Canning",
                "losing-pitcher" : "Skubal"
            },
            {
                "date": "2025-10-02",
                "score": "4-6",
                "winning-pitcher" : "Canning",
                "losing-pitcher" : "Mize"
            }
        ]
    },
    "nl-division" : {
    },
    "al-division" : {
    },
    "nl-championship" : {
    },
    "al-championship" : {   
    }   ,
    "world-series" : {
    }
}


export function addgame(round, series, date, score, winning_pitcher,losing_pitcher){
    console.log("start addgame");
    //console.log(data["nl-wildcard"]["padres-cubs"]);
    let the_round = round;
    let the_series = series;
    //console.log(data[the_round][the_series]);
    let game = data[the_round][the_series];
    console.log("get game");
    let new_game = {
        "date" : date,
        "score" : score,
        "winning_pitcher" : winning_pitcher,
        "losing_pitcher" : losing_pitcher,
    };
    console.log("get new game")
    //console.log(game);
    //console.log(new_game);
    game.push(new_game);
    console.log("push game");

}
//addgame("nl-wildcard","reds-dodgers", "2025-10-13", "4-8", "Yamamoto","Littell");
//console.log(data["nl-wildcard"]);

export function deleatgame(round, series, date){
    console.log("sart deleatgame");
    let the_round = round;
    let the_series = series;
    let the_date = date;
    let game = data[the_round][the_series];
    console.log("get game");
    console.log(game);
    //let game_date = game[the_date];
    //console.log(game_date);
    //let index_data = game.indexOf("date");
    //console.log(index_data);
    let data_test = Object.keys(game);
    console.log(data_test);
    if (date in game){
        console.log("found date");
    }
    //game.pop();
    //console.log(data);
}
deleatgame("nl-wildcard","reds-dodgers", "2025-09-30")