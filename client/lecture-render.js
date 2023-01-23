
import  '../client/components/CodeViewer/code.mjs';
import * as fflate from 'https://cdn.skypack.dev/fflate';

function downloadAsZip() {
    let viewer = document.querySelector("code-viewer");
    let code = viewer.raw_code;

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


function createCodeViewer() {
    const params = new Proxy(new URLSearchParams(window.location.search), {get: (param, prop) => param.get(prop)});

    let viewer = document.createElement("code-viewer");
    viewer.stem = params.course + "/" + params.folder;
    viewer.pages = params.pages;

    document.querySelector("h3").innerText += ": " + params.course ;
    document.querySelector("main").appendChild(viewer);

    document.querySelector("#download_zip").addEventListener("click", downloadAsZip);


}

if ( document.readyState !== 'loading')
    createCodeViewer()
else
    document.addEventListener("DOMContentLoaded", createCodeViewer);

