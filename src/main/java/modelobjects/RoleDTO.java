package modelobjects;

public class RoleDTO {

    private String bezeichnung = null;

    public RoleDTO (){}

    public RoleDTO(String bezeichnung) {
        this.bezeichnung = bezeichnung;
    }


    public String getBezeichnung() {
        return bezeichnung;
    }

    public void setBezeichnung(String bezeichnung) {
        this.bezeichnung = bezeichnung;
    }



}
