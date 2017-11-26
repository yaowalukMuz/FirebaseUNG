package androidthai.in.th.firebaseung.utility;

/**
 * Created by Yaowaluk on 26/11/2017.
 */

public class UserModel {
    private String uidString, nameDisplayString;

    //Constructur Getter Modual
    public UserModel() {

    }//For Getter

    //Constructur Setter Modual
    public UserModel(String uidString, String nameDisplayString) {
        this.uidString = uidString;
        this.nameDisplayString = nameDisplayString;
    }// For Setter

    public String getUidString() {
        return uidString;
    }

    public void setUidString(String uidString) {
        this.uidString = uidString;
    }

    public String getNameDisplayString() {
        return nameDisplayString;
    }

    public void setNameDisplayString(String nameDisplayString) {
        this.nameDisplayString = nameDisplayString;
    }
}//Main Class
