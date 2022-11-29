
import {initializeContentLocation, buildComponent} from "../setup-component.js";

let content = await initializeContentLocation(import.meta.url);

class ScheduleEvent extends HTMLElement {
    constructor() {
        super();
        this.attachShadow({mode: 'open'});
        buildComponent(content, this.shadowRoot);
    }

    static get observedAttributes() {
        return ['example'];
    }

    get example() {
        return this.getAttribute('example');
    }

    set example(value) {
        this.setAttribute('example', value)
    }

    attributeChangedCallback(name, oldValue, newValue) {

        switch (name) {
            case 'example':
                this.shadowRoot.querySelector('#example').innerText = newValue;
                break;
        }

    }


}


customElements.define('syllabus-event', ScheduleEvent);