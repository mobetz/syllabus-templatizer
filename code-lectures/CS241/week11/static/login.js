


document.addEventListener("DOMContentLoaded", () => {



    let user_box = document.getElementById("username");
    let password_box = document.getElementById("password");

    let button = document.getElementById("login");

    button.addEventListener("click", () => {

        fetch("http://localhost:9999/authenticate_v2", {
            method: "POST",
            headers: {
                "Content-Type": "application/json"
            },
            body: JSON.stringify({
                username: user_box.value,
                password: password_box.value
            })
        }).then((response) => {

            let result_box = document.getElementById("result");
            if (response.status === 202) {
                result_box.innerText = "Success! Redirecting to control panel..."

                response.json().then((body) => {
                    // Usually, once logged in, we'll save something that represents that the user has successfully
                    // logged in here.
                    window.localStorage.setItem("loggedInAs", body.logged_in_user);

                    setTimeout(() => {
                        window.location = "http://localhost:9999/control_panel.html";
                    }, 3000);

                })

            } else {
                result_box.innerText = "Failure!";
            }


        });

    });




    let create_button = document.getElementById("create");

    create_button.addEventListener("click", () => {

        let create_user = document.getElementById("create_user");
        let create_pass = document.getElementById("create_pass");

        let account_creation_request = {
            username: create_user.value,
            password: create_pass.value
        };

        /*
        This time, we will hit the /create-account endpoint:
         */
        fetch("http://localhost:9999/create-account", {
            method: "POST",
            headers: {
                "Content-Type": "application/json"
            },
            body: JSON.stringify(account_creation_request)
        }).then(( response ) => {

            if (response.status === 200) {
                let result_box = document.getElementById("result");
                result_box.innerText = "Success! Redirecting to login page....";
                setTimeout(() => {
                    window.location = "http://localhost:9999/login_page.html";
                }, 3000);

            } else {
                response.json().then((response_object) => {
                    let result_box = document.getElementById("result");
                    result_box.innerText = "Failure: " + response_object.Message;
                });
            }
        });
    });
});