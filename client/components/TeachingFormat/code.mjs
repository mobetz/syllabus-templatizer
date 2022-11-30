
import {initializeContentLocation, buildComponent} from "../setup-component.js";

let content = await initializeContentLocation(import.meta.url);

class TeachingFormat extends HTMLElement {
    constructor() {
        super();
        this.attachShadow({mode: 'open'});
        buildComponent(content, this.shadowRoot);
    }

    static get observedAttributes() {
        return ['labdays', 'meetingdays'];
    }

    get labdDays() {
        return this.getAttribute('labdays');
    }

    set labDays(value) {
        this.setAttribute('labdays', value)
    }

    get meetingDays() {
        return this.getAttribute('meetingdays');
    }

    set meetingDays(value) {
        this.setAttribute('meetingdays', value)
    }

    counts = {
        1: "once",
        2: "twice",
        3: "thrice",
        4: "four times",
        5: "five times"
    }

    attributeChangedCallback(name, oldValue, newValue) {

        switch (name) {
            case 'meetingdays':
                this.shadowRoot.querySelector('#meetingtimes').innerText = this.counts[newValue.length];
                break;
            case 'labdays':
                if ( newValue.length == 0 ) {
                    this.shadowRoot.querySelector("#labinfo").style.display = "none";

                } else {
                    this.shadowRoot.querySelector("#labinfo").style.display = "block";
                    this.shadowRoot.querySelector('#labtimes').innerText = this.counts[newValue.length];
                }
                break;
        }

    }


}


customElements.define('syllabus-format', TeachingFormat);