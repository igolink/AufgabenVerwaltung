package Logic;


import java.util.GregorianCalendar;

public class Person {

    private int personId;

    private String vorName;
    private String nachName;
    private int geschlecht;
    private int ortId;
    private GregorianCalendar userGeburtsDatum;
    private final char[] GESCHLECHT = {'m', 'w'};
    
    public int getPersonId() {
        return personId;
    }
    public void setPersonId(int personId) {
        this.personId = personId;
    }
    
    public String getVorName() {
        return vorName;
    }
    public void setVorName(String vorName) {
        this.vorName = vorName;
    }
    public String getNachName() {
        return nachName;
    }
    public void setNachName(String nacName) {
        this.nachName = nacName;
    }
    public char getGeschlecht() {
        return GESCHLECHT[geschlecht];
    }
    public void setGeschlecht(int geschlecht) {
        this.geschlecht = geschlecht;
    }
    public int getOrtId() {
        return ortId;
    }
    public void setOrt(int ortId) {
        this.ortId = ortId;
    }
    public GregorianCalendar getDatum() {
        return userGeburtsDatum;
    }
    
    
    public void setDatum(GregorianCalendar userGeburtsDatum) {
        this.userGeburtsDatum = userGeburtsDatum;
    }
    
    
    public String getName(){
        return (this.vorName + " " + this.vorName);
    }
    
    public Person(int personId, String vorName, String nachName, int geschlecht, int ortId, GregorianCalendar userGeburtsDatum){
        this.personId = personId;
        this.vorName = vorName;
        this.nachName = nachName;
        this.geschlecht = geschlecht;
        this.ortId = ortId;
        this.userGeburtsDatum = userGeburtsDatum;
    }
    
    public Person() {
        super();
    }
   
    
}