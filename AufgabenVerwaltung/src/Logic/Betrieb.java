package Logic;


import java.util.HashSet;
import java.util.Set;

public class Betrieb {
    
    public Set<Person> personen = new HashSet<Person>();
    public Set<Project> projekte = new HashSet<Project>();
    public Set<Ort> orte = new HashSet<Ort>();
    public Set<Aufgabe> aufgaben = new HashSet<Aufgabe>();

    
//==================================================Personen============================================

    public boolean addierePerson(Person pers){
        return (personen.add(pers));
    }

    
    public Person getPersonById(int personId){
        Person output = new Person();
        for (Person i:personen){
            if (i.getPersonId() == personId) {output = i; break;}
        }
        return output;
    }

    public void listeDerPersonen(){
        for (Person pers:personen){
            System.out.println("PersID: " + pers.getPersonId() + "; Vorname: " + pers.getVorName() + "; Nachname: " + pers.getNachName() + "; Wohnort: " + getOrtById(pers.getOrtId()).getBezeichnung() + ".");
            System.out.println("Beteiligt in folgenden Aufgaben:");
//            for (Aufgabe a:aufgaben){
//                if (a.getBearbeiter() == pers.getPersonId()){
//                    System.out.println("\t Aufgabe:" + a.getBezeichnung() + "; Projekt: " + getProjectById(a.getProjektId()).getProjectBezeichnung() + ".");
//                }
//            }
        }
    }

    public boolean loeschePerson(int iD){
        return this.personen.remove(getPersonById(iD));
    }
    
//==================================================Aufgaben============================================    

    public boolean addiereAufgabe(Aufgabe aufgabe){
        return aufgaben.add(aufgabe);
    }
    
    public Aufgabe getAufgabeById(int aufgabeId){
        Aufgabe output = new Aufgabe();
        for (Aufgabe i:aufgaben){
            if (i.getAufgabeId() == aufgabeId) {output = i; break;}
        }
        return output;
    }
    
    public void listeDerAufgaben(){
        for (Aufgabe aufgabe:aufgaben){
            System.out.println("AufgabenID: " + aufgabe.getAufgabeId() + "; Bezeichnung: " + aufgabe.getBezeichnung() + "; Projekt: " + getProjectById(aufgabe.getProjectId()).getProjectBezeichnung() + "; " + "Bearbeiter: " + getPersonById(aufgabe.getBearbeiterId()).getName() + ".");
            System.out.println("Aufgabenpriorität (1: niedrig bis 10: hoch): " + aufgabe.getPrioritaet() + "; Aufgabenstatus: " + aufgabe.getStatus() + "; Anfang: " + aufgabe.getAnfangsDatum().toString() + "; Ausübungsstandort: " + getOrtById(aufgabe.getAusuebungsOrt()).getBezeichnung());
        }
    }
    
    public boolean loescheAufgabe(int iD){
        return this.aufgaben.remove(getAufgabeById(iD));
    }

    
//==================================================Orte============================================   
    public boolean addiereOrt(Ort ort){
        return orte.add(ort);
    }
    
    public Ort getOrtById(int ortId){
        Ort output = new Ort();
        for (Ort i:orte){
            if (i.getOrtId() == ortId) {output = i; break;}
        }
        return output;
    }
    
    public void listeDerOrte(){
        for (Ort ort:orte){
            ort.printOrt();
        }
    }
    
    
    public boolean loescheOrt(int iD){
        return this.orte.remove(getOrtById(iD));
    }
    
    
//==================================================Projekte============================================    
    
    public boolean adddiereProject(Project prj){
        return projekte.add(prj);
    }

    public Project getProjectById(int projectId){
        Project output = new Project();
        for (Project i:projekte){
            if (i.getProjectId() == projectId) {output = i; break;}
        }
        return output;
    }
    
    public void listeDerProjekte(){
        for (Project projekt:projekte){
            projekt.printProject();
            System.out.println();
        }
    }
    
    public boolean loescheProject(int iD){
        return this.projekte.remove(getProjectById(iD));
    }
    
    public Betrieb() {
        super();
    }    
}
