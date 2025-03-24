
package userManagement;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class UserDatabaseManager {
    private String dataDirectory;
    public static String passWord;
    private Map<String, String> users = new HashMap<>(); // maps usernames to passwords
    Gson gson = new Gson();

    public UserDatabaseManager(String databaseDirectory) {
        this.dataDirectory = databaseDirectory;
        loadDatabase();
    }

    private void loadDatabase() {
        File file = new File(dataDirectory + "/user_database.json");
        if (!file.exists()) {
            try (FileWriter writer = new FileWriter(file)) {
                writer.write("{}"); // Create an empty JSON file
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        try (FileReader reader = new FileReader(file)) {
            Type type = new TypeToken<Map<String, String>>() {}.getType();
            users = gson.fromJson(reader, type);
            if (users == null) {
                users = new HashMap<>(); // Initialize if file is empty
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void saveDatabase() {
        try (FileWriter writer = new FileWriter(dataDirectory + "/user_database.json")) {
            gson.toJson(users, writer);
        } catch (IOException e) {
            e.printStackTrace(); 
        }
    }
    public static String hashPassword(String password) {
        try {
            // Derive a "deterministic salt" from the password itself
            MessageDigest saltMd = MessageDigest.getInstance("SHA-256");
            byte[] salt = saltMd.digest(password.getBytes()); // Salt is derived from password

            // Hash the password using SHA-256
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            md.update(salt);
            byte[] hashedPassword = md.digest(password.getBytes());

            // Encode salt and hash separately in Base64
            String saltString = Base64.getEncoder().encodeToString(salt);
            String hashString = Base64.getEncoder().encodeToString(hashedPassword);

            // Return the salt and hash separated by ":"
            return saltString + ":" + hashString;
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Error hashing password", e);
        }
    }

    public void addUser(String username, String password) {
        users.put(username, hashPassword(password));
        saveDatabase();
    }
    
    public boolean validatePassword(String username, String password) {
    	// TODO: unhash + unsalt password
    	return users.get(username).equals(hashPassword(password));
    }

    public boolean userExists(String username) {
        return users.containsKey(username);
    }
}
