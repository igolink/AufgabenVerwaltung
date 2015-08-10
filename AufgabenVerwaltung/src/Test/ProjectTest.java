package Test;

import static org.junit.Assert.*;

//import java.util.ArrayList;
import java.util.Arrays;
import java.util.GregorianCalendar;
//import java.util.HashSet;
//import java.util.List;




//import org.junit.Before;
//import org.junit.BeforeClass;
import org.junit.Test;
//import org.junit.After;




import Frontend.KonsoleFrontEnd;
//import Frontend.KonsoleFrontEnd;
import Logic.Aufgabe;
import Logic.Ort;
import Logic.Project;
import Logic.Person;
import Logic.Betrieb;

public class ProjectTest {
    
//      @Before
//    System.out.println("bla");
//    public void setUp() throws Exception{
    Betrieb tst = new Betrieb();
    Project testProjekt = new Project();
    Aufgabe a = new Aufgabe(0, 1, "Testaufgabe", 0, 1, 0, new GregorianCalendar(), 500, 1, Arrays.asList("Mach nach", "Mach mit", "Mach besser"));
    Aufgabe a2 = new Aufgabe(1, testProjekt.getProjectId(), "Testaufgabe2", 0, 1, 0, new GregorianCalendar(), 400, 1, Arrays.asList("Mach nach", "Mach mit", "Mach besser"));;
    Aufgabe a3 = new Aufgabe();
    Person prs = new Person(1, "Muster", "zu Musterdorf", 0, 1, new GregorianCalendar(1905, 4, 1));
    Project p = new Project(1, "Megasuperproject", "dient dem Testen");
    Ort o = new Ort();
//    }

    @Test
    public void testDesEinschliessens() {
           a.setProjectId(testProjekt.getProjectId());
           assertEquals(testProjekt.getProjectId(), a.getProjectId());
    }
    
    
    @Test 
    public void testDesLoeschens(){
        a.setProjectId(testProjekt.getProjectId());
        a2.setProjectId(testProjekt.getProjectId());
        a.setProjectId(1);
        assertNotEquals(a.getProjectId(), testProjekt.getProjectId());
    }
//    
//    @Test
//    public void testDesUpdates(){
//        a.setProjectId(testProjekt.getProjectId());
//        a2.setProjectId(testProjekt.getProjectId());
//        Aufgabe neu3 = a2;
//        neu3.setBezeichnung("TestAufgabe3");
//        a2 = neu3;
//        assertEquals(neu3, testProjekt.("15"));
//    }
    
    @Test
    public void testDatumEingabe(){
        a.setAnfangsDatum(KonsoleFrontEnd.dateEingabe());
//        GregorianCalendar datum = new GregorianCalendar(1999, 5, 3);
//        a.setAnfangsDatum(new GregorianCalendar(1999, 6, 3));
        System.out.println(a.getAnfangsDatum().getTime().toString());
        assertNotNull(a.getAnfangsDatum());
   }
    
    @Test
    public void notNullTest(){ //wegen nullPointerException im Frontend
        Betrieb firma = new Betrieb();
        assertNotNull(firma.getPersonen());
        assertNotNull(firma.getAufgaben());
        assertNotNull(firma.getOrte());
        assertNotNull(firma.getProjekte());
    }
    
    @Test
    public void addiereAufgabeTest(){
        tst.addiereAufgabe(a2);
        assertEquals(a2.getBezeichnung(), tst.getAufgabeById(1).getBezeichnung());
    }
    
    @Test
    public void addierePersonTest(){
        tst.addierePerson(prs);
        assertEquals(prs, tst.getPersonById(prs.getPersonId()));
    }
    
    @Test
    public void addierePojectTest(){
        tst.adddiereProject(p);
        assertEquals(p, tst.getProjectById(p.getProjectId()));
    }
    
    @Test
    public void addiereOrtTest(){
        tst.addiereOrt(o);
        tst.addiereOrt(new Ort(2, "Musterdorf", "musterStrasse", "15b", "01069"));
        tst.listeDerOrte();
        assertEquals(o, tst.getOrtById(o.getOrtId()));
        assertEquals(2, tst.getOrtById(2).getOrtId());
    }
}