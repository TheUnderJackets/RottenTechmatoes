package theunderjackets.com.rottentechmatoes;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.HashSet;
import java.util.NoSuchElementException;

public class UserList {
    private static Map<User, String> userNames = new HashMap<>();
    private static Set<User> users = new HashSet<>();

    public UserList() {

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
        userNames.put(user, user.getUserName());
        users.add(user);
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
    public static boolean isUserValid(String username) {
        return !userNames.containsValue(username);
    }

    /**
     * Checks to see if the username and password combo is valid.
     * @param username username to check
     * @param password password to check
     * @return true if login attempt is valid, false otherwise
     */
    public static boolean isUserValid(String username, String password) {
        User user;
        if (!UserList.isUserValid(username)) {
            user = UserList.getUserByUsername(username);
        } else {
            return false;
        }
        if (user.getPass().equals(password)) {
            return true;
        }
        return false;
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