
let HTML = await fetch("./Syllabus.html");
let Response = await HTML.text();
let template_container = document.createElement("template");
template_container.innerHTML = Response;

class Syllabus extends HTMLElement {
    constructor() {
        super();
        this.attachShadow({mode: 'open'});
        this.shadowRoot.appendChild(template_container.content.cloneNode(true));
    }

    static get observedAttributes() {
        return ['course', 'professor','email',
            'time', 'days',
            'lab-time', 'lab-days',
            'office-time', 'office-days', 'office-link'];
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

    get time() {
        return this.getAttribute('time');
    }

    set time(value) {
        this.setAttribute('time', value);
    }

    get days() {
        return this.getAttribute('days');
    }

    set days(value) {
        this.setAttribute('days', value);
    }


    get labTime() {
        return this.getAttribute('lab-time');
    }

    set labTime(value) {
        this.setAttribute('lab-time', value);
    }

    get labDays() {
        return this.getAttribute('lab-days');
    }

    set labDays(value) {
        this.setAttribute('lab-days', value);
    }


    get officeTime() {
        return this.getAttribute('office-time');
    }

    set officeTime(value) {
        this.setAttribute('office-time', value);
    }


    get officeDays() {
        return this.getAttribute('office-days');
    }

    set officeDays(value) {
        this.setAttribute('office-days', value);
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
            case "days":
                this.shadowRoot.querySelector("#meeting-time").innerText
                    = newValue + " " + this.time;
                break;
            case "time":
                this.shadowRoot.querySelector("#meeting-time").innerText
                    = this.days + " " + newValue;
                break;
            case "lab-days":
                this.shadowRoot.querySelector("#lab-time").innerText
                    = newValue + " " + this.labTime;
                break;
            case "lab-time":
                this.shadowRoot.querySelector("#lab-time").innerText
                    = this.labDays + " " + newValue;
                break;
            case "office-days":
                this.shadowRoot.querySelector("#office-hours").innerText
                    = newValue + " " + this.officeTime;
                break;
            case "office-time":
                this.shadowRoot.querySelector("#office-hours").innerText
                    = this.officeDays + " " + newValue;
                break;
            case "office-link":
                this.shadowRoot.querySelector("#office-room").innerText = newValue;
                break;
        }


    }



}


customElements.define('template-syllabus', Syllabus);