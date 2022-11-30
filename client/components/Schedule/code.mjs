
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
    }


    static get observedAttributes() {
        return ['startdate', 'enddate', 'meetdays', 'labdays'];
    }

    attributeChangedCallback(name, oldValue, newValue) {
        switch (name) {
            case "startdate":
            case "enddate":

                this.generateScheduleGrid();
                break;
        }

    }

    get startDate() {
        return this.getAttribute("startdate");
    }

    set startDate(value) {
        this.setAttribute("startdate", value);
    }

    get endDate() {
        return this.getAttribute("enddate");
    }

    set endDate(value) {
        this.setAttribute("enddate", value);
    }

    get meetDays() {
        return this.getAttribute("meetdays");
    }

    getMeetingDaysByReference() {
        return this.meetDays.split("").map((d) => this.dayReference.indexOf(d))
    }

    getLabDaysByReference() {
        return this.labDays.split("").map((d) => this.dayReference.indexOf(d));
    }

    set meetDays(value) {
        this.setAttribute("meetdays", value);
    }


    get labDays() {
        return this.getAttribute("labdays");
    }

    set labDays(value) {
        this.setAttribute("labdays", value);
    }

    getHolidays() {
        return Array.from(this.shadowRoot.host.querySelectorAll("syllabus-holiday"));
    }

    getEvents() {
        return Array.from(this.shadowRoot.host.querySelectorAll("syllabus-event"));
    }


    dayReference = ["U", "M", "T", "W", "R", "F", "S"]

    static #strongify(text) {
        return `<strong>${text}</strong>`
    }

    generateScheduleGrid() {

        let meetings = this.getMeetingDays();

        let grid = this.shadowRoot.querySelector("#schedule-grid");
        grid.innerHTML = ""

        meetings.forEach((meeting) => {

            let tr = document.createElement("tr");
            let date_col = document.createElement("td");
            let topic_col = document.createElement("td");
            let calendar_col = document.createElement("td");
            tr.append(date_col, topic_col, calendar_col);

            let dateFormat = new Intl.DateTimeFormat('en', {month: 'short', day: 'numeric'});

            date_col.innerText = dateFormat.format(meeting.date);
            topic_col.innerHTML = [meeting.topic, Schedule.#strongify(meeting.assignment)].join("<br>");
            calendar_col.innerHTML = Schedule.#strongify(meeting.events.join("\n"));

            grid.appendChild(tr);
        })





    }


    getMeetingDays() {
        let topics = Array.from(this.shadowRoot.host.querySelectorAll("syllabus-lecture"))
            .map((t) => t.topic); //TODO: put unit in here if present
        let labs = Array.from(this.shadowRoot.host.querySelectorAll("syllabus-assignment"))
            .map((l) => l.title);

        //TODO: better solution for off by one day (timezones) Also fix Holiday and Event
        let startDate = new Date(Date.parse(this.startDate));
        startDate.setDate(startDate.getDate() + 1);
        startDate.setHours(0);
        startDate.setMinutes(0);
        let endDate = new Date(Date.parse(this.endDate));
        endDate.setDate(endDate.getDate() + 1);
        endDate.setHours(0);
        endDate.setMinutes(0);

        let holidays = this.getHolidays()
        let events = this.getEvents()

        let meetingDays = this.getMeetingDaysByReference()
        let labDays = this.getLabDaysByReference()

        let currentDate = new Date(startDate);
        let meetings = [];
        while ( currentDate <= endDate) {
            let possibleHoliday = holidays
                .filter(
                    (h)=> //TODO: there seems to be a race condition with getDateAsDateTime() being undefined
                        h.getDateAsDateTime().toDateString() === currentDate.toDateString()
                         || (h.getStartAsDateTime() <= currentDate && h.getEndAsDateTime() >= currentDate )
                );
            let possibleEvent = events.filter( (e)=> e.getDateAsDateTime().toDateString() === currentDate.toDateString());
            let isHoliday = possibleHoliday.length > 0;
            let isEvent = possibleEvent.length > 0;

            let meetsToday = meetingDays.indexOf(currentDate.getDay()) !== -1;
            let labToday = labDays.indexOf(currentDate.getDay()) !== -1
            let lectureHeld = ( meetsToday && !isHoliday);
            let labHeld = ( labToday && !isHoliday);

            if (meetsToday || labToday || isEvent) {
                let meeting = new AgendaDate(lectureHeld, labHeld, new Date(currentDate));
                meetings.push(meeting);
                possibleHoliday.forEach((h)=>meeting.events.push(h.event));
                possibleEvent.forEach((e)=>meeting.events.push(e.event));
                if ( isHoliday ) {
                    meeting.topic = "NO LECTURE"
                }
                if ( lectureHeld ) {
                    meeting.topic = topics.shift() || "UNSCHEDULED";
                }
                if ( labHeld ) {
                    meeting.assignment = labs.shift() || "UNSCHEDULED";
                }
                if ( isEvent && !meetsToday && !labToday) {
                    meeting.topic = "";
                }
            }

            currentDate.setDate(currentDate.getDate() + 1);
        }

        return meetings;
    }
}


customElements.define('syllabus-schedule', Schedule);