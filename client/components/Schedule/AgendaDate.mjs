

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


    setTopic(newTopic) {
        this.topic = newTopic;
    }
}