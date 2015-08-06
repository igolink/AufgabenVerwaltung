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

import Frontend.FrontEnd;
import Logic.Aufgabe;
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
    Person muster = new Person(1, "Muster", "zu Musterdorf", 0, 1, new GregorianCalendar(1905, 4, 1));
    Project p = new Project(1, "Megasuperproject", "dient dem Testen");
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
    
//    @Test
//    public void testDatumEingabe(){
//        a.setAnfangsDatum(FrontEnd.dateEingabe());
//        System.out.println(a.getAnfangsDatum().toString());
//        assertNotNull(a.getAnfangsDatum());
//   }
    
    @Test
    public void personTest(){
        Betrieb firma = new Betrieb();
        assertNotNull(firma.personen);
    }
    
    @Test
    public void personAufgaben(){
        Betrieb firma = new Betrieb();
        assertNotNull(firma.aufgaben);
    }
    
    @Test
    public void personProjekte(){
        Betrieb firma = new Betrieb();
        assertNotNull(firma.projekte);
    }

    @Test
    public void addiereAufgabeTest(){
        tst.addiereAufgabe(a2);
        assertEquals(a2.getBezeichnung(), tst.getAufgabeById(1).getBezeichnung());
    }
    
    @Test
    public void addierePersonTest(){
        tst.addierePerson(muster);
        assertEquals(muster, tst.getPersonById(muster.getPersonId()));
    }
    
    @Test
    public void addierePojectTest(){
        tst.adddiereProject(p);
        assertEquals(p, tst.getProjectById(muster.getPersonId()));
    }
    
    @Test
    public void blablaTest(){
        tst.adddiereProject(p);
        assertEquals(p, tst.getProjectById(muster.getPersonId()));
    }
    
    
}