package theunderjackets.com.rottentechmatoes;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Set;

/**
 * Utility class to handle high level Admin management.
 */
final class AdminList {
    private static final Map<Admin, String> userNames = new HashMap<>();
    private static final Set<Admin> admins = new HashSet<>();

    /**
     * Empty AdminList constructor to prevent this from being instantiated, is just a utility class.
     */
    private AdminList() {
    }

    /**
     * Adds the given admin to the list. This currently isn't used, but would be necessary if we
     * use functionality to add admins using other admins.
     *
     * @param admin admin to be added
     */
    public static void addAdmin(Admin admin) {
        userNames.put(admin, admin.getUserName());
        admins.add(admin);
    }

    /**
     * Removes the admin from the admin list. Currently not used, but if we wanted to add this
     * functionality, we would need this.
     * @param username username of the admin
     */
    public static void removeAdmin(String username) {
        Admin admin = null;
        for (final Map.Entry<Admin, String> entry : userNames.entrySet()) {
            if (entry.getValue().equals(username)) {
                admin = entry.getKey();
            }
        }
        if (admin == null) {
            throw new NoSuchElementException("Admin was not found.");
        }
        for (final Admin adminFound : admins) {
            if (adminFound.equals(admin)) {
                admins.remove(adminFound);
            }
        }
    }


}
