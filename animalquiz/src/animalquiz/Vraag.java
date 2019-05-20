package animalquiz;

public class Vraag {

    public String vraag;
    public String[] keuzes = new String[4];
    public int antwoord;

    public Vraag(String vraag, String keuze1, String keuze2,
            String keuze3, String keuze4, int antwoord) {
        setVraag(vraag);
        setKeuzes(new String[] {keuze1, keuze2, keuze3, keuze4});
        setAntwoord(antwoord);
    }

    public int getAntwoord() {
        return antwoord;
    }

    public void setAntwoord(int antwoord) {
        this.antwoord = antwoord;
    }

    public String[] getKeuzes() {
        return keuzes;
    }

    public void setKeuzes(String[] keuzes) {
        this.keuzes = keuzes;
    }

    public String getVraag() {
        return vraag;
    }

    public void setVraag(String vraag) {
        this.vraag = vraag;
    }
}


