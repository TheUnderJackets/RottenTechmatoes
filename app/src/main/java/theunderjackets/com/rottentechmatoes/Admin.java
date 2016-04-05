package theunderjackets.com.rottentechmatoes;


import com.firebase.client.Firebase;

public class Admin extends User {

    private String userName;
    private String pass;
    private static final String USERSURL = "https://rottentechmatoes.firebaseio.com/users";

    /**
     * Empty Admin constructor
     */
    public Admin() {

    }

    /**
     * Admin constructor
     * @param email email to assign
     * @param userName to assign
     * @param pass to assign
     */
    public Admin(String email, String userName, String pass) {
        super(null, email, pass, userName, false);
        setUserName(userName);
        changePass(pass);
        this.setIsAdmin(true);
    }

    /**
     * Determine if an admin
     * @return isAdmin whether user is an admin
     */
    public boolean getIsAdmin() {
        return isAdmin;
    }

    /**
     * Get user's username
     * @return userName
     */
    public String getUserName() {
        return userName;
    }

    /**
     * Get admin's password
     * @return pass
     */
    public String getPass() {
        return this.pass;
    }

    /**
     * Change admin's password
     * @param pass new password
     */
    public void changePass(String pass) {
        this.pass = pass;
        Firebase userRef = new Firebase(USERSURL);
        userRef = userRef.child(this.email + "/pass");
        userRef.setValue(pass);
    }

    /**
     * Set Admin's password
     * @param name username to set
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
     * Set whether or not user is an admin
     * @param bool is/isn't and Admin
     */
    public void setIsAdmin(boolean bool) {
        this.isAdmin = true;
        Firebase userRef = new Firebase(USERSURL);
        userRef = userRef.child(this.email + "/isAdmin");
        userRef.setValue(bool);
    }
}
