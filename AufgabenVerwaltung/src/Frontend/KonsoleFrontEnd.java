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
        System.out.println(" tasksreadf -- lesen der AufgabenListe aus der Datei \"aufgaben.csv\" in die aktuelle Aufgabenliste");
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
            betrieb.orteCsvWriter(betrieb.getOrte());
            System.out.println();
            System.out.println("Ein neues Ort wurde erfolgreich eingetragen!");
            System.out.println();
        }
        else System.out.println("Vorgang abgebrochen");
            
//            scan.close();
    }
    
    public int orteZaehlerEinstellenBeimLesenAusDatei(){
        int neuerIdZaehler = 1;
        
        if (!betrieb.getOrte().equals(null)){
            for (Ort ort:betrieb.getOrte()){
                if (ort.getOrtId() > neuerIdZaehler)  neuerIdZaehler = ort.getOrtId();           
            }
            neuerIdZaehler++;
        }
        return neuerIdZaehler;
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
            person.setUserGeburtsDatum(dateEingabe());                                             //hier boolean zurückliefern (erfolg/abbruch)
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
            betrieb.personenCsvWriter(betrieb.getPersonen());
            System.out.println();
            System.out.println("Eine neue Person wurde erfolgreich eingetragen!");
            System.out.println();
        }
        else System.out.println("Vorgang abgebrochen");
    }
    
    public int personenZaehlerEinstellenBeimLesenAusDatei(){
        int neuerIdZaehler = 1;

        if (!betrieb.getPersonen().equals(null)){
            for (Person person:betrieb.getPersonen()){
                if (person.getPersonId() > neuerIdZaehler)  neuerIdZaehler = person.getPersonId();           
            }
            neuerIdZaehler++;
        }
        return neuerIdZaehler;
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
            if (betrieb.projectsCsvWriter(betrieb.getProjekte())) System.out.println("Projekt gespeichert.");
            else System.out.println("Kein Erfolg beim Speichern des Projektes. Mist.");
            System.out.println();
            System.out.println("Ein neues Projekt wurde erfolgreich eingetragen!");
            System.out.println();

        }
        else System.out.println("Vorgang abgebrochen. Kein neues Projekt eingetragen.");
    }
    
    public static void listeAufgabenInProjekt(){
        String inData;
        
        while(true){
            betrieb.listeDerProjekte();
            System.out.println("Geben Sie bitte die ProjektID ein (\"exit\" für Abbruch): ");
            inData = scan.nextLine();
            if (areDigits(inData) && betrieb.projectIdExists(Integer.parseInt(inData))){
                betrieb.tasksInProjectEnthalten(Integer.parseInt(inData));
                break;
            }
            if (inData.toLowerCase().equals("exit")) break;
            
            badInputMessage();
        }
        
    }

    public int projekteZaehlerEinstellenBeimLesenAusDatei(){
        int neuerIdZaehler = 1;
        
        if (!betrieb.getProjekte().equals(null)){
            for (Project pojekt:betrieb.getProjekte()){
                if (pojekt.getProjectId() > neuerIdZaehler)  neuerIdZaehler = pojekt.getProjectId();           
            }
            neuerIdZaehler++;
        }
        return neuerIdZaehler;
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
            badInputMessage();
            System.out.println("Bitte geben Sie eine Zahl zwischen 1 und 10 ein.");
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
            else if (!inData.equals(null) && areDigits(inData)){
                aufgabe.setStundenBudget(Integer.parseInt(inData)); 
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

            if (betrieb.aufgabenCsvWriter(betrieb.getAufgaben())) System.out.println("Gelungen");
            else System.out.println("Kein Erfolg beim Speichern. Mist!");

            System.out.println();
            System.out.println("Eine neue Aufgabe wurde erfolgreich eingetragen!");

            System.out.println();

        }
        
        else System.out.println("Vorgang abgebrochen. Keine neue Aufgabe eingetragen."); //abgebrochene Eingabe. ABER. WAS IST MIT EINGETRAGENEN ORT, PERSON, PROJECT USW. DIE ANLEGEMÖGLICHKEIT GAR ENTNEHMEN?
    }
    
    public static void loescheAufgabePerId(){
        while(true){
            betrieb.listeDerAufgaben();
            System.out.println("Geben Sie bitte die AufgabenID ein (\"exit\" für Abbruch):");
            String inData = scan.nextLine();
            
            if (areDigits(inData) && betrieb.aufgabeIdExists(Integer.parseInt(inData))){
                if(betrieb.loescheAufgabe(Integer.parseInt(inData))){
                    System.out.println("Die Aufgabe wurde erfolgreich gelöscht");
                    break;
                }

            }
            if (inData.toLowerCase().equals("exit")){
                break;
            }
            badInputMessage();
        }
    }
    
    
    public static void aendereAufghabeAusKonsole(){
        boolean erfolg = false;
        boolean keinAbbruch = true;     //regulärer Abbruch, von Benutzer gewollt. ob nötig?
        
        while(keinAbbruch){

            betrieb.listeDerAufgaben();
            System.out.println("Geben Sie bitte die AufgabenID ein (\"exit\" für Abbruch): ");
            String inData = scan.nextLine();
            if (inData.toLowerCase().equals("exit")) break;
            
            if (areDigits(inData) && betrieb.aufgabeIdExists(Integer.parseInt(inData))){
                
                Aufgabe aufgabe = betrieb.getAufgabeById(Integer.parseInt(inData));
            
                System.out.println("Wählen Sie bitte zwischen folgenden Optionen:\n"
                                    + "- \"b\": um Aufgabenbezeichnung zu ändern;\n"
                                    + "- \"p\": um Priorität der Aufgabe zu ändern;\n"
                                    + "- \"m\": um diese Aufgabe einem anderen Mitarbeiter zuzuordnen;\n"
                                    + "- \"s\": um Aufgabenstatus zu ändern;\n"
                                    + "- \"d\": um das AnfangsDatum der Aufgabe zu ändern;\n"
                                    + "- \"o\": um einen anderen Ausübunsgsort für die Aufgabe zu wählen;\n"
                                    + "- \"t\": um das stundenbudget der Aufgabe zu ändern.\n"
                                    + "- \"exit\" oder \"Enter\" drücken: um in das Hauptmenu zurückzukehren.");
                inData = scan.nextLine();
                if (inData.toLowerCase().equals("exit") || inData.equals(null)) break;
                if (inData.toLowerCase().equals("b")){
                    System.out.println("Aktuelle Bezeichnung der Aufgabe: " + aufgabe.getBezeichnung());
                    System.out.println("Geben Sie eine neue Bezeichnung ein. \"Enter\" um alte Bezeichnung beizubehalten: ");
                    inData = scan.nextLine();
                    
                    if (!inData.equals(null)){
                        aufgabe.setBezeichnung(inData);
                        erfolg = true;
                    }
                    else break;
                }
                if (inData.toLowerCase().equals("p")){
                    System.out.println("Aktuelle Priorität der Aufgabe: " + aufgabe.getPrioritaet());
                    System.out.println("Geben Sie eine neuen Wert für Priorität der Aufgabe ein. \"Enter\" um alte Priorität beizubehalten: ");
                    inData = scan.nextLine();
 
                    if (!inData.equals(null)){
                        while(true){
                            if (areDigits(inData)){
                                int i = Integer.parseInt(inData);
                                
                                if ((i >= 0) && (i <= 10)){
                                    aufgabe.setPrioritaet(i);
                                    erfolg = true;
                                    break;
                                }
                                badInputMessage();
                                System.out.print("(es muss eine Zahl zwischen 1 und 10 sein).");
                                inData = scan.nextLine();
                            }
                        }
                    }
                    else break;
                }

                if (inData.toLowerCase().equals("m")){
                    System.out.println("Aktuell ist die Aufgabe dem Mitarbeiter " + 
                                        betrieb.getPersonById(aufgabe.getBearbeiterId()).getName() + 
                                        " " + betrieb.getPersonById(aufgabe.getBearbeiterId()).getNachName() + 
                                        ", ID: " + aufgabe.getBearbeiterId() + "zugeordnet.");
                    betrieb.listeDerPersonen();

                    System.out.print("Geben Sie eine Mitarbeiter-ID für den neuen Mitarbeiter ein. \"Enter\" um alten Wert beizubehalten: ");
                    inData = scan.nextLine();
                    
                    if (!inData.equals(null)){
                        while(true){
                            if (areDigits(inData) && betrieb.personIdExists(Integer.parseInt(inData))){
                                aufgabe.setBearbeiterId(Integer.parseInt(inData));
                                erfolg = true;
                            }
                            badInputMessage();
                            betrieb.listeDerPersonen();
                            System.out.print("Ihre Wahl: ");
                            inData = scan.nextLine();
                        }
                    }

                    else break;
                }
                if (inData.toLowerCase().equals("s")){
                    System.out.println("Aktueller Status der Aufgabe: " + aufgabe.getStatusAsString());
                    System.out.println("Geben Sie einen neuen Status ein: \n"
                                        + " - \"0\" für \"angefangen\"\n"
                                        + " - \"1\" für \"in Bearbeitung\"\n"
                                        + " - \"2\" für \"angehalten\"\n"
                                        + " - \"3\" für \"abgeschlossen\"\n"
                                        + "Enter\" um alten Wert beizubehalten. ");
                    System.out.print("Ihre Wahl: ");
                    inData = scan.nextLine();
                    if (!inData.equals(null)){
                        if (areDigits(inData)){
                            int i = Integer.parseInt(inData);
                            if ((i >= 0) && (i <= 3)) aufgabe.setStatus(i);
                            erfolg = true;
                        }
                    }
                    else break;
                }
                
                if (inData.toLowerCase().equals("d")){
                    System.out.println("Aktuelle Anfangsdatum der Aufgabe: " + aufgabe.getAnfangsDatum());
                    System.out.println("Geben Sie \"n\" für das neue Anfangsdatum. Jede weitere Taste - Abbruch: ");

                    inData = scan.nextLine();
 
                    if (inData.toLowerCase().equals("n")){
                        aufgabe.setAnfangsDatum(dateEingabe());
                        erfolg = true;
                    }
                    else break;
                }
                
                if (inData.toLowerCase().equals("o")){
                    System.out.println("Aktuelle Ausübungsort der Aufgabe: " + betrieb.getOrtById(aufgabe.getAusuebungsOrt()).toString());
                    System.out.println("Geben Sie \"n\" für das neue Anfangsdatum. Jede weitere Taste - Abbruch: ");

                    inData = scan.nextLine();
 
                    if (inData.toLowerCase().equals("n")){
                        betrieb.listeDerOrte();
                        System.out.print("Geben Sie bitte die ID des anderen Ortes ein, \"Enter\" für abbruch:");
                        inData = scan.nextLine();
                        if (!inData.toLowerCase().equals(null)){
                            while(true){
                                if (areDigits(inData) && betrieb.ortIdExists(Integer.parseInt(inData))){
                                    aufgabe.setAusuebungsOrt(Integer.parseInt(inData));
                                    erfolg = true;
                                }
                                else badInputMessage();
                                betrieb.listeDerOrte();
                                System.out.print("Ihre Wahl: ");
                                inData = scan.nextLine();
                            }
                        }
                    }
                    else break;
                }
            
                if (inData.toLowerCase().equals("t")){
                    System.out.println("Aktuelles Stundenbudget der Aufgabe: " + aufgabe.getStundenBudget());
                    System.out.println("Geben Sie eine neuen Wert für das Stundenbudget der Aufgabe ein. \"Enter\" um alte Priorität beizubehalten: ");
                    inData = scan.nextLine();
    
                    if (!inData.equals(null)){
                        if (areDigits(inData)){
                            int i = Integer.parseInt(inData);
                            aufgabe.setStundenBudget(i);
                            erfolg = true;
                        }
                    }
                    else break;
                }
                if(erfolg){
                    betrieb.replaceAufgabe(aufgabe);
                    break;
                }
                else{
                    System.out.println();
                    System.out.println("Neuer Versuch. Muss eigentlich nicht sein.");
                    System.out.println();
                }
            }
        }
    }
    
    
    
//    else breaks - nafiga? ved' posle pervoj proverki ujdet v nikuda...
    
    public int aufgabenZaehlerEinstellenBeimLesenAusDatei(){
        int neuerIdZaehler = 1;
        
        if (!betrieb.getAufgaben().equals(null)){
            for (Aufgabe aufgabe:betrieb.getAufgaben()){
                if (aufgabe.getAufgabeId() > neuerIdZaehler)  neuerIdZaehler = aufgabe.getAufgabeId();           
            }
            neuerIdZaehler++;
        }
        return neuerIdZaehler;
    }

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
                monat = eingabe - 1;  
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
        System.out.println(datum.getTime().toString());
        return datum;
    }
  
    
    public static boolean areDigits(String string){
        for (char i:string.toCharArray()){
            if (!Character.isDigit(i)) return false;
        }
        return true;
        
    }
 
    static void badInputMessage(){
        System.out.println();
        System.out.println("Falsche eingabe, bitte widerholen Sie sie!");
    }
    public static void main(String[] args){
        boolean abbruch = false;
        betrieb = new Betrieb();
        String inData;
        while(!abbruch){
            help();
            System.out.print("Geben Sie einen Befehl ein: ");
            inData = scan.nextLine();
            System.out.println("Sie haben eingegeben: " + inData );
            System.out.println();
            switch (inData.toUpperCase()){
                case "HELP": help();  break;
                case "NEWPRJ": addiereNeuesProjektAusDerKonsole(projektIdZaehler);  projektIdZaehler++;  break;
                case "NEWTASK": addiereNeueAufgabeAusDerKonsole(aufgabenIdZaehler); aufgabenIdZaehler++; break;
                case "NEWPLACE": addiereNeuenOrtAusDerKonsole(ortIdZaehler);        ortIdZaehler++;      break;
                case "NEWSTUFF": addiereNeuePersonAusDerKonsole(personIdZaehler);   personIdZaehler++;   break;
                case "PRINTPRJ": betrieb.listeDerProjekte();    break;
                case "PRINTTASKS": betrieb.listeDerAufgaben();  break;
                case "PRINTSTUFF": betrieb.listeDerPersonen();  break;
                case "PRINTPLACES": betrieb.listeDerOrte();     break;
                case "TASKPRJ":  listeAufgabenInProjekt();      break;
                case "DELTASK":  loescheAufgabePerId();         break;
                case "UPDATETASK": aendereAufghabeAusKonsole(); break;
                case "BETRIEB-RF": betrieb.aufgabenCsvReader(); 
                                   projekteZaehlerEinstellenBeimLesenAusDatei(); 
                                   orteZaehlerEinstellenBeimLesenAusDatei(); 
                                   personenZaehlerEinstellenBeimLesenAusDatei();
                                   aufgabenZaehlerEinstellenBeimLesenAusDatei();
                                   break;
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

//======================================================BUG-LOG==============================================================


// PLZ kann beliebig lang sein, nur das erste Symbol wird auf digit() geprüft.
// beim Speichern der Task in .csv wird status als tekst gespeichert. muss zahl sein.

// Datenitegrität: wenn wir eine Tabelle aus der Datei laden und andere - nicht. Nix gut. Soll abgefangen werden oder alles in einer datei. Mist. 
// Entscheidung: erst alles zusammen laden, das ganze betrieb, alles jedoch in einzelnen Dateien. 
//Die ganze Integritätsüberprüngs-Logic danach mal bei Gelegenheit programmieren. oder doch in einer Datei.

// neuer_mensch: undeutig message bei Geburts/Wohnort - eingabe.
// Aufgabe.getbearbeitername shreiben.
// bei taskprj: besser _Namen_ der Bearbeiter, als id.
// Errormessage bei falscher Nummer in Updatetask.
// in menu bei updatetask - kein projekt, aufgabe.

// beim auflisten sind die frischeste einträge zuerst, die numerierung (IDs) ist dann entsprechend umgekehrt. nix gut.

//Kontrole, ob die aufgaben gleiche bezeichnungen haben? ob notig?
//Wenn aus einer datei gelesen - Nummercheck notwendig, das die Nummern nicht doppelt vorhanden? 

//wenn die Daten aus einer Datei gelesen werden - die ID-Zaehler sollen dann auf maximalen Wert gesetzt werden

// s takim podxodom k date jeto zh mozhno fevral' otlavlivat', xotja by do 29

// keine begrenzung zu Person.Status, Person.Geschlecht, AufgabenArt, 