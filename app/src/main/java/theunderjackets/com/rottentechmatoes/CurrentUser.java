package theunderjackets.com.rottentechmatoes;

/**
 * Utilizes the singleton pattern to enforce a single use logged in at a time.
 * Created by Andrew Suh on 2/18/2016.
 */
public final class CurrentUser {
    private static final CurrentUser currentInstance = new CurrentUser();
    private User currentUser;

    /**
     * empty CurrentUser constructor
     */
    private CurrentUser() {
    }

    /**
     * Getter method for the Singleton Pattern.
     *
     * @return the instance of CurrentUser
     */
    public static CurrentUser getInstance() {
        return currentInstance;
    }

    /**
     * Getter method for the current user.
     *
     * @return the current user
     */
    public User getUser() {
        if (currentUser == null) {
            throw new IllegalStateException("There is no current user.");
        }
        return currentUser;
    }

    /**
     * Setter method to set the current user
     *
     * @param user user to set the currentUser to
     */
    public void setUser(User user) {
        currentUser = user;
    }
}
