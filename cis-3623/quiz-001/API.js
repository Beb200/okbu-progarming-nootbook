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
    if(round == "nl-wildcard"){
        let x = 0;
    }
    else(round == "al-wildcard"){
        let x = 1;
    }
    let game = data.round[x][y];
    console.log("get game");
    let new_game = {
        "date" : date,
        "score" : score,
        "winning_pitcher" : winning_pitcher,
        "losing_pitcher" : losing_pitcher,
    };
    console.log("get new game")
    console.log(game);
    console.log(new_game);
    game.push(new_game);
    console.log("push game");

}
addgame("al-wildcard","reds-dodgers", "2025-10-13", "4-8", "Yamamoto","Littell");
console.log(data["al-championship"]);