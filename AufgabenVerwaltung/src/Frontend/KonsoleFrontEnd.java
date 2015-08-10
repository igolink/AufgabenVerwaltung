package Frontend;

import java.util.GregorianCalendar;
import java.util.Scanner;

import Logic.Aufgabe;
import Logic.Betrieb;
import Logic.Ort;
import Logic.Person;
import Logic.Project;

public class KonsoleFrontEnd {
    private static int ortIdZaehler = 1;
    private static int personIdZaehler = 1;
    private static int aufgabenIdZaehler = 1;
    private static int projektIdZaehler = 1;
    private static Betrieb betrieb;
    private static Scanner scan = new Scanner(System.in); 

    public static void help(){
        System.out.println("    newtask -- eine neue Aufgabe erstellen (Kentnis der ProjektID erforderlich)");
        System.out.println("     newprj -- ein neues Projekt erstellen");
        System.out.println("   printprj -- Liste aller Projekten ausgeben");
        System.out.println(" printtasks -- Liste aller Tasks ausgeben");
        System.out.println(" printstuff -- Liste aller Mitarbeiter ausgeben");
        System.out.println("printplaces -- Liste aller Orte ausgeben");
        System.out.println("    taskprj -- Aufgaben in einem Projekt auflisten (Kentnis der ProjektID erforderlich)");
        System.out.println("    deltask -- Löschen einer Aufgabe (Kentniss der AufgabeID erforderlich)");
        System.out.println(" updatetask -- Änderung einer Aufgabe (Kentniss der AufgabeID erforderlich)");
    }
    
//==================================================Ort=====================================================    
    public static void addiereNeuenOrtAusDerKonsole(int iD){
        boolean abbruch = false;
        String inData;
//        Scanner scan = new Scanner(System.in);
        Ort ort = new Ort();
        ort.setOrtId(iD);
        
        while(!abbruch){//Ortsname
            System.out.println("Geben Sie bitte den Name des Ortes ein ( \"exit\" für Abbruch):");
            inData = scan.nextLine();
            System.out.println("Sie haben eingegeben: " + inData );
            if (inData.toLowerCase().equals("exit")){
                abbruch = true; 
                break;
                }
            
            if (!inData.isEmpty()){
                ort.setBezeichnung(inData.toUpperCase()); //substring(0, 1).toUpperCase() + inData.substring(1).toLowerCase());
                break;
            }
            badInputMessage();
        }
      
        while(!abbruch){//Strasse
            System.out.println("Geben Sie bitte den Strassennamen ein ( \"exit\" für Abbruch):");
            inData = scan.nextLine();
            System.out.println("Sie haben eingegeben: " + inData );
            
            if (inData.toLowerCase().equals("exit")){
                abbruch = true; 
                break;
            }
                
            if (!inData.isEmpty()){
                ort.setStrasse(inData.toUpperCase());
                break;
            }
            badInputMessage();
        }
            
        while(!abbruch){//Hausnummer
            System.out.println("Geben Sie bitte die Hausnummer ein ( \"exit\" für Abbruch):");
            inData = scan.nextLine();
            System.out.println("Sie haben eingegeben: " + inData );
            
            if (inData.toLowerCase().equals("exit")){
                abbruch = true; 
                break;
            }
            
            if (!inData.isEmpty() && (Character.digit(inData.charAt(0), 10) >= 0)){
                ort.setHausNummer(inData.toLowerCase());
                break;
            }
            badInputMessage();  
                        
        }
            
        while(!abbruch){ //OrtsPLZ
            System.out.println("Geben Sie bitte die PLZ ein ( \"exit\" für Abbruch):");
            inData = scan.nextLine();
            System.out.println("Sie haben eingegeben: " + inData );
                    
            if (inData.toLowerCase().equals("exit")){
                abbruch = true; 
                break;}
            if (!inData.isEmpty() && (inData.length() == 5) && areDigits(inData)){
                ort.setPlz(inData);
                break;
            }
            badInputMessage();
            
        }
        if (!abbruch){
            betrieb.addiereOrt(ort);
            betrieb.ortCsvWriter(ort);
            System.out.println();
            System.out.println("Ein neues Ort wurde erfolgreich eingetragen!");
            System.out.println();
        }
        else System.out.println("Vorgang abgebrochen");
            
//            scan.close();
    }
    
//==================================================Person=====================================================

    public static void addiereNeuePersonAusDerKonsole(int iD){
        boolean abbruch = false;
        String inData;
//        Scanner scan = new Scanner(System.in);
        Person person = new Person();
        person.setPersonId(iD);
        
        while(!abbruch){ //Vorname
            System.out.println("Geben Sie bitte den Vornamen der Person ein ( \"exit\" für Abbruch):");
            inData = scan.nextLine();
            System.out.println("Sie haben eingegeben: " + inData );
            if (inData.toLowerCase().equals("exit")){
                abbruch = true; 
                break;}
            
            if (!inData.isEmpty()){
                person.setVorName(inData.toUpperCase());
                break;
            }
            badInputMessage();
        }
        

        while(!abbruch){ //Nachname
            System.out.println("Geben Sie bitte den Nachnamen der Person ein ( \"exit\" für Abbruch):");
            inData = scan.nextLine();
            System.out.println("Sie haben eingegeben: " + inData );
            if (inData.toLowerCase().equals("exit")){
                abbruch = true; 
                break;
                }
            if (!inData.isEmpty()){
                person.setNachName(inData.toUpperCase());
                break;
            }
            badInputMessage();       
        }

        while(!abbruch){ //Geschlechtseingabe
            System.out.println("Geben Sie bitte für Geschlechtsangabe \"0\" (männlich) oder \"1\" (weiblich) ein (\"exit\" für Abbruch):");
            
            inData = scan.nextLine();
            System.out.println("Sie haben eingegeben: " + inData );

            if (inData.toLowerCase().equals("exit")){
                abbruch = true; 
                break;
            }
                
            if (!inData.isEmpty() && (inData.length() == 1) 
                                  && (Character.digit(inData.charAt(0), 10) >= 0) 
                                  && (Integer.parseInt(inData) <= 1)){
                
                person.setGeschlecht(Integer.parseInt(inData));
                break;
            }
            badInputMessage();       
        }

        while(!abbruch){ //Geburtsdatumseingabe
            System.out.println("Geben Sie bitte das Geburtsdatum der Person ein: ");    //überarbeiten, ohne abbruchmöglichkeit schlecht
            person.setDatum(dateEingabe());                                             //hier boolean zurückliefern (erfolg/abbruch)
            break;
                
            }
        while(!abbruch){ //Wohnorteingabe
            boolean neuesOrt = false;
            if (betrieb.getOrte().isEmpty()){
                System.out.println("Die Liste der Orte ist in Moment leer. Bitte \"n\" für neues Ort eingeben, jede andere Taste fürs Abbrechen.");
                neuesOrt = true;
            }
            else{
                betrieb.listeDerOrte();
                System.out.println("Geben Sie bitte das ID des Wohnortes des Mitarbeiters ein, erstellen Sie dafür einen neuen Wohnorteintrag (\"n\"), oder \"exit\" für Abbruch:");
            }
                        
            inData = scan.nextLine();
            inData = scan.nextLine();
            System.out.println("Sie haben eingegeben: " + inData );

            if ((neuesOrt && !inData.toLowerCase().equals("n")) || inData.toLowerCase().equals("exit")){
                abbruch = true; 
                break;
            }

            if (inData.toLowerCase().equals("n")){
                addiereNeuenOrtAusDerKonsole(ortIdZaehler);
                person.setOrt(ortIdZaehler++);
                break;
            }
            
            if (betrieb.ortIdExists(Integer.parseInt(inData))){
                person.setOrt(Integer.parseInt(inData));
                break;
            }
            
            badInputMessage();
        }
        if(!abbruch) {
            betrieb.addierePerson(person);
            betrieb.personCsvWriter(person);
            System.out.println();
            System.out.println("Eine neue Person wurde erfolgreich eingetragen!");
            System.out.println();
        }
        else System.out.println("Vorgang abgebrochen");
    }
    
// ==================================================Projekt=====================================================
    
    public static void addiereNeuesProjektAusDerKonsole(int iD){
        boolean abbruch = false;
        String inData;
        Project projekt = new Project();
        projekt.setProectID(iD);
        
        while(!abbruch){ //Projektbezeichnung
            System.out.println("Geben Sie bitte die Bezeichnung des Projektes ein ( \"exit\" für Abbruch):");
            inData = scan.nextLine();
            System.out.println("Sie haben eingegeben: " + inData );
 
            if (inData.toLowerCase().equals("exit")){
                abbruch = true; 
                break;
            }
            
            if (!inData.isEmpty()){
                projekt.setProjectBezeichnung(inData.substring(0, 1).toUpperCase() + inData.substring(1).toLowerCase());
                break;
            }
            
            badInputMessage();
            
        }
        while(!abbruch){ //Projektbeschreibung
            System.out.println("Geben Sie bitte die kurze Beschreibung des Projektes ein ( \"exit\" für Abbruch):");
            inData = scan.nextLine();
            System.out.println("Sie haben eingegeben: " + inData );
            if (inData.toLowerCase().equals("exit")){
                abbruch = true; 
                break;
            }
                
            if (!inData.isEmpty()){
                projekt.setProjectBeschreibung(inData.substring(0, 1).toUpperCase() + inData.substring(1).toLowerCase());
                break;
            }
            badInputMessage();       
        }
        if (!abbruch){
            betrieb.adddiereProject(projekt);
            if (betrieb.projectCsvWriter(projekt)) System.out.println("Projekt gespeichert.");
            else System.out.println("Kein Erfolg beim Speichern des Projektes. Mist.");
            System.out.println();
            System.out.println("Ein neues Projekt wurde erfolgreich eingetragen!");
            System.out.println();

        }
        else System.out.println("Vorgang abgebrochen. Kein neues Projekt eingetragen.");
    }
    
// ==================================================Aufgabe=====================================================
    public static void addiereNeueAufgabeAusDerKonsole(int iD){
        boolean abbruch = false;
        boolean neuesProject = false;
        String inData;
        Aufgabe aufgabe = new Aufgabe();
        aufgabe.setAufgabeId(iD);
        if (betrieb.getProjekte().isEmpty()){
            while(!abbruch){
                System.out.println("Es gibt in Moment keine Projekte, zu den die neue Aufgabe gehören konnte, was das Erstellen einer Aufgabe verhindert.\n "
                                   + "Wollen Sie ein neues Projekt für die Aufgabe gleich anlegen? (\"j\" für \"Ja\", jede andere Taste für \"Nein\" (Abbruch)):");
                inData = scan.nextLine();
                if (inData.toLowerCase().equals("j")){
                    addiereNeuesProjektAusDerKonsole(projektIdZaehler); 
                    aufgabe.setProjectId(projektIdZaehler++);
                    neuesProject = true;
                    break;
                    }
                else {System.out.println("Das Erstellen der neuen Aufgabe ist unmöglich."); abbruch = true; break;}
            }
        }

        while(!abbruch && !neuesProject){
            betrieb.listeDerProjekte();
            System.out.println("Geben Sie bitte die ProjektID ein (\"exit\" für Abbruch):");
            inData = scan.nextLine();
            System.out.println("Sie haben eingegeben: " + inData );
            if (inData.toLowerCase().equals("exit")){   //case hier besser?
                abbruch = true; 
                break;
                }

            if (areDigits(inData) && betrieb.projectIdExists(Integer.parseInt(inData))){ 
                aufgabe.setProjectId(Integer.parseInt(inData)); 
                break;
            }
            badInputMessage();
                           
        }

        while(!abbruch){
            System.out.println("Geben Sie bitte die Bezeichnung der Aufgabe ein, \"exit\" für Abbruch:");
            inData = scan.nextLine();
            System.out.println("Sie haben eingegeben: " + inData );
            if (inData.toLowerCase().equals("exit")){
                abbruch = true; 
                break;
            }
            if (!inData.isEmpty()){
                aufgabe.setBezeichnung(inData); 
                break;
            } 
            else{
                System.out.println("Leere Eingabe, bitte versuchen Sie es nochmal.");
                System.out.println();
            }
        }   

        while(!abbruch){            
            System.out.println("Geben Sie bitte die Priorität der Aufgabe von 1 (niedrig) bis 10 (sehr wichtig) ein oder \"exit\" für Abbruch:");
            inData = scan.nextLine();
            System.out.println("Sie haben eingegeben: " + inData);
            if (inData.toLowerCase().equals("exit")){
                abbruch = true; 
                break;
            }
            
            if (areDigits(inData)){
            int i = Integer.parseInt(inData);
                if ((i > 0) && (i <= 10)){
                    aufgabe.setPrioritaet(i);
                    break;
                }
            }
            System.out.println("Aufgabe nicht korrekt. Bitte geben Sie eine Zahl zwischen 1 und 10 ein.");
        }

        while(!abbruch){
            boolean erstePerson = false;
            if (betrieb.getPersonen().isEmpty()){ //noch keine Personen in der Personen-Tabelle
                System.out.println("Es gibt in Moment keine Personen, den die neue Aufgabe zugeordnet werden konnte, was das Erstellen einer Aufgabe verhindert.\n "
                        + "Wollen Sie eine neue Person für die Aufgabe gleich registrieren? (\"j\" für \"Ja\", jede andere Taste für \"Nein\" (Abbruch)): ");

                inData = scan.nextLine();
                System.out.println("Sie haben eingegeben: " + inData);
                
                if (!inData.toLowerCase().equals("j")){
                    abbruch = true; 
                    break;
                }
                else erstePerson = true;
                
            }else{ // es gibt schon Personen in der Personen-Tabelle
            
                betrieb.listeDerPersonen();
            
                System.out.println("Bearbeiter-Eingabe. Geben Sie die PersonalID eines Bearbeiters.\n"
                                + "Um einen neuen Mitarbeiter für diese Aufgabe zu registrieren geben Sie \"n\", \"exit\" für Abbruch: ");
                inData = scan.nextLine();
                System.out.println("Sie haben eingegeben: " + inData);
            
                if (inData.toLowerCase().equals("exit")){
                    abbruch = true; 
                    break;
                }
            }
            
            if (erstePerson || inData.toLowerCase().equals("n")){
                addiereNeuePersonAusDerKonsole(personIdZaehler);
                aufgabe.setBearbeiterId(personIdZaehler++);
                break;
            }
            else{
                if (areDigits(inData) && betrieb.personIdExists(Integer.parseInt(inData))){
                    aufgabe.setBearbeiterId(Integer.parseInt(inData));
                    break;
                }    
            }
            badInputMessage();
        }

        while(!abbruch){
            System.out.println("Das Anfangsdatum der neuen Aufgabe. Geben Sie bitte \"heute\", \"date\" oder \"exit\" für Abbruch:");
            inData = scan.nextLine();
            System.out.println("Sie haben eingegeben: " + inData );
            if (inData.toLowerCase().equals("exit")){
                abbruch = true; 
                break;
            }
            if (inData.toLowerCase().equals("heute")){
                aufgabe.setAnfangsDatum(new GregorianCalendar()); 
                break;
            }
            else{
                if (inData.toLowerCase().equals("date")){
                    aufgabe.setAnfangsDatum(dateEingabe());
                    break;
                }
            } 
            badInputMessage();
        }
    
            
        while(!abbruch){ //hier war am Anfang die absehbare Länge in Tagen. Jetzt Stundenbudget.
            System.out.println("Geben Sie bitte das Stundenbudget für diese Aufgabe ein (oder \"exit\" für Abbruch): ");
            inData = scan.nextLine();
            System.out.println("Sie haben eingegeben: " + inData );
            if (inData.toLowerCase().equals("exit")){
                abbruch = true; 
                break;
            }
            else if (areDigits(inData)){
                aufgabe.setAbsehbareLaengeInTage(Integer.parseInt(inData)); //wie prüfen, ob es zugänglich ist?
                break;
            }
            badInputMessage();
        }

        while(!abbruch){
            betrieb.listeDerOrte();
            System.out.println("Geben Sie bitte das OrtsID für den Ausübungsort dieser Aufgabe ein (\"n\" für neuen Ort oder \"exit\" für Abbruch):");
            inData = scan.nextLine();
            System.out.println("Sie haben eingegeben: " + inData );
            if (inData.toLowerCase().equals("exit")){
                abbruch = true; 
                break;
            }
            if (inData.toLowerCase().equals("n")){
                addiereNeuenOrtAusDerKonsole(ortIdZaehler);
                aufgabe.setAusuebungsOrt(ortIdZaehler++);
                break;
            }
            if (areDigits(inData) && betrieb.ortIdExists(Integer.parseInt(inData))){
                aufgabe.setAusuebungsOrt(Integer.parseInt(inData));
                break;
            }
            badInputMessage();
            
        }
        
        if (!abbruch){
            betrieb.addiereAufgabe(aufgabe);  //erfolgreicher Eintrag neuer Aufgabe
            if (betrieb.aufgabeCsvWriter(aufgabe)) System.out.println("Gelungen");
            else System.out.println("Kein Erfolg beim Speichern. Mist!");
            System.out.println();
            System.out.println("Eine neue Aufgabe wurde erfolgreich eingetragen!");

            System.out.println();

        }
        else System.out.println("Vorgang abgebrochen. Keine neue Aufgabe eingetragen."); //abgebrochene Eingabe. ABER. WAS IST MIT EINGETRAGENEN ORT, PERSON, PROJECT USW. DIE ANLEGEMÖGLICHKEIT GAR ENTNEHMEN?
    }
    
//   public static boolean loescheAufgabeAusDerKonsole(){
//       boolean allOk = false;
//       while(allOk){
//           System.out.println("Geben Sie bitte das ID der zu löschenden Aufgabe ein");
//           int inData = scan.nextInt();
//           System.out.println("Sie haben eingegeben: " + inData );
//           
//           if (betrieb.aufgabeIdExists(inData)){
//               Aufgabe a = betrieb.getAufgabeById(inData);
//               System.out.println("Folgende Aufgabe wird gelöscht:");
//               System.out.println(betrieb.toString(a));
//               while(allOk){
//                   System.out.println("Sind Sie sicher? \"j\" für löschen, jede andere Eingabe für Stop");
//                   
//               }
//           }
//       }
//       return allOk;
//   }
    
// =========================================================================================================
    

    public static GregorianCalendar dateEingabe(){ // Eingabe des Datums per Tastatur, wird an mehreren Stellen benutzt
        GregorianCalendar datum = new GregorianCalendar();
        int tag, monat, jahr, eingabe;
        while(true){
            System.out.println("Tag (dd):");
            eingabe = scan.nextInt(); //und wenn hier keine Ziffer eingegeben werden?
            if ((eingabe > 0) && (eingabe <= 31)){
               tag = eingabe;
               break;
            }
            badInputMessage();
        }
        while(true){ //Monatseingabe
            System.out.println("Monat (mm):");
            eingabe = scan.nextInt();
            if ((eingabe > 0) && (eingabe <= 12)){
                monat = eingabe + 1;  
                break;
            }
            badInputMessage();
        }
        while(true){    
            System.out.println("Jahr (jj):");
            eingabe = scan.nextInt();
            if ((eingabe >= 0) && (eingabe < 100)){
                jahr = eingabe;
                if (jahr > 50) jahr = 1900 + jahr;
                else jahr = 2000 + jahr;
                break;
            }
            badInputMessage();
        }
        
        datum.set(jahr, monat, tag);
//        scan.close();
        System.out.println(datum.getTime().toString());
//        String inData = scan.nextLine();
        return datum;
    }
  
    
    public static boolean areDigits(String string){
        for (char i:string.toCharArray()){
            if (!Character.isDigit(i)) return false;
        }
        return true;
        
    }
 
    static void badInputMessage()
    {
        System.out.println("Falsche eingabe, bitte widerholen Sie sie!");
    }
    
    public static void main(String[] args){
        boolean abbruch = false;
        betrieb = new Betrieb();
        String inData;
        while(!abbruch){
            System.out.print("Geben Sie einen Befehl ein oder \"help\" für die Befehlslisgte: ");
            inData = scan.nextLine();
            System.out.println("Sie haben eingegeben: " + inData );
            System.out.println();
            switch (inData.toUpperCase()){
                case "HELP": help();  break;
                case "NEWPRJ": addiereNeuesProjektAusDerKonsole(projektIdZaehler++); break;
                case "NEWTASK": addiereNeueAufgabeAusDerKonsole(aufgabenIdZaehler++); break;
                case "NEWPLACE": addiereNeuenOrtAusDerKonsole(ortIdZaehler++); break;
                case "NEWSTUFF": addiereNeuePersonAusDerKonsole(personIdZaehler++); break;
                case "PRINTPRJ": betrieb.listeDerProjekte(); break;
                case "PRINTTASKS": betrieb.listeDerAufgaben(); break;
                case "PRINTSTUFF": betrieb.listeDerPersonen(); break;
                case "PRINTPLACES": betrieb.listeDerOrte(); break;
                case "TASKPRJ": break;
                case "DELTASK": break;
                case "UPDATETASK": break;
                case "EXIT": abbruch = true; break;
                default: System.out.println("Befehl nicht erkannt."); System.out.println(); break;
            }
            System.out.println();
            if (abbruch) break;
        }
        System.out.println("Bye");
        scan.close();
    }
}