
let HTML = await fetch("./Timeslot.html");
let Response = await HTML.text();
let template_container = document.createElement("template");
template_container.innerHTML = Response;

class Timeslot extends HTMLElement {
    constructor() {
        super();
        this.attachShadow({mode: 'open'});
        this.shadowRoot.appendChild(template_container.content.cloneNode(true));
    }

    static get observedAttributes() {
        return ['days', 'starthour', 'startmin', 'endhour', 'endmin'];
    }

    get days() {
        return this.getAttribute('days');
    }

    set days(value) {
        this.setAttribute('days', value);
    }


    get startHour() {
        return this.getAttribute('starthour');
    }

    set startHour(value) {
        this.setAttribute('starthour', value);
    }

    get startMin() {
        return this.getAttribute('startmin');
    }

    set startMin(value) {
        this.setAttribute('startmin', value);
    }

    get endHour() {
        return this.getAttribute('endhour');
    }

    set endHour(value) {
        this.setAttribute('endhour', value);
    }

    get endMin() {
        return this.getAttribute('endmin');
    }

    set endMin(value) {
        this.setAttribute('endmin', value);
    }



    attributeChangedCallback(name, oldValue, newValue) {


        switch (name) {
            case "days":
                this.shadowRoot.querySelector("#days").innerText = newValue;
                break;
            case "starthour":
            case "startmin":
            case "endhour":
            case "endmin":
                this.regenerateTimeAppearance()
                break;
        }

    }

    regenerateTimeAppearance() {
        let timeFormatter = new Intl.DateTimeFormat('en', {hour:'numeric', minute:'numeric'});
        let startTime = timeFormatter.format(new Date(0, 0, 1, this.startHour, this.startMin));
        let endTime = timeFormatter.format(new Date(0, 0, 1, this.endHour, this.endMin));


        this.shadowRoot.querySelector("#start").innerText = startTime;
        this.shadowRoot.querySelector("#end").innerText = endTime;

    }


}


customElements.define('syllabus-timeslot', Timeslot);