
import Express from 'express';


let app = Express();
let router = Express.Router();

app.use("/api", router);


let job_list = [{
    "name": "Software Engineer",
    "city": "Seattle",
    "company": "Microsoft",
    "state": "WA",
    "income": 70000
},{
    "name": "Software Engineer",
    "city": "Boston",
    "company": "Fintech",
    "state": "MA",
    "income": 72000
},{
    "name": "Sr. Software Engineer",
    "city": "Boston",
    "company": "Fintech",
    "state": "MA",
    "income": 120000
}
]



router.get("/Jobs", (req, resp) => {

    let user_specified_location = req.query.location;
    let user_min_salary = req.query.min_salary;

    if ( isNaN(user_min_salary)) {
        user_min_salary = 0;
    } else {
        user_min_salary = parseInt(user_min_salary);
    }

    let search_results = [];

    for ( let job of job_list ) {
        if ( job.city.includes(user_specified_location)
            && job.income >= user_min_salary ) {
            search_results.push(job);
        }
    }

    resp.json(search_results);
})


/* We can also use Express to serve our 'static' content (HTML, CSS, images, and client-side JavaScript.)
by using the Express.static() function.
 */
app.use('/', Express.static('../client'));


app.listen(8080, () => {
    console.log("Server started on 8080.");
})