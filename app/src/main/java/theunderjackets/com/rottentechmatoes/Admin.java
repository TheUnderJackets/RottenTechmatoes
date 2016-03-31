package theunderjackets.com.rottentechmatoes;


import com.firebase.client.Firebase;

public class Admin extends User {

    private String userName;
    private String pass;
    private static final String USERSURL = "https://rottentechmatoes.firebaseio.com/users";

    public Admin() {

    }

    public Admin(String email, String userName, String pass) {
        super(null, email, pass, userName, false);
        setUserName(userName);
        changePass(pass);
        this.setIsAdmin(true);
    }


    public boolean getIsAdmin() {
        return isAdmin;
    }

    public String getUserName() {
        return userName;
    }

    public String getPass() {
        return this.pass;
    }

    public void changePass(String pass) {
        this.pass = pass;
        Firebase userRef = new Firebase(USERSURL);
        userRef = userRef.child(this.email + "/pass");
        userRef.setValue(pass);
    }

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

    public void setIsAdmin(boolean bool) {
        this.isAdmin = true;
        Firebase userRef = new Firebase(USERSURL);
        userRef = userRef.child(this.email + "/isAdmin");
        userRef.setValue(bool);
    }

}
