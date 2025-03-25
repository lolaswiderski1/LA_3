// Authors: Sam Hershey, Lola Swiderski
// Description:	Used to create and access user accounts. Adds new usernames
// and associated passwords to a database.
package userManagement;

import model.LibraryModel;

public class AccountsManager {
	private String dataDirectory; // name of folder containing all json files
	private UserDatabaseManager dbManager;
	
	public AccountsManager(String dataDirectory) {
		this.dataDirectory = dataDirectory;
		dbManager = new UserDatabaseManager(dataDirectory);
	}
	
	// returns true if account with username exists
	public boolean userExists(String username) {
		return dbManager.userExists(username);
	}
	
	// creates new file <username>.json with no data
	public void createAccount(String username, String password) {
		// adds username/password to database
		dbManager.addUser(username, password); 
		// creates new account with username
		UserAccount account = new UserAccount(username, dataDirectory);
	}
	
	// returns data (library) for given username
	public LibraryModel getUserData(String username) {
		// creates account object to access data in <username>.json
		UserAccount account = new UserAccount(username, dataDirectory);
		return account.getLibrary(); 
	}
	
	public void updateAccount(String username, LibraryModel library) {
		// creates account object to store new data in <username>.json
		UserAccount account = new UserAccount(username, dataDirectory);
		account.update(library);
	}
	
	// returns true if password is correct for username
	public boolean validatePassword(String username, String password) {
		return dbManager.validatePassword(username, password);
	}	
}
