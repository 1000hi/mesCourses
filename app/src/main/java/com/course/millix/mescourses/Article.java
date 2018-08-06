package com.course.millix.mescourses;


public class Article {
    private String denomination;
    private String doneDate;
    private boolean fait;
    private int quantite;
    private float prix;

    public Article(String denomination, String creationDate, boolean fait, int quantite) {
        this.denomination = denomination;
        this.doneDate = creationDate;
        this.fait = fait;
        this.quantite = quantite;
    }

    public String getDenomination() {
        return denomination;
    }

    public void setDenomination(String denomination) {
        this.denomination = denomination;
    }

    public String getDoneDate() {
        return doneDate;
    }

    public void setDoneDate(String doneDate) {
        this.doneDate = doneDate;
    }

    public int getQuantite() {
        return quantite;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }

    public float getPrix() {
        return prix;
    }

    public void setPrix(float prix) {
        this.prix = prix;
    }
}
