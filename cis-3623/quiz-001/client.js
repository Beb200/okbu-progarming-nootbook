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

async function addgame(round, series, date, score, winning_pitcher,losing_pitcher) {
    let response = await fetch("http://localhost:3000/add-game", {
        method: "POST",
        headers: {
            'Accept' : 'application/json',
            'Content-Type' : 'application/json'
        },
        body: JSON.stringify({
            round: round,
            series: series,
            date : date,
            score : score,
            winning_pitcher : winning_pitcher,
            losing_pitcher : losing_pitcher

        })
    });
    let the_data = await response.json();
    console.log(the_data);
}
addgame("nl-wildcard","reds-dodgers", "2025-10-13", "0-8", "Yamamoto","Littell");
//post_example(10, 10);
