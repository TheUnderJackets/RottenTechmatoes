package theunderjackets.com.rottentechmatoes;

/**
 * Created by Andrew Suh on 2/18/2016.
 */
public class CurrentUser {
    private static CurrentUser currentInstance = new CurrentUser();
    private User currentUser;
    private CurrentUser() {
    }

    /**
     * Getter method for the Singleton Pattern.
     * @return the instance of CurrentUser
     */
    public static CurrentUser getInstance() {
        return currentInstance;
    }

    /**
     * Getter methpd for the current user.
     * @return the current user
     * @throws IllegalStateException if there is no current user
     */
    public User getUser() {
        if (currentUser == null) {
            throw new IllegalStateException("There is no current user.");
        }
        return currentUser;
    }

    /**
     * Setter method to set the current user
     * @param user user to set the currentUser to
     */
    public void setUser(User user) {
        currentUser = user;
    }
}
