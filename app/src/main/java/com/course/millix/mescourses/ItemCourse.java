package com.course.millix.mescourses;


import java.util.Date;

public class ItemCourse {
    private String denomination;
    private Date creationDate;
    private Date doneDate;
    private boolean fait;
    private int quantite;

    public ItemCourse(String denomination, Date creationDate,boolean fait, int quantite) {
        this.denomination = denomination;
        this.creationDate = creationDate;
        this.fait = fait;
        this.quantite = quantite;
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

    public boolean isFait() {
        return fait;
    }

    public void setFait(boolean fait) {
        this.fait = fait;
    }

    public int getQuantite() {
        return quantite;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }
}
