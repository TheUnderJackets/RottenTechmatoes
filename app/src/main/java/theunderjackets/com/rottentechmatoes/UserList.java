package theunderjackets.com.rottentechmatoes;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.HashSet;
import java.util.NoSuchElementException;

public final class UserList {
    private static Map<User, String> userNames = new HashMap<>();
    private static Set<User> users = new HashSet<>();
    private static final String URLREF = "https://rottentechmatoes.firebaseio.com/";
    private UserList() {
    }

    /**
     * checks the list of users to see if it already exists in the map
     * @param user user to check list
     * @return true if the userName is taken, false otherwise
     */

    public static boolean userExists(User user) {
        return users.contains(user);
    }

    /**
     * adds the given user to the list
     * @param user user to be added
     */
    public static void addUser(User user) {

    }

    /**
     * Get the set of users.
     * @return users
     */
    public static Set<User> getUsers() {
        return users;
    }

    /**
     * updates the list based on the given user
     * @param user user whose information has changed
     */
    public static void updateUser(User user) {
        userNames.put(user, user.getUserName());
    }

    /**
     *
     * Checks to see if the username is already used. This is used during user creation.
     * @param username the username we are checking for
     * @return true if username is valid, false otherwise
     */
    public static boolean isUserNameValid(String username) {
        return !userNames.containsValue(username);
    }

    /**
     * Checks to see if the username and password combo is valid.
     * @param username username to check
     * @param password password to check
     * @return true if login attempt is valid, false otherwise
     */
    public static int isUserValid(String username, String password) {
        User user;
        if (!UserList.isUserNameValid(username)) {
            user = UserList.getUserByUsername(username);
        } else {
            return 1;
        }
        if (user.getBanned() == true){
            return 2;
        } else if (user.getLocked()) {
            return 3;
        } else if(user.validatePassword(password)) {
            return 0;
        }
        return 4;
    }

    /**
     * Checks to see if the email entered during registration is taken or not.
     * @param email email to be verified
     * @return true if not used, false otherwise
     */
    public static boolean isEmailValid(String email) {
        for (User user : users) {
            if (user.getEmail().equals(email)) {
                return false;
            }
        }
        return true;
    }

    /**
     *
     * Getter method for user by username.
     * @param username username of the requested user
     * @return user if found, NoSuchElementException otherwise
     */
    public static User getUserByUsername(String username) throws NoSuchElementException {
        for (User u: users) {
            if (username.equalsIgnoreCase(u.getUserName())) {
                return u;
            }
        }
        throw new NoSuchElementException("user does not exist");

    }

    /**
     *
     * Getter method for user by email.
     * @param email email of the requested user
     * @return user if found, NoSuchElementException otherwise
     */
    public static User getUserByEmail(String email) throws NoSuchElementException {
        for (User u: users) {
            if (email.equalsIgnoreCase(u.getEmail())) {
                return u;
            }
        }
        throw new NoSuchElementException("user does not exist");

    }
}
