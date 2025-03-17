package userManagement;

import java.util.ArrayList;
import java.util.List;

import model.LibraryModel;

public class AccountsManager {
	private String dataDirectory;
	private UserDatabaseManager dbManager;
	
	public AccountsManager(String dataDirectory) {
		this.dataDirectory = dataDirectory;
		dbManager = new UserDatabaseManager(dataDirectory);
	}
	
	public boolean userExists(String username) {
		return dbManager.userExists(username);
	}
	
	public void createAccount(String username, String password) {
		dbManager.addUser(username, password);
		UserAccount account = new UserAccount(username, dataDirectory);
	}
	
	public LibraryModel getUserData(String username) {
		UserAccount account = new UserAccount(username, dataDirectory);
		return account.getLibrary(); 
	}
	
	public void updateAccount(String username, LibraryModel library) {
		UserAccount account = new UserAccount(username, dataDirectory);
		account.update(library);
	}
	
	public boolean validatePassword(String username, String password) {
		return dbManager.validatePassword(username, password);
	}
	
}
