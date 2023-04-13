
document.addEventListener("DOMContentLoaded", () => {
    let button = document.getElementById("update_details");

    button.addEventListener('click', () => {


        let name_field = document.getElementById("name");
        let major_field = document.getElementById("major");

        let modified_student_request = {
            name: name_field.value,
            major: major_field.value,
            id: student_id
        }

        fetch("http://localhost:9999/Students/" + student_id, {
            method: "PUT",
            headers: {
                "Content-Type": "application/json"
            },
            body: JSON.stringify(modified_student_request)
        }).then((resp) => {
            let results_field = document.getElementById("results");
            if ( resp.status === 200 ) {
                results_field.innerText = "Saved!";
            } else {
                results_field.innerText = "Failed!";
            }
        })
    });

});
