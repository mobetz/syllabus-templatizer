
import {initializeContentLocation, buildComponent} from "../../setup-component.js";

let content = await initializeContentLocation(import.meta.url);

class Holiday extends HTMLElement {
    constructor() {
        super();
        this.attachShadow({mode: 'open'});
        buildComponent(content, this.shadowRoot);
    }

    static get observedAttributes() {
        return ['date', 'start', 'end', 'event'];
    }

    get date() {
        return this.getAttribute('date');
    }

    getDateAsDateTime() {
        let ret = new Date(Date.parse(this.date));
        ret.setDate(ret.getDate() + 1);
        ret.setHours(0);
        ret.setMinutes(0);
        return ret;//TODO: less ugly solution
    }

    set date(value) {
        this.setAttribute('date', value)
    }

    get start() {
        return this.getAttribute('start');
    }

    getStartAsDateTime() {
        let ret = new Date(Date.parse(this.start));
        ret.setDate(ret.getDate() + 1);
        ret.setHours(0);
        ret.setMinutes(0);
        return ret;//TODO: less ugly solution
    }

    set start(value) {
        this.setAttribute('start', value)
    }

    get end() {
        return this.getAttribute('end');
    }

    getEndAsDateTime() {
        let ret = new Date(Date.parse(this.end));
        ret.setDate(ret.getDate() + 1);
        ret.setHours(0);
        ret.setMinutes(0);
        return ret;//TODO: less ugly solution
    }


    set end(value) {
        this.setAttribute('end', value)
    }

    get event() {
        return this.getAttribute('event');
    }

    set event(value) {
        this.setAttribute('event', value)
    }

    attributeChangedCallback(name, oldValue, newValue) {


    }


}


customElements.define('syllabus-holiday', Holiday);