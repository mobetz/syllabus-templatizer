
import {initializeContentLocation, buildComponent} from "../../setup-component.js";

let content = await initializeContentLocation(import.meta.url);

class GradeCategory extends HTMLElement {
    constructor() {
        super();
        this.attachShadow({mode: 'open'});
        buildComponent(content, this.shadowRoot);
    }

    static get observedAttributes() {
        return ['theme', 'value'];
    }


    attributeChangedCallback(name, oldValue, newValue) {

        switch (name) {
            case 'theme':
                this.shadowRoot.querySelector("#theme").innerHTML = newValue;
                break;
            case 'value':
                this.shadowRoot.querySelector("#value").innerHTML = newValue + "%";
        }


    }


}


customElements.define('syllabus-category', GradeCategory);
