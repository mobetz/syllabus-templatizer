
/*
Last week, we had learned how to pass one function to another function as a 'callback'. This let us describe
behavior that we wanted to happen later, such as after an event occurs:
 */

document.addEventListener("DOMContentLoaded", function () {


    /*
    However, this isn't the only way we can describe steps that happen later. In the early
    2000s, Javascript adopted another convention for talking about future behavior: the promise.


   Promises exist in their own little bubble. If we want to start a chain of promises, we can do so
   by resolving an empty promise with the resolve():
     */

    let promise_to_do_something = Promise.resolve();


    /*
    Once we have a 'promise' object, we can tell the promise what we want to do next with .then().
    Inside of the 'then' function, we describe behavior we want to happen. At the end of our
    function, we can return a 'final result' of this work. the .then() function then returns another
    new promise that works just like the previous one.
     */


    let promise_to_count_to_a_million = promise_to_do_something.then(function (the_data_from_previous_promise) {
        let i = 0;
        while (i < 999999) {
            i = i + 1;
        }
        console.log("The loop finished!");

        return i;
    });


    /*
    Unlike normal code, Javascript will not 'wait' for a promise to finish. Instead, it will do that
    work in the background, and our program will continue:
     */
    console.log("This line was written after our promise's loop.");


    /*
    Unfortunately, we can't use the final answer from "promise_to_count_to_a_million" directly, because
    we don't know when it will finish.

       Later in our program, we can use our 'next_promise' just like we used the previous one:
     */

    promise_to_count_to_a_million.then(function (final_counted_number) {
        console.log("The final number we counted to was: " + final_counted_number);
    });

    /*
    We can use promises to chain together strings of tasks, or even pass our promises around to give another
    part of our program a 'promise' that we'll give it a result eventually!
     */


    /*
    Many of our Browser APIs use promises to give us new functionality. For instance, the WebRTC API gives us
    functions that let us ask our browser for permission to use the webcam:
     */


    let promise_that_webcam_is_ready_to_use = navigator.mediaDevices.getUserMedia({video: true, audio: false});


    promise_that_webcam_is_ready_to_use.then(function (webcam_feed) {

        /*
        Our getUserMedia() function returns a promise to give us a webcam once the user has given us permission.
        Afterward, that promise resolves with some data representing the webcam feed, which we can attach to our
        video tag.
         */

        let video_tag = document.querySelector("#webcam");
        video_tag.style.width = "400px";
        video_tag.srcObject = webcam_feed;
        video_tag.play();
    });


    /* Sometimes, we get a function that doesn't quite return a promise, but works similarly to one. In this case,
       we can call a special "new Promise()" function that lets us describe the setup to a promise, such as with
       the GeoLocation API to find out a user's latitude and longitude.*/


    let promise_to_get_coordinates = new Promise(function (behavior_when_promise_kept, behavior_when_promise_broken) {
        navigator.geolocation.getCurrentPosition(behavior_when_promise_kept, behavior_when_promise_broken);
    });


    let promise_to_populate_the_webpage = promise_to_get_coordinates.then(function coord_populator(location) {
        let latitude = location.coords.latitude;
        let longitude = location.coords.longitude;

        let x_span = document.querySelector("#coord_lat");
        let y_span = document.querySelector("#coord_long");


        x_span.innerText = latitude;
        y_span.innerText = longitude;

        return location.coords;

    });



    /*
    One final thing we can practice today is getting data from another website. This uses one more browser API,
    called the "fetch" API. We can request to 'fetch' some data, and then
     */

    let promised_quokka = fetch("https://quokka.pics/api/", {
        method: "GET"
    });

    let promise_to_interpret_the_quokka_response =  promised_quokka.then(function (quokka_response) {
        if ( quokka_response.status == 200 ) {
            return quokka_response.json();
        } else {
                return {"error": "Error loading the panda info :("};
        }
    });

    promise_to_interpret_the_quokka_response.then( function (quokka_info) {

        let quokka_image = document.querySelector("#quokka_pic");
        quokka_image.src = quokka_info.image;

        let quokka_fact = document.querySelector("#quokka_fact");
        quokka_fact.innerText = quokka_info.error;

    });



    let search_box = document.querySelector("#state_name");
    let button = document.querySelector("#search")

    button.addEventListener("click", function() {
        fetch("https://api.covidtracking.com/v1/states/" + search_box.value + "/info.json", {
            method: "GET"
        }).then(function (state_response) {
            if (state_response.status == 200) {
                return state_response.json();
            }
        }).then(function (state_data) {
            console.log(state_data);

            let covid_data_box = document.getElementById("covid_data");
            covid_data_box.innerText = "Resources for the state of " + state_data.state + ": " +
                "Covid dashboard is at: " + state_data.covid19Site;
        });

    });
});