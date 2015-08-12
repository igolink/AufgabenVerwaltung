package Logic;

//import Logic.Aufgabe;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Set;



public class Betrieb{
    
    private Set<Person> personen;
    private Set<Project> projekte;
    private Set<Ort> orte;
    private Set<Aufgabe> aufgaben;
    private final char TRENNZEICHEN = ',';
    private final char ARRAY_SEPARATOR = ';';
    
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
            System.out.println("PersID: " + pers.getPersonId() + 
                             "; Vorname: " + pers.getVorName() + 
                             "; Nachname: " + pers.getNachName() + 
                             "; Wohnort: " + getOrtById(pers.getOrtId()).getBezeichnung() + ".");
            
            System.out.println("Beteiligt in folgenden Aufgaben:");
//            for (Aufgabe a:aufgaben){
//                if (a.getBearbeiter() == pers.getPersonId()){
//                    System.out.println("\t Aufgabe:" + a.getBezeichnung() + 
//                                        "; Projekt: " + getProjectById(a.getProjektId()).getProjectBezeichnung() + 
//                                        ".");
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
            if (p.getPersonId() == iD){ 
                r = true; 
                break;
            }
        }
        return r;
    }

    public boolean personenCsvReader(){
        
        boolean fileAlreadyExists = new File("personen.csv").exists();
             
        if (fileAlreadyExists){
            try {
                BufferedReader csvInput = new BufferedReader(new FileReader("personen.csv"));
                String row;
                GregorianCalendar birthDate = new GregorianCalendar();
                
                while((row = csvInput.readLine()) != null){
                    String[] personRow = row.split(",");
 
                    try{
                        birthDate.setTime(new SimpleDateFormat("EEE MMM dd HH:mm:ss zzzz yyyy", Locale.ENGLISH).parse(personRow[5]));
                    }catch(ParseException p){
                        System.out.println("Falscher Datenformat, so ein Mist!");
                        csvInput.close();
                        return false;
                    }

                    personen.add(new Person(Integer.parseInt(personRow[0]), 
                                             personRow[1], 
                                             personRow[2], 
                                             Integer.parseInt(personRow[3]),
                                             Integer.parseInt(personRow[4]),
                                             birthDate));
                                             
                }
                    csvInput.close();
                }catch(IOException e){
                    e.printStackTrace();
                    return false;
                }
        }
        else{
            System.out.println("Die Datei \"personen.csv\" existiert nicht!");
            return false;
        }
        return true;
    }

    

    public boolean personenCsvWriter(Set<Person> personen){
        boolean alreadyExists = new File("personen.csv").exists();
            
        try {
            if (alreadyExists){
                File f = new File("personen.csv");
                f.delete();
            }
            
            BufferedWriter csvOutput = new BufferedWriter(new FileWriter("personen.csv", true));
            
            for (Person pers:personen){
                csvOutput.write(String.valueOf(pers.getPersonId()));
                csvOutput.write(TRENNZEICHEN);
                csvOutput.write(pers.getVorName());
                csvOutput.write(TRENNZEICHEN);                
                csvOutput.write(pers.getNachName());
                csvOutput.write(TRENNZEICHEN);
                csvOutput.write(String.valueOf(pers.getGeschlecht()));
                csvOutput.write(TRENNZEICHEN);
                csvOutput.write(String.valueOf(pers.getOrtId()));
                csvOutput.write(TRENNZEICHEN);
                csvOutput.write(pers.getUserGeburtsDatum().getTime().toString());                
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
        return ("AufgabenID: " + aufgabe.getAufgabeId() + 
              "; Bezeichnung: " + aufgabe.getBezeichnung() + 
              "; Projekt: " + getProjectById(aufgabe.getProjectId()).getProjectBezeichnung() + 
              "; Bearbeiter: " + getPersonById(aufgabe.getBearbeiterId()).getName() + 
            ".\n Aufgabenpriorität (1: niedrig bis 10: hoch): " + aufgabe.getPrioritaet() + 
              "; Aufgabenstatus: " + aufgabe.getStatusAsString() + "; Anfang: " + aufgabe.getAnfangsDatum().toString() + 
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
        
        boolean fileAlreadyExists = new File("aufgaben.csv").exists();
             
        if (fileAlreadyExists){
            try {
                BufferedReader csvInput = new BufferedReader(new FileReader("aufgaben.csv"));
                String row;
                List<String> dummy = null;
                GregorianCalendar taskDateOfStart = new GregorianCalendar();
                
                while((row = csvInput.readLine()) != null){
                    String[] taskRow = row.split(",");
 
                    try{
                        taskDateOfStart.setTime(new SimpleDateFormat("EEE MMM dd HH:mm:ss zzzz yyyy", Locale.ENGLISH).parse(taskRow[6]));
                    }catch(ParseException p){
                        System.out.println("Falscher Datenformat, so ein Mist!");
                        csvInput.close();
                        return false;
                    }

                    aufgaben.add(new Aufgabe(Integer.parseInt(taskRow[0]), 
                                             Integer.parseInt(taskRow[1]), 
                                             taskRow[2], 
                                             Integer.parseInt(taskRow[3]), 
                                             Integer.parseInt(taskRow[4]), 
                                             Integer.parseInt(taskRow[5]), 
                                             taskDateOfStart, 
                                             Integer.parseInt(taskRow[7]), 
                                             Integer.parseInt(taskRow[8]), 
                                             dummy));
                    
                }
                    csvInput.close();
                }catch(IOException e){
                    e.printStackTrace();
                    return false;
                }
            
        }
        else{
            System.out.println("Die Datei \"aufgaben.csv\" existiert nicht!");
            return false;
        }
        return true;
    }
 
    public boolean aufgabenCsvWriter(Set<Aufgabe> aufgaben){
        
        boolean fileAlreadyExists = new File("aufgaben.csv").exists();
            
        try {
            if (fileAlreadyExists){
                File f = new File("aufgaben.csv");
                f.delete();
            }
                
            BufferedWriter csvOutput = new BufferedWriter(new FileWriter("aufgaben.csv", true));

            for (Aufgabe a:aufgaben){
                csvOutput.write(String.valueOf(a.getAufgabeId()));
                csvOutput.write(TRENNZEICHEN);                    
                csvOutput.write(String.valueOf(a.getProjectId()));
                csvOutput.write(TRENNZEICHEN);
                csvOutput.write(a.getBezeichnung());
                csvOutput.write(TRENNZEICHEN);
                csvOutput.write(String.valueOf(a.getPrioritaet()));
                csvOutput.write(TRENNZEICHEN);
                csvOutput.write(String.valueOf(a.getBearbeiterId()));
                csvOutput.write(TRENNZEICHEN);
                csvOutput.write(String.valueOf(a.getStatus()));
                csvOutput.write(TRENNZEICHEN);
                csvOutput.write(a.getAnfangsDatum().getTime().toString());                     
                csvOutput.write(TRENNZEICHEN);
                csvOutput.write(String.valueOf(a.getStundenBudget()));
                csvOutput.write(TRENNZEICHEN);
                csvOutput.write(String.valueOf(a.getAusuebungsOrt()));
//                csvOutput.write(TRENNZEICHEN);
//                csvOutput.write(a.getAufgabenArt());
//                    csvOutput.write(TRENNZEICHEN);
//                    List<String> sl = a.getZusaetzlicheMerkmale();
//                    for (String i:sl){
//                        csvOutput.write(i);
//                        csvOutput.write(ARRAY_SEPARATOR);
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
    
    public boolean replaceAufgabe(Aufgabe neueAufgabe){
        int iD = neueAufgabe.getAufgabeId();
        for(Aufgabe temp:aufgaben){
            if (temp.getAufgabeId() == iD){
                aufgaben.remove(temp);              //evtl. loescheAufgabe hier nutzen
                aufgaben.add(neueAufgabe);
                return true;
            }
        }
        System.out.println("Austauschen ungelungen.");
        return false;
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
            BufferedWriter csvOutput = new BufferedWriter(new FileWriter("orte.csv", true));
               
            for(Ort ort:orte){
                csvOutput.write(String.valueOf(ort.getOrtId()));
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
    
    public boolean orteCsvReader(){
        
        boolean fileAlreadyExists = new File("orte.csv").exists();
             
        if (fileAlreadyExists){
            try {
                BufferedReader csvInput = new BufferedReader(new FileReader("orte.csv"));
                String line;
                
                while((line = csvInput.readLine()) != null){
                    String[] ortsLine = line.split(",");

                    orte.add(new Ort(Integer.parseInt(ortsLine[0]), 
                                     ortsLine[1], 
                                     ortsLine[2], 
                                     ortsLine[3],
                                     ortsLine[4]));
                                             
                }
                    csvInput.close();
                }catch(IOException e){
                    e.printStackTrace();
                    return false;
                }
        }
        else{
            System.out.println("Die Datei \"ort.csv\" existiert nicht!");
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
    
    public boolean projectsCsvWriter(Set<Project> projekte){
        
        boolean alreadyExists = new File("projekte.csv").exists();
            
        try {
            if (alreadyExists){
                File f = new File("projekte.csv");
                f.delete();
            }
            
            BufferedWriter csvOutput = new BufferedWriter(new FileWriter("projekte.csv", true));
            
            for(Project prj:projekte){
                csvOutput.write(String.valueOf(prj.getProjectId()));
                csvOutput.write(TRENNZEICHEN);
                csvOutput.write(String.valueOf(prj.getProjectBezeichnung()));
                csvOutput.write(TRENNZEICHEN);
                csvOutput.write(String.valueOf(prj.getProjectBeschreibung()));
                csvOutput.newLine();
            }
            csvOutput.close();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public void tasksInProjectEnthalten(int projectId){
        System.out.println("Projekt: " + getProjectById(projectId).getProjectBezeichnung() + ". Enthält Aufgaben:");
        for (Aufgabe a:aufgaben){
            if (a.getProjectId() == projectId){
                a.printAufgabeShort();
            }
            
        }
    }

    public boolean projekteCsvReader(){
        
        boolean fileAlreadyExists = new File("projekte.csv").exists();
             
        if (fileAlreadyExists){
            try {
                BufferedReader csvInput = new BufferedReader(new FileReader("projekte.csv"));
                String line;
                
                while((line = csvInput.readLine()) != null){
                    String[] projectRow = line.split(",");

                    projekte.add(new Project(Integer.parseInt(projectRow[0]), 
                                             projectRow[1], 
                                             projectRow[2]));
                }
                    csvInput.close();
                }catch(IOException e){
                    e.printStackTrace();
                    return false;
                }
        }
        else{
            System.out.println("Die Datei \"projekte.csv\" existiert nicht!");
            return false;
        }
        return true;
    }
    
//======================================Betrieb========================================
    public void readBetrieb(){
        personenCsvReader();
        projekteCsvReader();
        orteCsvReader();
        aufgabenCsvReader();
    }
    
    
    
    
    public Betrieb(){
        super();
        personen = new HashSet<Person>();
        projekte = new HashSet<Project>();
        orte = new HashSet<Ort>();
        aufgaben = new HashSet<Aufgabe>();
    }
}
