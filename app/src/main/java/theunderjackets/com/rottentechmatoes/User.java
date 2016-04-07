package theunderjackets.com.rottentechmatoes;


import android.os.Parcel;
import android.os.Parcelable;

import com.firebase.client.Firebase;

public class User implements Parcelable {
    protected boolean isAdmin;
    private String userName;
    private String pass;
    protected String email;
    private String name;
    private Major major = Major.UNDECIDED;
    private String description = "";
    private boolean banned;
    private boolean locked;
    private boolean isSelected;
    private static final String USERSURL = "https://rottentechmatoes.firebaseio.com/users";

    /**
     * empty constructor
     */
    public User() {

    }

    /**
     * constructor for users
     * @param name name of user
     * @param email email of user
     * @param pass password for user
     * @param userName username of user
     * @param isSelected whether or not its selected
     */
    public User(String name, String email, String pass, String userName, boolean isSelected) {
        this.userName = userName;
        this.name = name;
        this.pass = pass;
        this.email = email;
        this.isSelected = isSelected;
        Firebase usersRef = new Firebase(USERSURL).child(this.email);
        usersRef.setValue(this);
    }

    /**
     * Private constructor for implementation of Parcelable.
     * @param in parcel used to construct the object
     */
    private User(Parcel in) {
        this.isAdmin = (Boolean) in.readValue(null);
        this.userName = in.readString();
        this.pass = in.readString();
        this.email = in.readString();
        this.name = in.readString();
        this.major = (Major) in.readSerializable();
        this.description = in.readString();
        this.banned = (Boolean) in.readValue(null);
        this.locked = (Boolean) in.readValue(null);
        this.isSelected = (Boolean) in.readValue(null);
    }

    /**
     * Implementation of Parcelable. Ensures that the read/write are given in FIFO.
     * @param dest destination parcel
     * @param flags flags
     */
    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(isAdmin);
        dest.writeString(userName);
        dest.writeString(pass);
        dest.writeString(email);
        dest.writeString(name);
        dest.writeSerializable(major);
        dest.writeString(description);
        dest.writeValue(banned);
        dest.writeValue(locked);
        dest.writeValue(isSelected);
    }

    /**
     * Basic implementation of describeContents(). For our purposes, we have no need to customize
     * the implementation.
     * @return 0
     */
    @Override
    public int describeContents() {
        return 0;
    }

    /**
     * Constant for implementation of Parcelable.
     */
    public static final Parcelable.Creator<User> CREATOR =
            new Parcelable.Creator<User>() {

                @Override
                public User createFromParcel(Parcel source) {
                    return new User(source);
                }

                @Override
                public User[] newArray(int size) {
                    return new User[size];
                }
            };

    /**
     * getter for username
     * @return username of user
     */
    public String getUserName() {
        return userName;
    }

    /**
     * getter for email
     * @return users email
     */
    public String getEmail() {
        return email;
    }

    /**
     * getter for name
     * @return users name
     */
    public String getName() {
        return name;
    }

    /**
     * getter for password
     * @return users password
     */
    public String getPass() {
        return pass;
    }

    /**
     * changes users password
     * @param pass new password
     */
    public void changePass(String pass) {
        this.pass = pass;
    }

    /**
     * getter for major
     * @return users major
     */
    public Major getMajor() {
        return major;
    }

    /**
     * getter for description
     * @return description of user
     */
    public String getDescription() {
        return description;
    }

    /**
     * getter for banned
     * @return true if user is banned, false otherwise
     */
    public boolean getBanned() {
        return banned;
    }

    /**
     * getter for locked
     * @return true if locked, false otherwise
     */
    public boolean getLocked() {
        return locked;
    }

    /**
     * getter for isAdmin
     * @return true if admin, false otherwise
     */
    public boolean getIsAdmin() {
        return this.isAdmin;
    }

    /**
     * setter for banned
     * @param bool value of banned field
     */
    public void setBanned(boolean bool) {
        this.banned = bool;
        Firebase userRef = new Firebase(USERSURL);
        userRef = userRef.child(this.email + "/banned");
        userRef.setValue(bool);
    }

    /**
     * setter for locked
     * @param bool value of locked field
     */
    public void setLocked(boolean bool) {
        this.locked = bool;
        Firebase userRef = new Firebase(USERSURL);
        userRef = userRef.child(this.email + "/locked");
        userRef.setValue(bool);
    }

    /**
     * getter for isSelected
     * @return true if selected, false otherwise
     */
    public boolean isSelected() {
        return isSelected;
    }

    /**
     * setter for selected
     * @param isSelected value of selected field
     */
    public void setSelected(boolean isSelected) {
        this.isSelected = isSelected;
        Firebase userRef = new Firebase(USERSURL);
        userRef = userRef.child(this.email + "/isSelected");
        userRef.setValue(isSelected);
    }

    /**
     * setter for major
     * @param major users new major
     */
    public void setMajor(Major major) {
        this.major = major;
        Firebase userRef = new Firebase(USERSURL);
        userRef = userRef.child(this.email + "/major");
        userRef.setValue(major);
    }

    /**
     * setter for name
     * @param name users name
     */
    public void setName(String name) {
        this.name = name;
        Firebase userRef = new Firebase(USERSURL);
        userRef = userRef.child(this.email + "/name");
        userRef.setValue(name);
    }

    /**
     * setter for username
     * @param name username of user
     */
    public void setUserName(String name) {
        this.userName = name;
        Firebase userRef = new Firebase(USERSURL);
        userRef = userRef.child(this.email + "/userName");
        userRef.setValue(name);
    }

    /**
     * setter for description
     * @param desc description of user
     */
    public void setDescription(String desc) {
        this.description = desc;
        Firebase userRef = new Firebase(USERSURL);
        userRef = userRef.child(this.email + "/description");
        userRef.setValue(desc);
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
