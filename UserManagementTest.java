package tests;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import dataStructures.Album;
import dataStructures.PlayList;
import dataStructures.Song;
import model.LibraryModel;
import model.LibraryModelSerializer;
import userManagement.AccountsManager;
import userManagement.UserAccount;
import userManagement.UserDatabaseManager;

class UserManagementTest {  
    private AccountsManager AM = new AccountsManager("test_data");
    private LibraryModel lib = new LibraryModel();
    private Song song1, song2, song3, song4;
    private Album album1, album2;
    private PlayList playList1, playList2;
    private Gson gson;

    @BeforeEach
    void initialize() {
        gson = new GsonBuilder()
                .registerTypeAdapter(LibraryModel.class, new LibraryModelSerializer())
                .create();

        song1 = new Song("songTitle1", "artist1", "albumTitle1", "genre1");
        song2 = new Song("songTitle2", "artist1", "albumTitle1", "genre2");
        song3 = new Song("songTitle1", "artist2", "albumTitle2", "genre2");
        song4 = new Song("songTitle3", "artist2", "albumTitle2", "genre3");
        
        album1 = new Album("albumTitle1", "artist1", "genre1", "year1");
        album2 = new Album("albumTitle2", "artist2", "genre2", "year2");
        
        playList1 = new PlayList("pl1");
        playList2 = new PlayList("pl2");

        album1.addSong(song1);
        album1.addSong(song2);
        album2.addSong(song3);
        album2.addSong(song4);
        
        playList1.addSong(song1);
        playList1.addSong(song3);
        playList2.addSong(song2);
        playList2.addSong(song4);
        
        lib.addAlbum(album1);
        lib.addAlbum(album2);
        lib.addPlaylist(playList1);
        lib.addPlaylist(playList2);
        
        lib.rateSong(song1, LibraryModel.Rating.FOUR);
        lib.rateSong(song2, LibraryModel.Rating.FIVE);
        lib.addFavorite(song1);
        lib.playSong(song2);
        lib.playSong(song3);
    }
    
    @Test
    void testCatchException() {
    	UserDatabaseManager dbm = new UserDatabaseManager("fake_directory");
    	dbm.saveDatabase();
    }

    @Test
    void testSerializationDeserialization() {
        // Serialize
        String json = gson.toJson(lib, LibraryModel.class);
        assertNotNull(json);
        assertFalse(json.isEmpty());
        
        LibraryModel deserializedLib = gson.fromJson(json, LibraryModel.class);
        
        assertEquals(lib.getAllSongs().size(), deserializedLib.getAllSongs().size());
        assertEquals(lib.getAlbums().size(), deserializedLib.getAlbums().size());
        assertEquals(lib.getPlayLists().size(), deserializedLib.getPlayLists().size());
        assertEquals(lib.getFavorites().size(), deserializedLib.getFavorites().size());
        
        assertEquals(lib.getPlayedSongs().size(), deserializedLib.getPlayedSongs().size());
        
        assertEquals(lib.getRating(song1), deserializedLib.getRating(song1));
        assertEquals(lib.getRating(song2), deserializedLib.getRating(song2));
    }

    @Test
    void testLoadLibrary() {
        AM.createAccount("username0", "password0");
        AM.updateAccount("username0", lib);
        LibraryModel lib2 = AM.getUserData("username0");
        
        assertEquals(lib.getAllSongs().size(), lib2.getAllSongs().size());
        assertEquals(lib.getAlbums().size(), lib2.getAlbums().size());
        assertEquals(lib.getPlayLists().size(), lib2.getPlayLists().size());
    }
    
    @Test
    void createAccount() {
        String randomUsername = UUID.randomUUID().toString();
        String randomPassword = UUID.randomUUID().toString();
        
        AM.createAccount(randomUsername, randomPassword);
        assertTrue(AM.userExists(randomUsername));
    }

    @Test
    void AMuserExists() {
        AM.createAccount("username", "password");
        assertTrue(AM.userExists("username"));
        assertFalse(AM.userExists("wrongUsername"));
    }
    
    @Test
    void AMgetUserData() {
        AM.createAccount("username", "password");
        LibraryModel expectedLibrary = AM.getUserData("username");
        assertNotNull(expectedLibrary, "Library should not be null");
    }
    
    @Test
    void AMtestValidatePassword() {
        String password = "securePassword";
        AM.createAccount("testUser", password); 
        assertTrue(AM.validatePassword("testUser", "securePassword"));
        assertFalse(AM.validatePassword("testUser", "wrongPassword"));
    }
    
    @Test
    void UAgetUserName() {
        UserAccount user = new UserAccount("username", "test_data");
        assertEquals("username", user.getUsername());
    }
}
