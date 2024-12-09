package DAO.Model;

public class Verpackung {

    private int verpackungId;

    private int subVerpackungId;

    private String verpackungName;

    private int anzahlEinheiten;

    public Verpackung() {
    }

    public int getVerpackungId() {
        return verpackungId;
    }

    public void setVerpackungId(int verpackungId) {
        this.verpackungId = verpackungId;
    }

    public int getSubVerpackungId() {
        return subVerpackungId;
    }

    public void setSubVerpackungId(int subVerpackungId) {
        this.subVerpackungId = subVerpackungId;
    }

    public String getVerpackungName() {
        return verpackungName;
    }

    public void setVerpackungName(String verpackungName) {
        this.verpackungName = verpackungName;
    }

    public int getAnzahlEinheiten() {
        return anzahlEinheiten;
    }

    public void setAnzahlEinheiten(int anzahlEinheiten) {
        this.anzahlEinheiten = anzahlEinheiten;
    }
}
