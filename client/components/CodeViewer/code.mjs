import {buildComponent, initializeContentLocation} from "../setup-component.js";
import hljs from 'https://cdn.jsdelivr.net/gh/highlightjs/cdn-release@11.7.0/build/es/highlight.min.js';

let content = await initializeContentLocation(import.meta.url);

class CodeViewer extends HTMLElement {
    constructor() {
        super();
        this.attachShadow({mode: 'open'});
        buildComponent(content, this.shadowRoot);
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

    attributeChangedCallback(name, oldValue, newValue) {

        switch (name) {
            case 'pages':

                let tabs = this.shadowRoot.querySelector('.container');
                tabs.innerText = "";

                let pages = newValue.split(";");
                for ( let page of pages ) {

                    //TODO: promises.all() before appending to shadowRoot to ensure consistency in page order

                    this.generateLabelFromPageLocation(this.stem + "/" + page)
                        .then((tab_element) => {
                            this.shadowRoot.querySelector(".container").appendChild(tab_element);
                    });




                }

                this.shadowRoot.querySelector(".tab:nth-child(1) input").checked = true;

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