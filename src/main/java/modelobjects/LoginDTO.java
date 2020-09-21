package modelobjects;

public class LoginDTO {
    private String emailadresse;
    private String password;

    public LoginDTO(String emailadresse, String password) {
        this.emailadresse = emailadresse;
        this.password = password;
    }

    public String getEmailadresse(){
        return emailadresse;
    }
    public void setEmailadresse(String emailadresse){
        this.emailadresse = emailadresse;
    }

    public String getPassword(){
        return password;
    }
    public void setPassword(String password){
        this.password = password;
    }

}
