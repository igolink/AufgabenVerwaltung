package Logic;


import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Betrieb{
    
    private Set<Person> personen;
    private Set<Project> projekte;
    private Set<Ort> orte;
    private Set<Aufgabe> aufgaben;


    
    public Set<Person> getPersonen() {
        return personen;
    }

    
    public Set<Project> getProjekte() {
        return projekte;
    }


       public Set<Ort> getOrte() {
        return orte;
    }

    public Set<Aufgabe> getAufgaben() {
        return aufgaben;
    }
    
    
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
    
    public boolean personIdExists(int iD){
        boolean r = false;
        for (Person p:personen){
            if (p.getPersonId() == iD) r = true; break;
        }
        return r;
    }
    

    public boolean personCsvWriter(Person pers){
        boolean alreadyExists = new File("personen.csv").exists();
            
        try {
            BufferedWriter csvOutput = new BufferedWriter(new FileWriter("personen.csv", true), ',');
            
            // if the file didn't already exist then we need to write out the header line
            if (!alreadyExists){
                csvOutput.write(pers.getPersonId());
                csvOutput.write(pers.getVorName());
                csvOutput.write(pers.getNachName());
                csvOutput.write(pers.getGeschlecht());
                csvOutput.write(pers.getDatum().getTime().toString());
                csvOutput.write(pers.getOrtId());
                csvOutput.newLine();
            }
            csvOutput.close();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return true;
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
    
    public String toString(Aufgabe aufgabe){
        return ("AufgabenID: " + aufgabe.getAufgabeId() + "; Bezeichnung: " + aufgabe.getBezeichnung() + 
                "; Projekt: " + getProjectById(aufgabe.getProjectId()).getProjectBezeichnung() + "; " + "Bearbeiter: " + 
                getPersonById(aufgabe.getBearbeiterId()).getName() + ".\n" + "Aufgabenpriorität (1: niedrig bis 10: hoch): " + 
                aufgabe.getPrioritaet() + "; Aufgabenstatus: " + aufgabe.getStatus() + "; Anfang: " + aufgabe.getAnfangsDatum().toString() + 
                "; Ausübungsstandort: " + getOrtById(aufgabe.getAusuebungsOrt()).getBezeichnung());
    }
    
    public void listeDerAufgaben(){
        for (Aufgabe aufgabe:aufgaben){
            System.out.println(toString(aufgabe));
//            System.out.println("AufgabenID: " + aufgabe.getAufgabeId() + "; Bezeichnung: " + aufgabe.getBezeichnung() + "; Projekt: " + getProjectById(aufgabe.getProjectId()).getProjectBezeichnung() + "; " + "Bearbeiter: " + getPersonById(aufgabe.getBearbeiterId()).getName() + ".");
//            System.out.println("Aufgabenpriorität (1: niedrig bis 10: hoch): " + aufgabe.getPrioritaet() + "; Aufgabenstatus: " + aufgabe.getStatus() + "; Anfang: " + aufgabe.getAnfangsDatum().toString() + "; Ausübungsstandort: " + getOrtById(aufgabe.getAusuebungsOrt()).getBezeichnung());
        }
    }
    
    public boolean loescheAufgabe(int iD){
        return this.aufgaben.remove(getAufgabeById(iD));
    }
    
    public boolean aufgabeIdExists(int iD){
        boolean r = false;
        for (Aufgabe a:aufgaben){
            if (a.getAufgabeId() == iD) r = true; break;
//        for (Iterator<Project> i = projekte.iterator(); i.hasNext();){   
//            if (i.next().getProjectId() == iD) r = true; break;
        }
        return r;
    }
 
    public boolean aufgabeCsvWriter(Aufgabe a){
            boolean alreadyExists = new File("aufgaben.csv").exists();
                
            try {
                BufferedWriter csvOutput = new BufferedWriter(new FileWriter("aufgaben.csv", true), ',');
                
                // if the file didn't already exist then we need to write out the header line
                if (!alreadyExists){
                    csvOutput.write(a.getProjectId());
                    csvOutput.write(a.getAufgabeId());
                    csvOutput.write(a.getBezeichnung());
                    csvOutput.write(a.getPrioritaet());
                    csvOutput.write(a.getStatus());
                    csvOutput.write(a.getBearbeiterId());
                    csvOutput.write(a.getAusuebungsOrt());
                    csvOutput.write(a.getAbsehbareLaengeInTage());
                    csvOutput.write(a.getAnfangsDatum().getTime().toString()); //ob es dann das lesen leicht ist
//                    csvOutput.write(a.getAufgabenArt());
                    List<String> sl = a.getZusaetzlicheMerkmale();
                    for (String i:sl){
                        csvOutput.write(i);
                    }
                    csvOutput.newLine();
                }
                csvOutput.close();
            } catch (IOException e) {
                e.printStackTrace();
                return false;
            }
            return true;
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
    
    public boolean ortIdExists(int iD){
        boolean r = false;
        for (Ort a:orte){
            if (a.getOrtId() == iD) r = true; break;
//        for (Iterator<Project> i = projekte.iterator(); i.hasNext();){   
//            if (i.next().getProjectId() == iD) r = true; break;
        }
        return r;
    }
    
    public boolean ortCsvWriter(Ort ort){
        boolean alreadyExists = new File("orte.csv").exists();
            
        try {
            BufferedWriter csvOutput = new BufferedWriter(new FileWriter("orte.csv", true), ',');
            
            // if the file didn't already exist then we need to write out the header line
            if (!alreadyExists){
                csvOutput.write(ort.getOrtId());
                csvOutput.write(ort.getBezeichnung());
                csvOutput.write(ort.getStrasse());
                csvOutput.write(ort.getHausNummer());
                csvOutput.write(ort.getPlz());
                csvOutput.newLine();
            }
            csvOutput.close();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return true;
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
    
    public boolean projectIdExists(int iD){
        boolean r = false;
        for (Project p:projekte){
            if (p.getProjectId() == iD) r = true; break;
//        for (Iterator<Project> i = projekte.iterator(); i.hasNext();){   
//            if (i.next().getProjectId() == iD) r = true; break;
        }
        return r;
    }
    
    public boolean loescheProject(int iD){
        return this.projekte.remove(getProjectById(iD));
    }
    
    public boolean projectCsvWriter(Project prj){
        boolean alreadyExists = new File("projekte.csv").exists();
            
        try {
            BufferedWriter csvOutput = new BufferedWriter(new FileWriter("projekte.csv", true), ',');
            
            if (!alreadyExists){
                csvOutput.write(prj.getProjectId());
                csvOutput.write(prj.getProjectBezeichnung());
                csvOutput.write(prj.getProjectBeschreibung());
                csvOutput.newLine();
            }
            csvOutput.close();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    
    
    
    public Betrieb() {
        super();
        personen = new HashSet<Person>();
        projekte = new HashSet<Project>();
        orte = new HashSet<Ort>();
        aufgaben = new HashSet<Aufgabe>();
        
    }    
}
