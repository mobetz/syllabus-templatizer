
import {initializeContentLocation, buildComponent} from "../setup-component.js";

let content = await initializeContentLocation(import.meta.url);

class SubmissionPolicy extends HTMLElement {
    constructor() {
        super();
        this.attachShadow({mode: 'open'});
        buildComponent(content, this.shadowRoot);
    }

    static get observedAttributes() {
        return [];
    }

    attributeChangedCallback(name, oldValue, newValue) {


    }


}


customElements.define('syllabus-submission', SubmissionPolicy);