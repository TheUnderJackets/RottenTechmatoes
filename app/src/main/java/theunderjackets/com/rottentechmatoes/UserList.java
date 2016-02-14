package theunderjackets.com.rottentechmatoes;

import java.util.Set;
import java.util.HashSet;

public class UserList {
    private static Set userNames;

    public UserList() {
        Set<String> userNames = new HashSet<>();
    }

    /**
     * checks the list of users to see if it already exists in the map
     * @param userName user to check list
     * @return true if the userName is taken, false otherwise
     */

    public boolean userNameExists(String userName) {
        return userNames.contains(userName);
    }

    /**
     * adds the given user to the list
     * @param userName user to be added
     * @return true if user was added to the list, false otherwise
     */

    public boolean addUser(String userName) {
        return userNames.add(userName);
    }


}
