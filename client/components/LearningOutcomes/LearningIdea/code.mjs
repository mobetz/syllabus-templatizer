
import {initializeContentLocation, buildComponent} from "../../setup-component.js";

let content = await initializeContentLocation(import.meta.url);

class LearningIdea extends HTMLElement {
    constructor() {
        super();
        this.attachShadow({mode: 'open'});
        buildComponent(content, this.shadowRoot);
    }

    static get observedAttributes() {
        return ['theme'];
    }


    attributeChangedCallback(name, oldValue, newValue) {

        switch (name) {
            case 'theme':
                let parent = this.shadowRoot.host.parentElement;
                let idea_num = -1;
                for ( let i=0; i<parent.children.length; i++ ) {
                    if (parent.children[i] === this) {
                        idea_num = i + 1;
                    }
                }
                this.shadowRoot.querySelector("#theme").innerHTML = convertToRoman(idea_num) + ". " + newValue;
        }


    }


}


customElements.define('syllabus-idea', LearningIdea);

function convertToRoman(num) {
    var roman = {
        M: 1000,
        CM: 900,
        D: 500,
        CD: 400,
        C: 100,
        XC: 90,
        L: 50,
        XL: 40,
        X: 10,
        IX: 9,
        V: 5,
        IV: 4,
        I: 1
    };
    let str = '';

    for (let i of Object.keys(roman)) {
        let q = Math.floor(num / roman[i]);
        num -= q * roman[i];
        str += i.repeat(q);
    }

    return str;
}