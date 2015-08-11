package Logic;


import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashSet;
import java.util.List;
import java.util.Set;



public class Betrieb{
    
    private Set<Person> personen;
    private Set<Project> projekte;
    private Set<Ort> orte;
    private Set<Aufgabe> aufgaben;
    private final char TRENNZEICHEN = ',';
    private final char ARRAYSEPARATOR = ';';

    
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
    

    public boolean personenCsvWriter(Set<Person> personen){
        boolean alreadyExists = new File("personen.csv").exists();
            
        try {
            if (alreadyExists){
                File f = new File("personen.csv");
                f.delete();
            }
                
            
            BufferedWriter csvOutput = new BufferedWriter(new FileWriter("personen.csv", true), ',');
            
            for (Person pers:personen){
                csvOutput.write(pers.getPersonId());
                csvOutput.write(TRENNZEICHEN);
                csvOutput.write(pers.getVorName());
                csvOutput.write(TRENNZEICHEN);                
                csvOutput.write(pers.getNachName());
                csvOutput.write(TRENNZEICHEN);
                csvOutput.write(pers.getGeschlecht());
                csvOutput.write(TRENNZEICHEN);
                csvOutput.write(pers.getUserGeburtsDatum().getTime().toString());
                csvOutput.write(TRENNZEICHEN);
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
            aufgabe.printAufgabeShort();
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
 
    public boolean aufgabenCsvReader(){
        
        boolean alreadyExists = new File("aufgaben.csv").exists();
             
        if (alreadyExists){
            try {
                BufferedReader csvInput = new BufferedReader(new FileReader("aufgaben.csv"));
                String line;
                while((line = csvInput.readLine()) != null){
                    String[] aufgabe = line.split(",");
                    aufgaben.add(new Aufgabe(Integer.parseInt(aufgabe[0]), Integer.parseInt(aufgabe[1]), aufgabe[2], Integer.parseInt(aufgabe[3]), 
                                  Integer.parseInt(aufgabe[4]), Integer.parseInt(aufgabe[5]), Integer.parseInt(aufgabe[6]), Integer.parseInt(aufgabe[7]),
                                  new GregorianCalendar.setTime((Date)((new SimpleDateFormat("EEE MMM dd HH:mm:ss zzzz yyyy", Locale.GERMAN)).parse(aufgabe[8])))),
                                  null);
                }
                csvInput.close();
//che-to ne rabotaet...                
            } catch (IOException e){
                e.printStackTrace();
                return false;
            }
        }
        else{
            System.out.println("Die Datei \"orte.csv\" existiert nicht!");
            return false;
        }
        return true;
    }
    
    
    public boolean aufgabenCsvWriter(Set<Aufgabe> aufgaben){
        
            boolean alreadyExists = new File("aufgaben.csv").exists();
            
            
            try {
                
                if (alreadyExists){
                    File f = new File("aufgaben.csv");
                    f.delete();
                }
                
                BufferedWriter csvOutput = new BufferedWriter(new FileWriter("aufgaben.csv", true), ',');
                
                // if the file didn't already exist then we need to write out the header line

                    
                    
                for (Aufgabe a:aufgaben){
                    csvOutput.write(a.getAufgabeId());
                    csvOutput.write(TRENNZEICHEN);                    
                    csvOutput.write(a.getProjectId());
                    csvOutput.write(TRENNZEICHEN);
                    csvOutput.write(a.getBezeichnung());
                    csvOutput.write(TRENNZEICHEN);
                    csvOutput.write(a.getPrioritaet());
                    csvOutput.write(TRENNZEICHEN);
                    csvOutput.write(a.getStatus());
                    csvOutput.write(TRENNZEICHEN);
                    csvOutput.write(a.getBearbeiterId());
                    csvOutput.write(TRENNZEICHEN);
                    csvOutput.write(a.getAusuebungsOrt());
                    csvOutput.write(TRENNZEICHEN);
                    csvOutput.write(a.getStundenBudget());
                    csvOutput.write(TRENNZEICHEN);
                    csvOutput.write(a.getAnfangsDatum().getTime().toString()); //ob das lesen dann leicht ist
//                    csvOutput.write(a.getAufgabenArt());
//                    csvOutput.write(TRENNZEICHEN);
//                    List<String> sl = a.getZusaetzlicheMerkmale();
//                    for (String i:sl){
//                        csvOutput.write(i);
//                        csvOutput.write(ARRAYSEPARATOR);
//                     }
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
    
    public boolean ortCsvReader(){
        
        boolean alreadyExists = new File("orte.csv").exists();
        
        if (alreadyExists){
            try {
                BufferedReader csvInput = new BufferedReader(new FileReader("orte.csv"));
                String line;
                while((line = csvInput.readLine()) != null){
                    String[] ort = line.split(",");
                    orte.add(new Ort(Integer.parseInt(ort[0]), ort[1], ort[2], ort[3], ort[4]));
                }
                csvInput.close();
                
            } catch (IOException e){
                e.printStackTrace();
                return false;
            }
        }
        else{
            System.out.println("Die Datei \"orte.csv\" existiert nicht!");
            return false;
        }
        return true;
    }
    
    public boolean orteCsvWriter(Set<Ort> orte){
        boolean alreadyExists = new File("orte.csv").exists();
            
        try {
            if (alreadyExists){
                File f = new File("orte.csv");
                f.delete();
            }

            BufferedWriter csvOutput = new BufferedWriter(new FileWriter("orte.csv", true), ',');
               
            // if the file didn't already exist then we need to write out the header line
            for(Ort ort:orte){
                csvOutput.write(ort.getOrtId());
                csvOutput.write(TRENNZEICHEN);
                csvOutput.write(ort.getBezeichnung());
                csvOutput.write(TRENNZEICHEN);
                csvOutput.write(ort.getStrasse());
                csvOutput.write(TRENNZEICHEN);
                csvOutput.write(ort.getHausNummer());
                csvOutput.write(TRENNZEICHEN);
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
    
    
    public boolean projectCsvReader(){
        
        boolean alreadyExists = new File("projekte.csv").exists();
        
        if (alreadyExists){
            try {
                BufferedReader csvInput = new BufferedReader(new FileReader("projekte.csv"));
                String line;
                while((line = csvInput.readLine()) != null){
                    String[] projekt = line.split(",");
                    projekte.add(new Project(Integer.parseInt(projekt[0]), projekt[1], projekt[2]));
                }
                csvInput.close();
                
            } catch (IOException e){
                e.printStackTrace();
                return false;
            }
        }
        return true;
    }
    
    public boolean projectsCsvWriter(Set<Project> projekte){
        
        boolean alreadyExists = new File("projekte.csv").exists();
            
        try {
            if (alreadyExists){
                File f = new File("projekte.csv");
                f.delete();
            }
            
            BufferedWriter csvOutput = new BufferedWriter(new FileWriter("projekte.csv", true), ',');
            
            for(Project prj:projekte){
                csvOutput.write(prj.getProjectId());
                csvOutput.write(TRENNZEICHEN);
                csvOutput.write(prj.getProjectBezeichnung());
                csvOutput.write(TRENNZEICHEN);
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
