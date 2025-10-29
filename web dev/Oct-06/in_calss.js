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