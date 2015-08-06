package Logic;


import java.util.GregorianCalendar;
import java.util.List;

public class Aufgabe {
    private final String[] statusList = {"angefangen","in Bearbeitung", "Angehalten", "Abgeschlossen"};
    final private String[] aufgabenArt = {"Neuprogrammierung", "Bugfix"};
    
    private int aufgabeId;
    private int projectId;
    private String bezeichnung;
    private int prioritaet;
    private int bearbeiterId;
    private int status;
    private GregorianCalendar anfangsDatum;
    private int stundenBudget; // ob hier besser budget in stunden wäre?
    private int ausuebungsOrtId;
// budget? deadline?    längere Beschreibung?
    
    
    private List<String> zusaetzlicheMerkmale;

    public int getAufgabeId() {
        return aufgabeId;
    }

    public void setAufgabeId(int id) {
        this.aufgabeId = id;
    }

    public int getProjectId() {
        return projectId;
    }

    public void setProjectId(int projektId) {
        this.projectId = projektId;
    }
    
    
    public String getBezeichnung() {
        return bezeichnung;
    }

    public void setBezeichnung(String bezeichnung) {
        this.bezeichnung = bezeichnung;
    }

    public int getPrioritaet() {
        return prioritaet;
    }

    public void setPrioritaet(int prioritaet) {
        this.prioritaet = prioritaet;
    }

    public int getBearbeiterId() {
        return bearbeiterId;
    }
    
    public void setBearbeiterId(int bearbeiterID) {
        this.bearbeiterId = bearbeiterID;
    }
    
    public String getStatus() {
        return statusList[this.status];
    }
    
    public void setStatus(int status) {
        this.status = status;
    }
    
    public GregorianCalendar getAnfangsDatum() {
        return anfangsDatum;
    }
    
    public void setAnfangsDatum(GregorianCalendar anfangsDatum) {
        this.anfangsDatum = anfangsDatum;
    }
    
    public int getAbsehbareLaengeInTage() {
        return stundenBudget;
    }
    
    public void setAbsehbareLaengeInTage(int absehbareLaengeInTage) {
        this.stundenBudget = absehbareLaengeInTage;
    }
    
    public int getAusuebungsOrt() {
        return ausuebungsOrtId;
    }
    
    public void setAusuebungsOrt(int ausuebungsOrtId) {
        this.ausuebungsOrtId = ausuebungsOrtId;
    }
    
    public List<String> getZusaetzlicheMerkmale() {
        return zusaetzlicheMerkmale;
    }

    public void zusaetzMerkmalAnhaengen(String zusaetzlichesMerkmal) {
        this.zusaetzlicheMerkmale.add(zusaetzlichesMerkmal);
    }
    
    public void setZusaetzlicheMerkmale(List<String> zusaetzlicheMerkmale) {
        this.zusaetzlicheMerkmale = zusaetzlicheMerkmale;
    }

    public String[] getStatusList() {
        return statusList;
    }

    public String[] getAufgabenArt() {
        return aufgabenArt;
    }
    public Aufgabe(int aufgabeId, int projectId, String bezeichnung, int prioritaet, int bearbeiterId, int status, GregorianCalendar anfangsDatum,
            int absehbareLaengeInTage, int ausuebungsOrtId, List<String> zusaetzlicheMerkmale) {
        super();
        this.aufgabeId = aufgabeId;
        this.projectId = projectId;
        this.bezeichnung = bezeichnung;
        this.prioritaet = prioritaet;
        this.bearbeiterId = bearbeiterId;
        this.status = status;
        this.anfangsDatum = anfangsDatum;
        this.stundenBudget = absehbareLaengeInTage;
        this.ausuebungsOrtId = ausuebungsOrtId;
        this.zusaetzlicheMerkmale = zusaetzlicheMerkmale;
    }
    
    
    public Aufgabe(){
        this.aufgabeId = 0;
        this.projectId = 0;
        this.bezeichnung = "emptyTask";
        this.bearbeiterId = 0;
        this.status = 0;
        this.anfangsDatum = null;
        this.stundenBudget = 0;
        this.ausuebungsOrtId = 0;
        this.zusaetzlicheMerkmale = null;
    };
    
}
