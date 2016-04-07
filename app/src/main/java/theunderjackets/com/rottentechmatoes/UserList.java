package theunderjackets.com.rottentechmatoes;

import com.firebase.client.Firebase;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Set;

/**
 * Many of these method are deprecated due to implementation of a FireBase. However, we may still
 * want to use these for later use with offline access. Otherwise, this is a utility, managerial
 * class for Users.
 */
public final class UserList {
    private static final Map<User, String> userNames = new HashMap<>();
    private static final Set<User> users = new HashSet<>();
    /**
     * Both of these are intended to be constants, so naming is acceptable.
     */
    public static final String URLREF = "https://rottentechmatoes.firebaseio.com/";
    public static final String USERSURL = "https://rottentechmatoes.firebaseio.com/users";

    /**
     * empty UserList constructor
     */
    private UserList() {
    }

    /**
     * Checks the list of users to see if it already exists in the map
     *
     * @param user user to check list
     * @return true if the userName is taken, false otherwise
     */

    public static boolean userExists(User user) {
        return users.contains(user);
    }

    /**
     * adds the given user to the list
     *
     * @param user user to be added
     */
    public static void addUser(User user) {
        final Firebase usersRef = new Firebase(USERSURL).child(user.getEmail());
        usersRef.setValue(user);
        addUserLocal(user);
    }

    /**
     * Adds the user to the list locally.
     * @param user user to be added
     */
    public static void addUserLocal(User user) {
        users.add(user);
        userNames.put(user, user.getUserName());
    }

    /**
     * Get the set of users.
     *
     * @return users
     */
    public static Set<User> getUsers() {
        return users;
    }

    /**
     * updates the list based on the given user
     *
     * @param user user whose information has changed
     */
    public static void updateUser(User user) {
        final Firebase userRef = new Firebase("https://rottentechmatoes.firebaseio.com/users/" + user.getEmail());
        userRef.setValue(user);
    }

    /**
     * Checks to see if the username is already used. This is used during user creation. Might need
     * to be public for future use.
     *
     * @param username the username we are checking for
     * @return true if username is valid, false otherwise
     */
    public static boolean isUserNameValid(String username) {
        return !userNames.containsValue(username);
    }

    /**
     * Checks to see if the username and password combo is valid. The numbers are exit codes.
     *
     * @param username username to check
     * @param password password to check
     * @return numbers based on exit code
     */
    public static int isUserValid(String username, String password) {
        User user;
        if (!UserList.isUserNameValid(username)) {
            user = UserList.getUserByUsername(username);
        } else {
            return 1;
        }
        if (user.getBanned()) {
            return 2;
        } else if (user.getLocked()) {
            return 3;
        } else if (user.validatePassword(password)) {
            return 0;
        }
        return 4;
    }

    /**
     * Checks to see if the email entered during registration is taken or not.
     *
     * @param email email to be verified
     * @return true if not used, false otherwise
     */
    public static boolean isEmailValid(String email) {
        for (final User user : users) {
            if (user.getEmail().equals(email)) {
                return false;
            }
        }
        return true;
    }

    /**
     * Getter method for user by username. Used with local copy. Might need to be public for future
     * use.
     *
     * @param username username of the requested user
     * @return user if found, NoSuchElementException otherwise
     */
    public static User getUserByUsername(String username) {
        for (final User u : users) {
            if (username.equalsIgnoreCase(u.getUserName())) {
                return u;
            }
        }
        throw new NoSuchElementException("user does not exist");

    }

    /**
     * Getter method for user by email.
     *
     * @param email email of the requested user
     * @return user if found, NoSuchElementException otherwise
     */
    public static User getUserByEmail(String email) {
        for (final User u : users) {
            if (email.equalsIgnoreCase(u.getEmail())) {
                return u;
            }
        }
        throw new NoSuchElementException("user does not exist");

    }
}
