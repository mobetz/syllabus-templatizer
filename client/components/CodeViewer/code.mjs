import {buildComponent, initializeContentLocation} from "../setup-component.js";
import hljs from 'https://cdn.jsdelivr.net/gh/highlightjs/cdn-release@11.7.0/build/es/highlight.min.js';

let content = await initializeContentLocation(import.meta.url);

class CodeViewer extends HTMLElement {
    constructor() {
        super();
        this.attachShadow({mode: 'open'});
        buildComponent(content, this.shadowRoot);
        this.code = {};
    }

    static get observedAttributes() {
        return ['pages', 'stem'];
    }

    get pages() {
        return this.getAttribute('pages');
    }

    set pages(value) {
        this.setAttribute('pages', value)
    }

    get stem() {
        return this.getAttribute('stem');
    }

    set stem(value) {
        this.setAttribute('stem', value)
    }

    get raw_code() {
        return this.code;
    }

    attributeChangedCallback(name, oldValue, newValue) {

        switch (name) {
            case 'pages':

                let tabs = this.shadowRoot.querySelector('.container');
                tabs.innerText = "";

                let pages = newValue.split(";");

                Promise
                    .all(pages.map((page) => this.generateLabelFromPageLocation(this.stem + "/" + page)))
                    .then((tab_list) => {
                        tab_list[0].querySelector("input").checked = true;
                        this.shadowRoot.querySelector(".container").append(...tab_list);
                    });

                break;
        }

    }


    generateLabelFromPageLocation(page) {
        return this.getCodeFromLocation(page).then((code) => {
            let tab = document.createElement("div");
            tab.classList.add("tab");


            let option = document.createElement("input");
            option.type = "radio";
            option.id = page + "_opt";
            option.name="file_choices";
            tab.appendChild(option);

            let label = document.createElement("label");
            label.htmlFor = option.id;
            label.innerText = page;
            tab.appendChild(label);

            let content = document.createElement("pre");
            content.classList.add("content");

            let codeTag = document.createElement("code");
            this.code[page] = code;
            codeTag.appendChild(document.createTextNode(code));
            tab.appendChild(content);
            content.appendChild(codeTag);


            hljs.highlightElement(content);

            return tab;
        });
    }


    async getCodeFromLocation(location) {

        let lectureLocation = location.split("/");

        let thisLocation = import.meta.url.split("/");

        let baseLocation = thisLocation.slice(0, thisLocation.length - 4);
        let contentLocation = baseLocation.concat("code-lectures").concat(lectureLocation).join("/");
        let HTML = await fetch(contentLocation);
        let response = await HTML.text();
        return response;
    }


}


customElements.define('code-viewer', CodeViewer);