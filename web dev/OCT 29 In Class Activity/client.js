async function call_add_numbers(x_val, y_val) {
    let response = await fetch("http://localhost:3000/add-numbers", {
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

async function sub_num(x_val,y_val) {
    let response = await fetch("http://localhost:3000/sub-numbers", {
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

call_add_numbers(45, 67)
//call_add_numbers("x", "y")
//call_add_numbers("x", 67)
//call_add_numbers(45, "y")
