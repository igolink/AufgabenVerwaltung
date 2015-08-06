package Frontend;

import java.util.GregorianCalendar;
import java.util.Scanner;

import Logic.Aufgabe;
import Logic.Betrieb;
import Logic.Ort;
import Logic.Person;
import Logic.Project;

public class FrontEnd {
    private static int ortIdZaehler = 1;
    private static int personIdZaehler = 1;
    private static int projektIdZaehler = 1;
    private static int aufgabenIdZaehler = 1;
    public static Betrieb betrieb;

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
    
    
    public static void addiereNeuenOrt(int iD){
        boolean abbruch = false;
        String inData;
        Scanner scan = new Scanner(System.in);
        Ort ort = new Ort();
        ort.setOrtId(iD);
        
        while(true){//Ortsname
            System.out.println("Geben Sie bitte den Name des Ortes ein ( \"exit\" für Abbruch):");
            inData = scan.nextLine();
            System.out.println("Sie haben eingegeben: " + inData );
            if (inData.toLowerCase().equals("exit")){
                abbruch = true; 
                break;}
            
            if (!inData.isEmpty()){
                ort.setBezeichnung(inData.substring(0, 1).toUpperCase() + inData.substring(1).toLowerCase());
                break;
            }
            System.out.println("Falsche Eingabe, versuchen Sie es nochmal.");
        }
        
        if (!abbruch){
            while(true){//Strasse
                System.out.println("Geben Sie bitte den Strassennamen ein ( \"exit\" für Abbruch):");
                inData = scan.nextLine();
                System.out.println("Sie haben eingegeben: " + inData );
                
                if (inData.toLowerCase().equals("exit")){
                    abbruch = true; 
                    break;}
                
                if (!inData.isEmpty()){
                    ort.setStrasse(inData.substring(0, 1).toUpperCase() + inData.substring(1).toLowerCase());
                    break;
                }
                System.out.println("Falsche Eingabe, versuchen Sie es nochmal.");
            }
        }
            
            if (!abbruch){
                while(true){//Hausnummer
                    System.out.println("Geben Sie bitte die Hausnummer ein ( \"exit\" für Abbruch):");
                    inData = scan.nextLine();
                    System.out.println("Sie haben eingegeben: " + inData );
                    
                    if (inData.toLowerCase().equals("exit")){
                        abbruch = true; 
                        break;}
                    
                    if (!inData.isEmpty()){
                        ort.setHausNummer(inData);
                    }
                    System.out.println("Falsche Eingabe, versuchen Sie es nochmal.");         
                        
                }
            }
            if (!abbruch){
                while(true){ //OrtsPLZ
                    System.out.println("Geben Sie bitte die PLZ ein ( \"exit\" für Abbruch):");
                    inData = scan.nextLine();
                    System.out.println("Sie haben eingegeben: " + inData );
                    
                    if (inData.toLowerCase().equals("exit")){
                        abbruch = true; 
                        break;}
                    if (!inData.isEmpty() && inData.length() == 5){
                        ort.setPlz(inData);
                    }
                    
                }
                betrieb.addiereOrt(ort);                
            }
            else System.out.println("Vorgang abgebrochen");
            
            scan.close();
    }
    
    public static void addiereNeuePerson(int iD){
        boolean abbruch = false;
        String inData;
        Scanner scan = new Scanner(System.in);
        Person person = new Person();
        person.setPersonId(iD);
        
        while(true){ //Vorname
            System.out.println("Geben Sie bitte den Vornamen der Person ein ( \"exit\" für Abbruch):");
            inData = scan.nextLine();
            System.out.println("Sie haben eingegeben: " + inData );
            if (inData.toLowerCase().equals("exit")){
                abbruch = true; 
                break;}
            
            if (!inData.isEmpty()){
                person.setVorName(inData.substring(0, 1).toUpperCase() + inData.substring(1).toLowerCase());
                break;
            }
            System.out.println("Falsche Eingabe, versuchen Sie es nochmal.");
        }
        
        if(!abbruch){
            while(true){ //Nachname
                System.out.println("Geben Sie bitte den Nachnamen der Person ein ( \"exit\" für Abbruch):");
                inData = scan.nextLine();
                System.out.println("Sie haben eingegeben: " + inData );
                if (inData.toLowerCase().equals("exit")){
                    abbruch = true; 
                    break;}
                
                if (!inData.isEmpty()){
                    person.setNachName(inData.substring(0, 1).toUpperCase() + inData.substring(1).toLowerCase());
                    break;
                }
                System.out.println("Falsche Eingabe, versuchen Sie es nochmal.");       
            }

        }

        if(!abbruch){
            while(true){ //Geschlecht
                System.out.println("Geben Sie bitte für Geschlechtsangabe \"0\" (männlich) oder \"1\" (weiblich) ein ( \"exit\" für Abbruch):");
                inData = scan.nextLine();
                System.out.println("Sie haben eingegeben: " + inData );
                if (inData.toLowerCase().equals("exit")){
                    abbruch = true; 
                    break;}
                
                if (!inData.isEmpty() && (inData.length() == 1) && (Character.digit(inData.charAt(0), 10) >= 0) && (Integer.parseInt(inData) <= 1)){
                    person.setGeschlecht(Integer.parseInt(inData));
                    break;
                }
                System.out.println("Falsche Eingabe, versuchen Sie es nochmal.");       
            }

        }

        if(!abbruch){
            while(true){ //Geburtsdatum
                System.out.println("Geben Sie bitte das Geburtsdatum der Person ein: ");
//                inData = scan.nextLine();
//                System.out.println("Sie haben eingegeben: " + inData );
//                Date datum = null;
//                if (inData.toLowerCase().equals("exit")){
//                    abbruch = true; 
//                    break;}
                person.setDatum(dateEingabe());
                break;
                
            }
            betrieb.addierePerson(person);
        }
        else System.out.println("Vorgang abgebrochen");

        scan.close();
    }
    
    public static void addiereNeuesProjekt(int iD){
        boolean abbruch = false;
        String inData;
        Scanner scan = new Scanner(System.in);
        Project projekt = new Project();
        projekt.setProectID(iD);
        
        while(true){ //Projektbezeichnung
            System.out.println("Geben Sie bitte die Bezeichnung des Projektes ein ( \"exit\" für Abbruch):");
            inData = scan.nextLine();
            System.out.println("Sie haben eingegeben: " + inData );
            if (inData.toLowerCase().equals("exit")){
                abbruch = true; 
                break;}
            
            if (!inData.isEmpty()){
                projekt.setProjectBezeichnung(inData.substring(0, 1).toUpperCase() + inData.substring(1).toLowerCase());
                break;
            }
            System.out.println("Falsche Eingabe, versuchen Sie es nochmal.");    
         }
        if(!abbruch){
            while(true){ //Projektbeschreibung
                System.out.println("Geben Sie bitte die kurze Beschreibung des Projektes ein ( \"exit\" für Abbruch):");
                inData = scan.nextLine();
                System.out.println("Sie haben eingegeben: " + inData );
                if (inData.toLowerCase().equals("exit")){
                    abbruch = true; 
                    break;}
                
                if (!inData.isEmpty()){
                    projekt.setProjectBeschreibung(inData.substring(0, 1).toUpperCase() + inData.substring(1).toLowerCase());
                    break;
            
                }
                System.out.println("Falsche Eingabe, versuchen Sie es nochmal.");       
            }
            betrieb.adddiereProject(projekt);
        }
        else System.out.println("Vorgang abgebrochen");
        scan.close();
    }
    
    public static void addiereNeueAufgabe(int iD){
        boolean abbruch = false;
        String inData;
        Scanner scan = new Scanner(System.in);
        Aufgabe aufgabe = new Aufgabe();
        aufgabe.setAufgabeId(iD);
        
        
        while(true){
            betrieb.listeDerProjekte();
            System.out.println("Geben Sie bitte die ProjektID ein, \"exit\" für Abbruch:");
            inData = scan.nextLine();
            System.out.println("Sie haben eingegeben: " + inData );
            if (inData.toLowerCase().equals("exit")){
                abbruch = true; 
                break;}
            if (betrieb.projekte.contains(Integer.parseInt(inData))){ //bred ved'
                aufgabe.setProjectId(Integer.parseInt(inData)); 
                break;}
            else{ 
                System.out.println("Inkorrekte Angabe, versuchen Sie es nochmal");
            }               
        }

        if (!abbruch){
            while(true){
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
        }    
 
        if (!abbruch){
            while(true){            
                System.out.println("Geben Sie bitte die Priorität der Aufgabe von 1 (niedrig) bis 10 (sehr wichtig) ein oder \"exit\" für Abbruch:");
                inData = scan.nextLine();
                System.out.println("Sie haben eingegeben: " + inData);
                if (inData.toLowerCase().equals("exit")){
                    abbruch = true; 
                    break;
                }
                int i = Integer.parseInt(inData);
                    
                if ((i > 0) && (i <= 10)){
                    aufgabe.setPrioritaet(i);
                    break;
                }
                System.out.println("Aufgabe nicht korrekt. Bitte geben Sie eine Zahl zwischen 1 und 10 ein.");
            }
        }
        

        if (!abbruch){
            while(true){
                betrieb.listeDerPersonen();
                System.out.println("Bearbeiter-Eingabe. Geben Sie die PersonalID eines Mitarbeiters. Um einen neuen Mitarbeiter zu registrieren geben Sie \"neu\", \"exit\" für Abbruch:");
                inData = scan.nextLine();
                System.out.println("Sie haben eingegeben: " + inData );
                if (inData.toLowerCase().equals("exit")){
                    abbruch = true; 
                    break;
                }
                if (inData.toLowerCase().equals("neu")){
                    addiereNeuePerson(personIdZaehler++); 
                }
                else{
                    aufgabe.setBearbeiterId(Integer.parseInt(inData));
                    break;
                }
                
            }
        }
        if (!abbruch){
            aufgabe.setStatus(0);
        }
        
        if (!abbruch){
            while(true){
                System.out.println("Das Anfangsdatum. Geben Sie bitte \"heute\", \"date\" oder \"exit\" für Abbruch:");
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
                System.out.println("Falshe Eingabe, versuchen Sie es nochmal.");    
            }
        }
    
        if (!abbruch){
            while(true){
                System.out.println("Geben Sie bitte das Stundenbudget für diese Aufgabe ein (oder \"exit\" für Abbruch):");
                inData = scan.nextLine();
                System.out.println("Sie haben eingegeben: " + inData );
                if (inData.toLowerCase().equals("exit")){
                    abbruch = true; 
                    break;
                }
                aufgabe.setAbsehbareLaengeInTage(Integer.parseInt(inData)); //wie prüfen, ob es zugänglich ist?
            }
        }

        if (!abbruch){
            while(true){
                betrieb.listeDerOrte();
                System.out.println("Geben Sie bitte das OrtsID für den Ausübungsort dieser Aufgabe ein (\"neu\" für neuen Ort oder \"exit\" für Abbruch):");
                inData = scan.nextLine();
                System.out.println("Sie haben eingegeben: " + inData );
                if (inData.toLowerCase().equals("exit")){
                    abbruch = true; 
                    break;
                }
                if (inData.toLowerCase().equals("neu")){
                    addiereNeuenOrt(ortIdZaehler);
                }
                aufgabe.setAusuebungsOrt(ortIdZaehler++);
                
                
            }
            betrieb.aufgaben.add(aufgabe); 
        }
        else System.out.println("Vorgang abgebrochen");
        scan.close();
    }
    

    public static GregorianCalendar dateEingabe(){
        Scanner scan = new Scanner(System.in);
        GregorianCalendar datum = new GregorianCalendar();
        int tag, monat, jahr, eingabe;
        while(true){
            System.out.println("Tag (dd):");
            eingabe = scan.nextInt(); //und wenn hier keine Ziffer eingegeben werden?
            if ((eingabe > 0) && (eingabe <= 31)){
               tag = eingabe;
               break;
            }
            badInput();
        }
        while(true){ //Monatseingabe
            System.out.println("Monat (mm):");
            eingabe = scan.nextInt();
            if ((eingabe > 0) && (eingabe <= 12)){
                monat = eingabe;  
                break;
            }
            badInput();
        }
        while(true){    
            System.out.println("Jahr (jj):");
            eingabe = scan.nextInt();
            if ((eingabe >= 0) && (eingabe <= 100)){
                jahr = eingabe;
                if (jahr > 50) jahr = 1900 + jahr;
                else jahr = 2000 + jahr;
                break;
            }
            badInput();
        }
        
        datum = new GregorianCalendar(jahr, monat, tag);
        scan.close();
        System.out.println(datum.toString());
        return datum;
    }
 
    static void badInput(){
        System.out.println("Falsche eingabe, bitte widerholen Sie sie!");
    }
    
    public static void main(String[] args){
        boolean abbruch = false;
        String inData;
        Scanner scan = new Scanner(System.in);
        while(true){
            System.out.print("Geben Sie einen Befehl ein oder \"help\" für die Befehlslisgte: ");
    
            inData = scan.nextLine();
            System.out.println("Sie haben eingegeben: " + inData );
            System.out.println();

            switch (inData.toUpperCase()){
                case "HELP": help();  break;
                case "NEWPRJ": addiereNeuesProjekt(projektIdZaehler++); break;
                case "NEWTASK": addiereNeueAufgabe(aufgabenIdZaehler++); break;
                case "NEWPLACE": addiereNeuenOrt(ortIdZaehler++); break;
                case "NEWSTUFF": addiereNeuePerson(personIdZaehler++); break;
                case "PRINTPRJ": betrieb.listeDerProjekte(); break;
                case "PRINTTASKS": betrieb.listeDerAufgaben(); break;
                case "PRINTSTUFF": betrieb.listeDerPersonen(); break;
                case "PRINTPLACES": betrieb.listeDerOrte(); break;
                case "TASKPRJ": break;
                case "DELTASK": break;
                case "UPDATETASK": break;
                case "EXIT": abbruch = true; break;
                default: System.out.println("Befehl nicht erkannt."); break;
            }
            System.out.println();
            if (abbruch) break;
        }
        System.out.println("Bye");
        scan.close();
    }
}