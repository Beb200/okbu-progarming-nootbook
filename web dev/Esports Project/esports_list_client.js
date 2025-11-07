async function call_get_list(){
    let response = await fetch("http://localhost:3000/get-list",{
        method : "GET",
        headers: {
            'Accept' : 'application/json',
            'Content-Type' : 'application/json'
        },
    });
    let the_data = await response.json();
    let status = the_data.status;
    let the_points = the_data.the_games.games;
    
    console.log(the_data); //prints the raw data
    console.log("the status: "+ status); //shows the status 
    console.log(the_points); //prints list to console

    //for(let i = 0; i <= the_points.length; i++){
    //    console.log("the points:" + the_points[i].games);
    //}
    
}

async function call_add_game(name_val, esport_val, console_val, genre_val) {
    let response = await fetch("http://localhost:3000/add-game",{
        method : "POST",
        headers: {
            'Accept' : "application/json",
            'Content-Type' : 'application/json'
        },
        body: JSON.stringify({
            name : name_val,
            esport : esport_val,
            console : console_val,
            genre : genre_val
        })
    });
    let the_data = await response.json();
    console.log(the_data);
}

call_get_list();
call_add_game("lol", "yes", "pc", "MOBA")
call_get_list();