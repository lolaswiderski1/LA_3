package test;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;

import org.junit.jupiter.api.Test;

import dataStructures.Song;
import model.LibraryModel;
import userManagement.AccountsManager;
import userManagement.UserAccount;
import userManagement.UserDatabaseManager;

class UserManagementTest {

	@Test
	void AMuserExists() {
		AccountsManager AM = new AccountsManager("data");
		AM.createAccount("username", "password");
		assertTrue(AM.userExists("username"));
		assertFalse(AM.userExists("username1"));
	}
	
	@Test
    void AMgetUserData() {
        AccountsManager AM = new AccountsManager("data");
        AM.createAccount("username", "password");
        LibraryModel expectedLibrary = AM.getUserData("username");
        assertNotNull(expectedLibrary, "Library should not be null");
    }
	
	@Test
	void AMtestValidatePassword() {
	    AccountsManager AM = new AccountsManager("testData"); 
	    String password = "securePassword";
	    AM.createAccount("testUser", password); 
	    assertTrue(AM.validatePassword("testUser", "securePassword"));
	    assertFalse(AM.validatePassword("testUser", "wrongPassword"));
	}
	
	@Test
	void UAgetUserName() {
		UserAccount user = new UserAccount("username", "data");
		assertEquals("username", user.getUsername());
	}
	
	
}




