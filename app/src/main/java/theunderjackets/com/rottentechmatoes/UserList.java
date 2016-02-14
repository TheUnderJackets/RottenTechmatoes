package theunderjackets.com.rottentechmatoes;

import java.util.HashMap;
import java.util.Map;

public class UserList {
    private static Map users;

    public UserList() {
        Map<User,String> users = new HashMap<>();
    }

    /**
     * checks the list of users to see if it already exists in the map
     * @param user user to check list
     * @return true if the userName is taken, false otherwise
     */

    public boolean userExists(User user) {
        return users.containsKey(user);
    }

    /**
     * adds the given user to the list
     * @param user user to be added
     * @return true if user was added to the list, false otherwise
     */

    public boolean addUser(User user) {
        if (!userExists(user)) {
            users.put(user,user.getPass());
            return true;
        }
        return false;
    }

    /**
     * TODO
     * Checks to see if the username is already used. This is used during user creation.
     * @param username the username we are checking for
     * @return true if username is valid, false otherwise
     */
    public static boolean isUserValid(String username) {
        return true;
    }

    /**
     * TODO
     * Getter method for user by username.
     * @param username username of the requested user
     * @return user if found
     */
    public static User getUserByUsername(String username) {
        return new User("a", "a", "a", "a");
    }

    /**
     * TODO
     * Getter method for user by email.
     * @param email email of the requested user
     * @return user if found
     */
    public static User getUserByEmail(String email) {
        return new User("a", "a", "a", "a");
    }
}