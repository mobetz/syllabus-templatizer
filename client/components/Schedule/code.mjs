
import {initializeContentLocation, buildComponent} from "../setup-component.js";
import {AgendaDate} from './AgendaDate.mjs';

let content = await initializeContentLocation(import.meta.url);

class Schedule extends HTMLElement {
    constructor() {
        super();
        this.attachShadow({mode: 'open'});
        buildComponent(content, this.shadowRoot);
    }

    connectedCallback() {
        this.computeSchedule();
    }


    static get observedAttributes() {
        return ['startdate', 'enddate', 'meetdays', 'labdays'];
    }

    attributeChangedCallback(name, oldValue, newValue) {

    }

    get startDate() {
        return this.getAttribute("startdate");
    }

    set startDate(value) {
        this.setAttribute("startdate");
    }

    get endDate() {
        return this.getAttribute("enddate");
    }

    set endDate(value) {
        this.setAttribute("enddate");
    }

    get meetDays() {
        return this.getAttribute("meetdays");
    }

    set meetDays(value) {
        this.setAttribute("meetdays");
    }


    get labDays() {
        return this.getAttribute("labdays");
    }

    set labDays(value) {
        this.setAttribute("labdays");
    }


    dayReference = ["U", "M", "T", "W", "R", "F", "S"]

    computeSchedule() {

        let meetings = this.getMeetingDays();

        let topics = this.shadowRoot.host.querySelectorAll("syllabus-lecture").entries();
        let labs = this.shadowRoot.host.querySelectorAll("syllabus-assignment").entries();

        let holidays = this.shadowRoot.host.querySelectorAll("syllabus-holiday");
        let events = this.shadowRoot.host.querySelectorAll("syllabus-event");

        for ( let next_meeting of meetings ) {
            if (next_meeting.lectureHeld ) {
                let next_topic = topics.next();
                next_meeting.topic = !next_topic.done ? next_topic.value[1].topic : "UNSCHEDULED";
            }

            if ( next_meeting.labHeld ) {
                let next_assignment = labs.next();
                next_meeting.assignment = !next_assignment.done ? next_assignment.value[1].title : "UNSCHEDULED";
            }

            //TODO: events
        }

        return meetings;

    }


    getMeetingDays() {

        let startDate = new Date(Date.parse(this.startDate)); //TODO: better solution for off by one day (timezones)
        startDate.setDate(startDate.getDate() + 1);
        let endDate = new Date(Date.parse(this.endDate));
        endDate.setDate(endDate.getDate() + 1);

        let meetDays = this.meetDays.split("").map((d) => this.dayReference.indexOf(d));
        let labDays = this.labDays.split("").map((d) => this.dayReference.indexOf(d));

        let currentDate = new Date(startDate);
        let meetings = [];
        while ( currentDate <= endDate) {
            let isHoliday = false; //TODO: holidays
            let lectureHeld = (meetDays.indexOf(currentDate.getDay()) !== -1 && !isHoliday);
            let labHeld = (labDays.indexOf(currentDate.getDay()) !== -1 && !isHoliday);

            if (lectureHeld || labHeld) {
                meetings.push(new AgendaDate(lectureHeld, labHeld, new Date(currentDate)));
            }

            currentDate.setDate(currentDate.getDate() + 1);
        }

        return meetings;
    }
}


customElements.define('syllabus-schedule', Schedule);