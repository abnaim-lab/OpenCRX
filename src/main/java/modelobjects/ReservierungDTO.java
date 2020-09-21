package modelobjects;

import java.sql.Date;


public class ReservierungDTO {

    private int id;
    private Date date;
    private int userID;
    private int autoID;

    public ReservierungDTO(){}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }


    public int getUserIDID() {
        return userID;
    }

    public void setStuID(int userID) {
        this.userID = userID;
    }

    public int getAutoIDID() {
        return autoID;
    }

    public void setAutoID(int autoID) {
        this.autoID = autoID;
    }



}
