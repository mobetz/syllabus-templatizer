
import {initializeContentLocation, buildComponent} from "../setup-component.js";

let content = await initializeContentLocation(import.meta.url);

class Schedule extends HTMLElement {
    constructor() {
        super();
        this.attachShadow({mode: 'open'});
        buildComponent(content, this.shadowRoot);
    }

    connectedCallback() {

    }


    static get observedAttributes() {
        return ['startdate', 'enddate', 'meetdays', 'labdays'];
    }

    attributeChangedCallback(name, oldValue, newValue) {

    }


    computeSchedule() {

    }
}


customElements.define('syllabus-schedule', Schedule);