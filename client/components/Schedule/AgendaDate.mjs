

export class AgendaDate {

    constructor(hasLecture, hasLab, date) {
        this.date = date;
        this.labHeld = hasLab;
        this.lectureHeld = hasLecture;
        this.topic = "";
        this.unit = "";
        this.assignment = "";
        this.events = [];
        this.calendarLevel = "0"
        this.topicLevel = "0"
        this.assignmentLevel = "0"
    }



    highlightToClassList(level) {

        let ret = [];
        let level_remainder = level % 1;
        (  level_remainder !== 0) ? ret.push("important") : null;
        level = level - level_remainder;
        if ( level <= -1 ) {
            ret.push("dull");
        } else if ( level === 1) {
            ret.push("bright");
        } else if ( level >= 2) {
            ret.push("off-bright");
        }

        return ret;
    }

    linkTextFromMeeting(text, href, highlight) {
        let meeting = document.createElement("div");
        if ( href ) {
            let link = document.createElement("a");
            link.href = href;
            link.innerText = text;
            meeting.append(link);
        } else if ( text !== undefined ) {
            meeting.innerText = text;
        }
        meeting.classList.add(...this.highlightToClassList(highlight));
        return meeting;
    }

    generateRowFromMeeting() {
        let tr = document.createElement("tr");
        let date_col = document.createElement("td");
        let topic_col = document.createElement("td");
        let calendar_col = document.createElement("td");
        tr.append(date_col, topic_col, calendar_col);

        let dateFormat = new Intl.DateTimeFormat('en', {month: 'short', day: 'numeric'});

        date_col.innerText = dateFormat.format(this.date);


        if ( this.topic ) {
            let lecture =
                this.linkTextFromMeeting(this.topic.topic,  this.topic.href,  this.topic.highlight );
            topic_col.append(lecture);
        }

        if ( this.assignment ) {
            let lab =
                this.linkTextFromMeeting(this.assignment.title, this.assignment.href, this.assignment.highlight);
            topic_col.append(lab);
        }

        this.events.map(
            (e) => {
                let div = document.createElement("div");
                div.innerText = e.event;
                div.classList.add(...this.highlightToClassList(e.highlight))
                return div;
            })
            .forEach((e) => calendar_col.append(e));

        return tr;
    }
}