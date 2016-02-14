package theunderjackets.com.rottentechmatoes;


public class User {
    private String userName;
    private String pass;
    private String email;
    private String name;
    private Major major;
    private String bio;

    public User(String name, String email, String pass, String userName, String major, String bio) {
        this.userName = userName;
        this.name = name;
        this.pass = pass;
        this.email = email;
        this.major = major;
        this.bio = bio;
    }

    public User(String name, String email, String pass, String userName) {
        this(name, email, pass, userName, "", "");
    }

    public String getUserName() {
        return userName;
    }

    public void changeUserName(String userName) {
        this.userName = userName;
    }

    public String getEmail() {
        return email;
    }

    public String getName() {
        return name;
    }

    public String getPass() {
        return pass;
    }

    public void changePass(String pass) {
        this.pass = pass;
    }

    public String getMajor() {
        return major;
    }

    public void changeMajor(Major major) {
        this.major = major;
    }

    public String getBio() {
        return bio;
    }

    public void changeBio(String newBio) {
        this.bio = newBio;
    }

}
