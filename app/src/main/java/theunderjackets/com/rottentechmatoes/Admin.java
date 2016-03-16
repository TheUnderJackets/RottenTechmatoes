package theunderjackets.com.rottentechmatoes;


public class Admin extends User {

    private String userName;
    private String pass;

    public Admin(String userName, String pass) {
        super(null, null, pass, userName);
        this.userName = userName;
        this.pass = pass;
    }

    public String getUserName() {
        return userName;
    }

    public void changePass(String pass) {
        this.pass = pass;
    }

    public void setUserName(String name) {
        this.userName = name;
    }

    /**
     * Checks if the password passed in is the same as the actual password.
     * @param pass password to be checked
     * @return true if they are equal, false otherwise
     */
    public boolean validatePassword(String pass) {
        return this.pass.equals(pass);
    }

}
