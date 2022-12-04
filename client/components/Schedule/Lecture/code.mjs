
import {initializeContentLocation, buildComponent} from "../../setup-component.js";

let content = await initializeContentLocation(import.meta.url);

class Lecture extends HTMLElement {
    constructor() {
        super();
        this.attachShadow({mode: 'open'});
        buildComponent(content, this.shadowRoot);
    }

    static get observedAttributes() {
        return ['topic', 'unit', 'highlight', 'href'];
    }

    get topic() {
        return this.getAttribute('topic');
    }

    set topic(value) {
        this.setAttribute('topic', value)
    }

    get unit() {
        return this.getAttribute('unit');
    }

    set unit(value) {
        this.setAttribute('unit', value)
    }

    get highlight() {
        return this.getAttribute('highlight') || "0";
    }

    set highlight(value) {
        this.setAttribute('highlight', value)
    }

    get href() {
        return this.getAttribute('href');
    }

    set href(value) {
        this.setAttribute('href', value)
    }

    attributeChangedCallback(name, oldValue, newValue) {

    }


}


customElements.define('syllabus-lecture', Lecture);