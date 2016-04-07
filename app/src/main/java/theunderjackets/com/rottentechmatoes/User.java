package theunderjackets.com.rottentechmatoes;


import android.os.Parcel;
import android.os.Parcelable;

import com.firebase.client.Firebase;

public class User implements Parcelable {
    /**
     * Is protected instead of private so that Admin will work properly.
     */
    protected boolean isAdmin;
    private String userName;
    private String pass;
    /**
     * Is protected instead of private so that Admin will work properly.
     */
    protected String email;
    private String name;
    private Major major = Major.UNDECIDED;
    private String description = "";
    private boolean banned;
    private boolean locked;
    /**
     * This has same name as getter method since it is logical with is prefix. Others have getters
     * strictly because FireBase requires it.
     */
    private boolean isSelected;
    /**
     * This is a constant, so it's expected to be in all caps for emphasis.
     */
    public static final String USERSURL = "https://rottentechmatoes.firebaseio.com/users";

    /**
     * Empty User Constructor. Necessary for FireBase to work correctly.
     */
    public User() {

    }

    /**
     * User constructor
     *
     * @param name       of user
     * @param email      of user
     * @param pass       password of user
     * @param userName   of user
     * @param isSelected if user is selected
     */
    public User(String name, String email, String pass, String userName, boolean isSelected) {
        this.userName = userName;
        this.name = name;
        this.pass = pass;
        this.email = email;
        this.isSelected = isSelected;
    }

    /**
     * Most descriptive constructor for a user.
     * @param name name of user
     * @param email email of user
     * @param pass password of user
     * @param userName username of user
     * @param isSelected whether or not this should be selected under manage users
     * @param isBanned whether or not this account is banned
     * @param isLocked whether or not this account is locked
     */
    public User(String name, String email, String pass, String userName, boolean isSelected, boolean isBanned, boolean isLocked) {
        this.userName = userName;
        this.name = name;
        this.pass = pass;
        this.email = email;
        this.isSelected = isSelected;
        banned = isBanned;
        locked = isLocked;
    }

    /**
     * Private constructor for implementation of Parcelable.
     *
     * @param in parcel used to construct the object
     */
    public User(Parcel in) {
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
     *
     * @param dest  destination parcel
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
     *
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
     * gets user's username
     *
     * @return userName
     */
    public String getUserName() {
        return userName;
    }

    /**
     * gets user's email
     *
     * @return email
     */
    public String getEmail() {
        return email;
    }

    /**
     * gets user's name
     *
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * gets user's password. Necessary for FireBase to work.
     *
     * @return pass
     */
    public String getPass() {
        return pass;
    }

    /**
     * changes user's password
     *
     * @param pass to update
     */
    public void changePass(String pass) {
        this.pass = pass;
    }

    /**
     * gets user's major
     *
     * @return major
     */
    public Major getMajor() {
        return major;
    }

    /**
     * gets user's description
     *
     * @return description
     */
    public String getDescription() {
        return description;
    }

    /**
     * gets user's banned status
     *
     * @return banned
     */
    public boolean getBanned() {
        return banned;
    }

    /**
     * gets user's locked status
     *
     * @return locked
     */
    public boolean getLocked() {
        return locked;
    }

    /**
     * Gets user's selected status. Necessary for FireBase to work.
     *
     * @return isSelected
     */
    public boolean getIsSelected() {
        return isSelected;
    }

    /**
     * gets user's admin status
     *
     * @return isAdmin
     */
    public boolean getIsAdmin() {
        return this.isAdmin;
    }

    /**
     * Method to ban or un-ban a User
     *
     * @param bool to ban/un-ban a user
     */
    public void setBanned(boolean bool) {
        this.banned = bool;
        Firebase userRef = new Firebase(USERSURL);
        userRef = userRef.child(this.email + "/banned");
        userRef.setValue(bool);
    }

    /**
     * Method to lock or un-lock a User
     *
     * @param bool to ban/un-ban a user
     */
    public void setLocked(boolean bool) {
        this.locked = bool;
        Firebase userRef = new Firebase(USERSURL);
        userRef = userRef.child(this.email + "/locked");
        userRef.setValue(bool);
    }

    /**
     * determine if a user is selected
     *
     * @return isSelected
     */
    public boolean isSelected() {
        return isSelected;
    }

    /**
     * Method to select/un-select a user
     *
     * @param isSelected to select/un-select a user
     */
    public void setSelected(boolean isSelected) {
        this.isSelected = isSelected;
        Firebase userRef = new Firebase(USERSURL);
        userRef = userRef.child(this.email + "/isSelected");
        userRef.setValue(isSelected);
    }

    /**
     * Method to set user's major
     *
     * @param major to assign to user
     */
    public void setMajor(Major major) {
        this.major = major;
        Firebase userRef = new Firebase(USERSURL);
        userRef = userRef.child(this.email + "/major");
        userRef.setValue(major);
    }

    /**
     * Method to set user's name
     *
     * @param name to assign to user
     */
    public void setName(String name) {
        this.name = name;
        Firebase userRef = new Firebase(USERSURL);
        userRef = userRef.child(this.email + "/name");
        userRef.setValue(name);
    }

    /**
     * Method to set user's username
     *
     * @param name to assign to user
     */
    public void setUserName(String name) {
        this.userName = name;
        Firebase userRef = new Firebase(USERSURL);
        userRef = userRef.child(this.email + "/userName");
        userRef.setValue(name);
    }

    /**
     * Method to set user's description
     *
     * @param desc to assign to user
     */
    public void setDescription(String desc) {
        this.description = desc;
        Firebase userRef = new Firebase(USERSURL);
        userRef = userRef.child(this.email + "/description");
        userRef.setValue(desc);
    }

    /**
     * Checks if the password passed in is the same as the actual password.
     *
     * @param pass password to be checked
     * @return true if they are equal, false otherwise
     */
    public boolean validatePassword(String pass) {
        return this.pass.equals(pass);
    }
}
