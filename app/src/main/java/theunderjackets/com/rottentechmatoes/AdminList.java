package theunderjackets.com.rottentechmatoes;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Set;

public class AdminList {
    private static Map<Admin, String> userNames = new HashMap<>();
    private static Set<Admin> admins = new HashSet<>();

    /**
     * empty constructor
     */
    private AdminList() {
    }

    /**
     * checks the list of admins to see if it already exists in the map
     * @param admin admin to check list
     * @return true if the userName is taken, false otherwise
     */

    public static boolean adminExists(Admin admin) {
        return admins.contains(admin);
    }

    /**
     * adds the given admin to the list
     * @param admin admin to be added
     */
    public static void addAdmin(Admin admin) {
        userNames.put(admin, admin.getUserName());
        admins.add(admin);
    }

    /**
     *
     * Checks to see if the username is already used. This is used during admin creation.
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
    public static boolean isAdminValid(String username, String password) {
        Admin admin;
        if (!AdminList.isUserNameValid(username)) {
            admin = AdminList.getAdminByUsername(username);
        } else {
            return false;
        }
        return admin.validatePassword(password);
    }


    /**
     *
     * Getter method for admin by username.
     * @param username username of the requested admin
     * @return Admin if found, NoSuchElementException otherwise
     * @throws NoSuchElementException if not found
     */
    public static Admin getAdminByUsername(String username) throws NoSuchElementException {
        for (Admin a: admins) {
            if (username.equalsIgnoreCase(a.getUserName())) {
                return a;
            }
        }
        throw new NoSuchElementException("admin does not exist");

    }


}
