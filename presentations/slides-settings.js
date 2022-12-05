

 Reveal.initialize({
    plugins: [RevealHighlight],

    controls: true,

    width: '1920',
    height: '1080',
    progress: true,
    slideNumber: true,// Display the page number of the current slide
    history: false,// Push each slide change to the browser history
    keyboard: true,// Enable keyboard shortcuts for navigation
    overview: true,// Enable the slide overview mode
    center: false,// Vertical centering of slides
    touch: true,// Enables touch navigation on devices with touch input
    fragments: true,// Turns fragments on and off globally
    help: true,// Flags if we should show a help overlay when ? is pressed


    // Global override for autolaying embedded media (video/audio/iframe)
    // - null: Media will only autoplay if data-autoplay is present
    // - true: All media will autoplay, regardless of individual setting
    // - false: No media will autoplay, regardless of individual setting
    autoPlayMedia: null,

    // Number of milliseconds between automatically proceeding to the
    // next slide, disabled when set to 0, this value can be overwritten
    // by using a data-autoslide attribute on your slides
    autoSlide: 0,
    autoSlideMethod: Reveal.navigateNext, // Use this method for navigation when auto-sliding
    mouseWheel: false,// Enable slide navigation via mouse wheel
    hideAddressBar: true,// Hides the address bar on mobile devices
    previewLinks: true,// Opens links in an iframe preview overlay
    transition: 'none', // none/fade/slide/convex/concave/zoom
    transitionSpeed: 'default', // default/fast/slow
    backgroundTransition: 'fade', // none/fade/slide/convex/concave/zoom
    viewDistance: 4, // Number of slides away from the current that are visible
    parallaxBackgroundImage: '', // e.g. "'https://s3.amazonaws.com/hakim-static/reveal-js/reveal-parallax-1.jpg'"
    parallaxBackgroundSize: '10000px 10000px', // CSS syntax, e.g. "2100px 900px"
    display: 'block',


    markdown: {
        //            renderer: myrenderer,
        smartypants: true
    }
})
