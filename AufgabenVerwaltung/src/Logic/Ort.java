package Logic;

public class Ort {

    private int OrtId;
    private String bezeichnung;
    private String strasse;
    private String hausNummer;
    private String plz;
    
    
    
    public int getOrtId() {
        return OrtId;
    }
    
    public void setOrtId(int ortId) {
        OrtId = ortId;
    }
    
    public String getBezeichnung() {
        return bezeichnung;
    }
    
    public void setBezeichnung(String bezeichnung) {
        this.bezeichnung = bezeichnung;
    }
    
    public String getStrasse() {
        return strasse;
    }
    
    public void setStrasse(String strasse) {
        this.strasse = strasse;
    }
    
    public String getHausNummer() {
        return hausNummer;
    }
    
    public void setHausNummer(String hausNummer) {
        this.hausNummer = hausNummer;
    }
    public String getPlz() {
        return plz;
    }
    public void setPlz(String plz) {
        this.plz = plz;
    }
    
    public void printOrt(){
        System.out.println("OrtID: " + this.getOrtId() + "; Strasse: " + this.getStrasse() + "; Hausnummer: " + this.getHausNummer() + "; PLZ: " + this.getPlz() + "; Ortsname: " + this.getBezeichnung());  
    }
    
    
    public Ort(int ortId, String bezeichnung, String strasse, String hausNummer, String plz) {
        super();
        this.OrtId = ortId;
        this.bezeichnung = bezeichnung;
        this.strasse = strasse;
        this.hausNummer = hausNummer;
        this.plz = plz;
    }
    
    public Ort() {
        super();
    }    
}
