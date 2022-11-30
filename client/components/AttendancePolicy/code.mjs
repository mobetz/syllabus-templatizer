
import {initializeContentLocation, buildComponent} from "../setup-component.js";

let content = await initializeContentLocation(import.meta.url);

class AttendancePolicy extends HTMLElement {
    constructor() {
        super();
        this.attachShadow({mode: 'open'});
        buildComponent(content, this.shadowRoot);
    }

    static get observedAttributes() {
        return ['course'];
    }

    get course() {
        return this.getAttribute('course');
    }

    set course(value) {
        this.setAttribute('course', value)
    }

    attributeChangedCallback(name, oldValue, newValue) {

        switch (name) {
            case 'course':
                this.shadowRoot.querySelector('#course').innerText = newValue;
                break;
        }

    }


}


customElements.define('syllabus-attendance', AttendancePolicy);