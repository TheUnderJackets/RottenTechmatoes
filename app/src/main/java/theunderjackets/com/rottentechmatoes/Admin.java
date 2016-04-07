package theunderjackets.com.rottentechmatoes;


import android.os.Parcel;
import android.os.Parcelable;

import com.firebase.client.Firebase;

public class Admin extends User {

    private String userName;
    private String pass;
    private static final String USERSURL = "https://rottentechmatoes.firebaseio.com/users";

    /**
     * empty constructor for DB stuff
     */
    public Admin() {

    }

    /**
     * constructor for admins
     * @param email admins email
     * @param userName admins username
     * @param pass admins password
     */
    public Admin(String email, String userName, String pass) {
        super(null, email, pass, userName, false);
        setUserName(userName);
        changePass(pass);
        this.setIsAdmin(true);
    }

    /**
     * getter for whether or not user is an admin
     * @return true if admin, false otherwise
     */
    public boolean getIsAdmin() {
        return isAdmin;
    }

    /**
     * getter for username
     * @return admins username
     */
    public String getUserName() {
        return userName;
    }

    /**
     * getter for password
     * @return admins password
     */
    public String getPass() {
        return this.pass;
    }

    /**
     * changes admins password
     * @param pass new password
     */
    public void changePass(String pass) {
        this.pass = pass;
        Firebase userRef = new Firebase(USERSURL);
        userRef = userRef.child(this.email + "/pass");
        userRef.setValue(pass);
    }

    /**
     * setter for username
     * @param name username for admin
     */
    public void setUserName(String name) {
        this.userName = name;
        Firebase userRef = new Firebase(USERSURL);
        userRef = userRef.child(this.email + "/userName");
        userRef.setValue(name);
    }

    /**
     * Checks if the password passed in is the same as the actual password.
     * @param pass password to be checked
     * @return true if they are equal, false otherwise
     */
    public boolean validatePassword(String pass) {
        return this.pass.equals(pass);
    }

    /**
     * setter for isadmin
     * @param bool value of isAdmin
     */
    public void setIsAdmin(boolean bool) {
        this.isAdmin = true;
        Firebase userRef = new Firebase(USERSURL);
        userRef = userRef.child(this.email + "/isAdmin");
        userRef.setValue(bool);
    }

}
