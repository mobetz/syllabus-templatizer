

export class AgendaDate {

    constructor(hasLecture, hasLab, date) {
        this.date = date;
        this.labHeld = hasLab;
        this.lectureHeld = hasLecture;
        this.topic = "";
        this.unit = "";
        this.assignment = "";
        this.events = [];
    }


    setTopic(newTopic) {
        this.topic = newTopic;
    }
}