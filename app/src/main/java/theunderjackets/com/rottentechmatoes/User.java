package theunderjackets.com.rottentechmatoes;


public class User {
    private String userName;
    private String pass;
    private String email;
    private String name;
    private Major major = Major.UNDECIDED;
    private String description = "";
    private boolean banned;
    private boolean locked;

    //This is the only constructor necessary since these are the required fields, and
    //we will have a separate page for the rest. DELETE THIS LATER.
    public User(String name, String email, String pass, String userName) {
        this.userName = userName;
        this.name = name;
        this.pass = pass;
        this.email = email;
    }

    public String getUserName() {
        return userName;
    }

    public String getEmail() {
        return email;
    }

    public String getName() {
        return name;
    }

    public boolean getBanned() {
        return banned;
    }

    public boolean getLocked() {
        return locked;
    }

    public void setBanned(boolean bool) {
        this.banned = bool;
    }
    public void setLocked(boolean bool) {
        this.locked = bool;
    }

    public void changePass(String pass) {
        this.pass = pass;
    }

    public Major getMajor() {
        return major;
    }

    public String getDescription() {
        return description;
    }

    public void setMajor(Major major) {
        this.major = major;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setUserName(String name) {
        this.userName = name;
    }

    public void setDescription(String desc) {
        this.description = desc;
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
