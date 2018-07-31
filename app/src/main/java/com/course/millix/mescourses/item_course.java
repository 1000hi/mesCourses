package com.course.millix.mescourses;


import java.util.Date;

public class item_course {
    private String denomination;
    private Date creationDate;
    private Date doneDate;

    public item_course(String denomination, Date creationDate) {
        this.denomination = denomination;
        this.creationDate = creationDate;
    }

    public String getDenomination() {
        return denomination;
    }

    public void setDenomination(String denomination) {
        this.denomination = denomination;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public Date getDoneDate() {
        return doneDate;
    }

    public void setDoneDate(Date doneDate) {
        this.doneDate = doneDate;
    }
}
