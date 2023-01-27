
import  '../client/components/CodeViewer/code.mjs';

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

