package theunderjackets.com.rottentechmatoes;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.Timeout;

import java.lang.IllegalArgumentException;
import java.util.NoSuchElementException;

import static org.junit.Assert.*;

/**
 * Tests the UserList.isUserValid(String username, String password) method,
 * and the UserList.getUserByUsername(String username) method.
 * Created by Andrew Suh and Lixin Wang on 4/6/2016.
 */
public class UserListTest {

    private User user;

    @Rule
    public Timeout globalTimeout = new Timeout(1000);

    @Before
    public void setUp() {
        user = new User("Testing User", "TestEmail", "Password", "TestingUser", false);
        UserList.addUserLocal(user);
        UserList.addUserLocal(new User("Another User", "AnotherEmail", "Password", "AnotherUser", false));
    }

    @Test
    public void test01UserNameNotValid() {
        int ret = UserList.isUserValid("IncorrectUser", "IncorrectPassword");
        assertEquals(1, ret);
    }

    @Test
    public void test02UserBanned() {
        user = new User("Banned User", "TestEmail2", "Password", "BannedUser", false, true, false);
        UserList.addUserLocal(user);
        int ret = UserList.isUserValid("BannedUser", "Password");
        assertEquals(2, ret);
    }

    @Test
    public void test03UserLocked() {
        user = new User("Locked User", "TestEmail3", "Password", "LockedUser", false, false, true);
        UserList.addUserLocal(user);
        int ret = UserList.isUserValid("LockedUser", "Password");
        assertEquals(3, ret);
    }

    @Test
    public void test04UserCorrect() {
        user = new User("Regular User", "TestEmail4", "Password", "RegularUser", false, false, false);
        UserList.addUserLocal(user);
        int ret = UserList.isUserValid("RegularUser", "Password");
        assertEquals(0, ret);
    }

    @Test
    public void test05UserInCorrect() {
        user = new User("Regular User", "TestEmail4", "Password", "RegularUser", false, false, false);
        UserList.addUserLocal(user);
        int ret = UserList.isUserValid("RegularUser", "InPassword");
        assertEquals(4, ret);
    }



    @Test (expected = IllegalArgumentException.class)
    public void testGetNullUser() {
        UserList.getUserByUsername(null);
    }

    @Test (expected = NoSuchElementException.class)
    public void testGetEmptyString() {
        UserList.getUserByUsername("");
    }

    @Test (expected = NoSuchElementException.class)
    public void testGetUserNotExist() {
        UserList.getUserByUsername("UserWhoDoesNotExist");
    }

    @Test (expected = NoSuchElementException.class)
    public void testGetUserNotFound() {
        User fakeUser = new User("Fake User", "FakeEmail", "Password", "FakeUser", false);
        UserList.addUserLocal(fakeUser);
        UserList.getUserByUsername("fakeuser  ");
    }

    @Test
    public void testGetValidUser() {
        User validUser = new User("Valid User", "ValidEmail", "Password", "ValidUser", false);
        UserList.addUserLocal(validUser);
        assertSame(UserList.getUserByUsername("ValidUser"), validUser);
        // case not sensitive:
        assertSame(UserList.getUserByUsername("VaLiDuSeR"), validUser);
    }
}