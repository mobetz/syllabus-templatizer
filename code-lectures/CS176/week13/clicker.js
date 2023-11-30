

/*

React has many features we can make use of for our 'custom' components. For instance, we can set properties on our
components:

 */




function Clicker(attributes) {

    /*
    React even gives us 'hooks', that let us create functions that will update the state of our components:
    Hooks have two parts, a variable they track, and a function you can call to update anything using that variable.
     */
    let [num_clicks, click_count_updater] = React.useState(0);


    function click_pressed() {
        //I want to count up by one
        click_count_updater(num_clicks + 1); //<- this says "take the current number of clicks, add one to it, and update my controls
    }

    function reset_pressed() {
        //I want to reset to zero
        click_count_updater(0);
    }

    /*
    When we have a 'bound state', we can put it inside our HTML by wrapping it in {curly braces}.

    This curly brace syntax also works with functions!
     */
    return (
        <div style={{backgroundColor: attributes.color}}>
            Hello {attributes.username}, you have clicked the button {num_clicks} times. <br />
            <button onClick={click_pressed}>Click Me!</button>
            <button onClick={reset_pressed}>Reset!</button>
        </div>
    )
    /*
    If I want to customize my tag with custom attributes, I can do so by putting double curly braces and using
    things from the "attributes" parameter of my component function.
     */
}


let content_container = document.querySelector("#content");
let inner_div = document.createElement("div")
ReactDOM.render(<Clicker color="green" username="Jennifer"></Clicker>, content_container);

content_container.appendChild(inner_div);
ReactDOM.render(<Clicker color="purple" username="Joe" />, inner_div);

/*
Full documentation for the React library is available at https://reactjs.org/docs/add-react-to-a-website.html

Next class, we'll learn an even more modern way to write these reusable HTML components!

 */