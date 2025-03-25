
package userManagement;

import model.LibraryModel;
import model.LibraryModelSerializer;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.Strictness;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;

public class UserAccount {
    private String dataDirectory;
    private String username;
    private LibraryModel library;
    private Gson gson;

    public UserAccount(String username, String dataDirectory) {
        this.username = username;
        this.dataDirectory = dataDirectory;
        
        // Create Gson instance with custom LibraryModelSerializer
        this.gson = new GsonBuilder()
                .setPrettyPrinting()
                .registerTypeAdapter(LibraryModel.class, new LibraryModelSerializer())
                .create();
        
        loadLibrary();
    }

    private void loadLibrary() {
        File file = new File(dataDirectory + "/users/" + username + ".json");
        if (!file.exists()) {
            System.out.println(dataDirectory + "/users/" + username + ".json" + " doesn't exist");
            try (FileWriter writer = new FileWriter(file)) {
                library = new LibraryModel(); // Initialize if file is empty
                gson.toJson(library, writer);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else { 
            try (FileReader reader = new FileReader(file)) {
                // Print the JSON content for debugging
                StringBuilder jsonContent = new StringBuilder();
                int ch;
                while ((ch = reader.read()) != -1) {
                    jsonContent.append((char) ch);
                }

                // Reset the reader
                reader.close();

                // Parse the JSON
                JsonReader jsonReader = new JsonReader(new FileReader(file));
                jsonReader.setStrictness(Strictness.LENIENT); // Enable lenient mode
                Type type = new TypeToken<LibraryModel>() {}.getType();
                library = gson.fromJson(jsonReader, type);
                
                // Initialize if file is empty
                if (library == null) {
                	library = new LibraryModel(); 
                }  
            
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void update(LibraryModel library) {
        this.library = new LibraryModel(library);

        try (FileWriter writer = new FileWriter(dataDirectory + "/users/" + username + ".json")) {
            gson.toJson(library, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getUsername() {
        return username;
    }

    public LibraryModel getLibrary() {
        return new LibraryModel(library);
    }
}
