package theunderjackets.com.rottentechmatoes;


public class User {
    private String userName;
    private String pass;
    private String email;
    private String name;
    private String major;
    //private RatingList ratings;

    public User(String name, String email, String pass, String userName) {
        this.userName = userName;
        this.name = name;
        this.pass = pass;
        this.email = email;
    }

    public User(String name, String email, String pass, String userName, String major) {
        this(name, email, pass, userName);
        this.major = major;
    }

    public String getUserName() {
        return userName;
    }

    public String getEmail() {
        return email;
    }

    public void changeEmail(String email) {
        this.email = email;
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

    public void changeMajor(String major) {
        this.major = major;
    }

}
