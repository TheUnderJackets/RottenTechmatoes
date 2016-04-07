package theunderjackets.com.rottentechmatoes;


import android.os.Parcel;
import android.os.Parcelable;

import com.firebase.client.Firebase;

public class Admin extends User {

    private String userName;
    private String pass;
    /**
     * This is a constant, so it's expected to have an all capitalized name and a public modifier.
     */
    public static final String USERSURL = "https://rottentechmatoes.firebaseio.com/users";

    /**
     * Empty Admin constructor. THIS IS NECESSARY FOR FireBase.
     */
    public Admin() {

    }

    /**
     * Admin constructor. This is not used currently, but it if we wanted functionality to create
     * new admins from current admins, this would be necessary.
     *
     * @param email    email to assign
     * @param userName to assign
     * @param pass     to assign
     */
    public Admin(String email, String userName, String pass) {
        super(null, email, pass, userName, false);
        setUserName(userName);
        changePass(pass);
        this.isAdmin = true;
        this.pass = pass;
    }

    /**
     * Determine if an admin
     *
     * @return isAdmin whether user is an admin
     */
    public boolean getIsAdmin() {
        return isAdmin;
    }

    /**
     * Get user's username
     *
     * @return userName
     */
    public String getUserName() {
        return userName;
    }

    /**
     * Get password from the current admin.
     *
     * @return pass
     */
    public String getPass() {
        return this.pass;
    }

    /**
     * Change password for the admin.
     *
     * @param pass new password
     */
    public void changePass(String pass) {
        this.pass = pass;
        Firebase userRef = new Firebase(USERSURL);
        userRef = userRef.child(this.email + "/pass");
        userRef.setValue(pass);
    }

    /**
     * Set password for the admin.
     *
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
     *
     * @param pass password to be checked
     * @return true if they are equal, false otherwise
     */
    public boolean validatePassword(String pass) {
        return this.pass.equals(pass);
    }

    /**
     * Implementation of parcelable with Admin.
     * @param in parcel to rebuild object
     */
    private Admin(Parcel in) {
        super(in);
    }

    /**
     * Constant for implementation of Parcelable. This is necessary for an implementation of
     * parcelable, despite the fact that checkstyle doesn't like it.
     */
    public static final Parcelable.Creator<Admin> CREATOR =
            new Parcelable.Creator<Admin>() {

                @Override
                public Admin createFromParcel(Parcel source) {
                    return new Admin(source);
                }

                @Override
                public Admin[] newArray(int size) {
                    return new Admin[size];
                }
            };
}
