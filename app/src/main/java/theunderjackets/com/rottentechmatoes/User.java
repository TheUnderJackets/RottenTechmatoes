package theunderjackets.com.rottentechmatoes;


import android.os.Parcel;
import android.os.Parcelable;

public class User implements Parcelable {
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

    /**
     * Private constructor for implementation of Parcelable.
     * @param in parcel used to construct the object
     */
    private User(Parcel in) {
        this.userName = in.readString();
        this.pass = in.readString();
        this.email = in.readString();
        this.name = in.readString();
        this.major = (Major) in.readSerializable();
        this.description = in.readString();
        this.banned = (Boolean) in.readValue(null);
        this.locked = (Boolean) in.readValue(null);
    }

    /**
     * Implementation of Parcelable. Ensures that the read/write are given in FIFO.
     * @param dest destination parcel
     * @param flags flags
     */
    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(userName);
        dest.writeString(pass);
        dest.writeString(email);
        dest.writeString(name);
        dest.writeSerializable(major);
        dest.writeString(description);
        dest.writeValue(banned);
        dest.writeValue(locked);
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
