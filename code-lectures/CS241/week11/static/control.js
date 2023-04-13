
/*
Unlike our other pages, control_panel.html has two different 'versions' of the page:
   - A 'not logged in' view that tells the user to log in
   - A 'logged in view' that allows users to change their password

 */


document.addEventListener("DOMContentLoaded", () => {

    let logged_in_user = window.localStorage.getItem("loggedInAs");


    /*
    If the user is not logged in, we will send them back to the login page:
     */

    if (logged_in_user === null) {
        setTimeout(() => {
            window.location = "http://localhost:9999/login_page.html"
        }, 3000);
    }  else {
        /*
        If the user did already log in, we can hide the not logged in view, and show the real control panel:
        */

        let not_authed_div = document.querySelector("#not-authed");
        let authed_div = document.querySelector("#auth-only");

        not_authed_div.style.visibility = "hidden";
        authed_div.style.visibility = "visible";

        let welcome_span = document.querySelector("#welcome-message");
        welcome_span.innerText = "Welcome, " + logged_in_user + "!";


        /*
        Now, we can add an event to our button to allow us to call the update-password endpoint:
         */
        let button = document.querySelector("#submit");

        button.addEventListener("click", () => {

            let old_password = document.querySelector("#old");
            let new_password = document.querySelector("#new");

            let update_request = {
                old: old_password.value,
                new: new_password.value
            }

            fetch("http://localhost:9999/change-password", {
                method: "PUT",
                headers: {
                    "Content-Type": "application/json"
                },
                body: JSON.stringify(update_request)
            })
                .then((response) => {
                    let result_box = document.getElementById("result");
                    if (response.status === 200) {
                        result_box.innerText = "Success!";
                    }else {
                        response.json().then((data) => {
                            result_box.innerText = "Failed: " + data.Message;
                            if ( response.status === 403 ) {
                                result_box.innerText += " Logging you out and returning to login page...";
                                window.localStorage.removeItem("loggedInAs");
                                setTimeout(() => { window.location = "http://localhost:9999/login_page.html"}, 3000);
                            }
                        });
                    }

                })

        });//when the change password button is clicked

    }//otherwise if they are logged in
    });//end of DOMContentLoaded
