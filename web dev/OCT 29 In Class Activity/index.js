
//add button
let add_button = document.getElementById("add_btn");
add_button.addEventListener("click", async function(event) {
    console.log(event.target.id + " clicked ...");
    let x_input_text = document.getElementById("x_value_id");
    let y_input_text = document.getElementById("y_value_id");

    if (x_input_text.value.length == 0 || y_input_text.value.length == 0) {
        let the_div_placeholder = document.getElementById("anwser_id");
        let sum_p = document.createElement("p");
        sum_p.innerHTML = "<b>Enter values for x and y</b>";
        the_div_placeholder.appendChild(sum_p);
    } else {
        let x_val = parseInt(x_input_text.value);
        let y_val = parseInt(y_input_text.value);
        console.log("Going to add " + x_val + " and " + y_val);

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
        let sum = the_data.the_sum;
        console.log("The sum is " + sum);
        let the_div_placeholder = document.getElementById("anwser_id");
        let sum_p = document.createElement("p");
        sum_p.innerHTML = "<b>The sum is: " + sum + "</b>";
        the_div_placeholder.appendChild(sum_p);
    }
});

//sub button
let sub_button = document.getElementById("sub-btn");
sub_button.addEventListener("click", async function (event) {
    console.log(event.target.id + "clicked");
    let x_input_text = document.getElementById("x_value_id");
    let y_input_text = document.getElementById("y_value_id");

    let the_div_placeholder = document.getElementById("anwser_id");
    let sum_p = document.createElement("p");

    if(x_input_text.value.length == 0 || y_input_text.value.length == 0){
        sum_p.innerHTML = "<b>Enter values for x and y</b>";
        the_div_placeholder.appendChild(sum_p);
    }else{
        let x_val = parseInt(x_input_text.value);
        let y_val = parseInt(y_input_text.value);
        console.log("Going to subtract " + x_val + " and " + y_val);

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
        let anwser = the_data.the_anwser;
        console.log("The sum is " + anwser);
        let the_div_placeholder = document.getElementById("anwser_id");
        let anwser_p = document.createElement("p");
        anwser_p.innerHTML = "<b>The sum is: " + anwser + "</b>";
        the_div_placeholder.appendChild(anwser_p);
    }
})