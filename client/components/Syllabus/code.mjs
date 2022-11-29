import {initializeContentLocation, buildComponent} from "../setup-component.js";

let content = await initializeContentLocation(import.meta.url);

class Syllabus extends HTMLElement {
    constructor() {
        super();
        this.attachShadow({mode: 'open'});
        buildComponent(content, this.shadowRoot);
    }

    static get observedAttributes() {
        return ['course', 'professor','email', 'office-link'];
    }

    get course() {
        return this.getAttribute('course');
    }

    set course(value) {
        this.setAttribute('course', value);
    }

    get professor() {
        return this.getAttribute('professor');
    }

    set professor(value) {
        this.setAttribute('professor', value);
    }

    get email() {
        return this.getAttribute('email');
    }

    set email(value) {
        this.setAttribute('email', value);
    }


    get officeLink() {
        return this.getAttribute('office-link');
    }

    set officeLink(value) {
        this.setAttribute('office-link', value);
    }



    attributeChangedCallback(name, oldValue, newValue) {


        switch (name) {
            case "course":
                this.shadowRoot.querySelector("#course_name").innerText = newValue;
                break;
            case "professor":
                this.shadowRoot.querySelector("#professor").innerText = newValue;
                break;
            case "email":
                this.shadowRoot.querySelector("#email").innerText = newValue;
                break;
            case "office-link":
                this.shadowRoot.querySelector("#office-room").innerText = newValue;
                break;
        }


    }



}


customElements.define('template-syllabus', Syllabus);