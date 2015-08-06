package Logic;

//import java.util.List;

public class Project {

    private int projectId;
        private String projectBezeichnung;
        private String projectBeschreibung;
// hier auch anfang und deadline? und budget in stunden?

    public int getProjectId(){
        return projectId;
    }

    public void setProectID(int projectID){
        this.projectId = projectID;
    }

    public String getProjectBezeichnung(){
        return projectBezeichnung;
    }

    public void setProjectBezeichnung(String projectBezeichnung){
        this.projectBezeichnung = projectBezeichnung;
    }

    public String getProjectBeschreibung(){
        return projectBeschreibung;
    }

    public void setProjectBeschreibung(String projectBeschreibung){
        this.projectBeschreibung = projectBeschreibung;
    }
    
    public void printProject(){
        System.out.println("ProjektID: " + this.getProjectId() + "; Projektbezeichnung: " + this.getProjectBezeichnung()); 
        System.out.println("Projektbeschrebung: " + this.getProjectBeschreibung());
    }

    public Project(){}
    
    public Project(int projectID, String projectBezeichnung, String projectBeschreibung){
        super();
        this.projectId = projectID;
        this.projectBezeichnung = projectBezeichnung;
        this.projectBeschreibung = projectBeschreibung;
    }
}