

export async function initializeContentLocation(scriptPath) {
    let scriptParts = scriptPath.split("/");
    let baseLocation = scriptParts.slice(0, scriptParts.length - 1);
    let contentLocation = baseLocation.concat("content.html").join("/");
    let HTML = await fetch(contentLocation);
    Response = await HTML.text();
    return {content: Response, location: baseLocation};
}

export function buildComponent(init, shadow_root) {


    let template_container = document.createElement("template");
    template_container.innerHTML = init.content;

    let container = template_container.content.cloneNode(true);
    container.childNodes //<- avoid pop-in from loading the stylesheet by hiding everything until loaded
        .forEach((n) => n.style != undefined ? n.style.visibility = "hidden" : null);

    let styles = document.createElement("link");
    styles.rel = "stylesheet";
    styles.href = init.location.concat("style.css").join("/");
    styles.addEventListener('load', () => {
        shadow_root.childNodes //<- once a documentfragment is consumed, those elements will be on the shadowRoot
            .forEach((n) => n.style != undefined ? n.style.visibility = "visible" : null)
    });

    shadow_root.appendChild(styles);
    shadow_root.appendChild(container);

}