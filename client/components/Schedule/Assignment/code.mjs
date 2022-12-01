
import {initializeContentLocation, buildComponent} from "../../setup-component.js";

let content = await initializeContentLocation(import.meta.url);

class Assignment extends HTMLElement {
    constructor() {
        super();
        this.attachShadow({mode: 'open'});
        buildComponent(content, this.shadowRoot);
    }

    static get observedAttributes() {
        return ['title', 'duein', 'highlight'];
    }

    get title() {
        return this.getAttribute('title');
    }

    set title(value) {
        this.setAttribute('title', value)
    }

    get dueIn() {
        return this.getAttribute('duein');
    }

    set dueIn(value) {
        this.setAttribute('duein', value)
    }

    get highlight() {
        return this.getAttribute('highlight') || "0.5";
    }

    set highlight(value) {
        this.setAttribute('highlight', value)
    }

    attributeChangedCallback(name, oldValue, newValue) {


    }


}


customElements.define('syllabus-assignment', Assignment);