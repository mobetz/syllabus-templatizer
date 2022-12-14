
import {initializeContentLocation, buildComponent} from "../setup-component.js";
import './LearningIdea/code.mjs';

let content = await initializeContentLocation(import.meta.url);

class LearningOutcomes extends HTMLElement {
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


customElements.define('syllabus-outcomes', LearningOutcomes);