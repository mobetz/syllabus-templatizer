import {buildComponent, initializeContentLocation} from "../setup-component.js";
import hljs from 'https://cdn.jsdelivr.net/gh/highlightjs/cdn-release@11.7.0/build/es/highlight.min.js';
import * as fflate from 'https://cdn.skypack.dev/fflate';



let content = await initializeContentLocation(import.meta.url);

class CodeViewer extends HTMLElement {
    constructor() {
        super();
        this.attachShadow({mode: 'open'});
        buildComponent(content, this.shadowRoot);
        this.shadowRoot.querySelector("#download_zip")
            .addEventListener("click", () => this.downloadAsZip(this));
        this.code = {};
    }

    static get observedAttributes() {
        return ['pages', 'stem', 'embed'];
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

    get embed() {
        return this.getAttribute('embed');
    }

    set embed(value) {
        this.setAttribute('embed', value)
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
                    .all(pages.map((page) =>  this.generateCodeViewFromPageLocation(page, false) ))
                    .then((tab_list) => {
                        tab_list[0].querySelector("input").checked = true;
                        this.shadowRoot.querySelector(".container").append(...tab_list);
                    });

                break;
            case 'embed':


                 this.generateCodeViewFromPageLocation(newValue, true)
                    .then((tab) => {
                        this.shadowRoot.querySelector(".container").append(tab);
                    });

        }

    }


    generateCodeViewFromPageLocation(page, asIFrame) {
        return this.getCodeFromLocation(this.stem + "/" + page).then((code) => {
            let tab = document.createElement("div");
            tab.classList.add("tab");


            let option = document.createElement("input");
            option.type = "radio";
            option.id = page + "_opt";
            option.name="file_choices";
            tab.appendChild(option);


            if ( asIFrame ) {
                let label = document.createElement("label");
                option.id = page + "_opt_webview";
                label.htmlFor = option.id;
                label.innerText = "Web View";
                tab.appendChild(label);

                let content = document.createElement("iframe");
                content.classList.add("content");
                content.src = this.stem + "/" + page;
                content.height = 1000;
                content.style.marginTop = "28px";
                content.style.padding = "0";
                content.style.border = "0";
                content.style.minHeight = "100%";
                tab.appendChild(content);

            } else {
                let label = document.createElement("label");
                label.htmlFor = option.id;
                label.innerText = page;
                tab.appendChild(label);

                let content = document.createElement("pre");
                content.classList.add("content");

                let codeTag = document.createElement("code");
                this.code[this.stem + "/" + page] = code;
                codeTag.appendChild(document.createTextNode(code));
                tab.appendChild(content);
                content.appendChild(codeTag);


                hljs.highlightElement(content);
            }
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

    downloadAsZip(self) {
        let code = self.raw_code;

        let prefix = Object.keys(code)[0].split("/"); prefix.pop(); prefix = prefix.join("-");
        let filename = prefix + "-lecture-notes.zip";

        let zipper_code = Object.fromEntries( Object.entries(code).map(([k,v]) => [k, fflate.strToU8(v)]) );
        let archive = fflate.zipSync(zipper_code, {
            filename: filename
        });

        let a = document.createElement("a");
        document.body.appendChild(a);
        a.style = "display: none";

        a.download = filename;
        a.href = window.URL.createObjectURL(new Blob([archive], {"type": "application/zip"}));

        a.click();
        window.URL.revokeObjectURL(url);

    }


}


customElements.define('code-viewer', CodeViewer);