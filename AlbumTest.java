package Music;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Test;

class AlbumTest {
	
	// gives 100% coverage of Album.java and passes all cases 100%
	
	@Test
	void testGetTitle() {
		Album album = new Album("Off The Wall", "Micheal Jackson", "Pop", "2000");
		assertEquals(album.getTitle(), "Off The Wall");
	}
	
	@Test
	void testGetGenre() {
		Album album = new Album("Off The Wall", "Micheal Jackson", "Pop", "2000");
		assertEquals(album.getGenre(), "Pop");
	}
	
	@Test
	void testGetArtist() {
		Album album = new Album("Off The Wall", "Micheal Jackson", "Pop", "2000");
		assertEquals(album.getArtist(), "Micheal Jackson");
	}
	
	@Test
	void testGetYear() {
		Album album = new Album("Off The Wall", "Micheal Jackson", "Pop", "2000");
		assertEquals(album.getYear(), "2000");
	}
	
	@Test
	void testAddSong() {
		Album album = new Album("Off The Wall", "Micheal Jackson", "Pop", "2000");
		// ensure initialized album is empty
		assertTrue(album.getAllSongs().size() == 0); 
		Song song = new Song("Rock With You", "Micheal Jackson", "Off The Wall");
		album.addSong(song);
		// after adding a song, the album is no longer empty
		assertFalse(album.getAllSongs().size() == 0);
	}
	@Test
	void testCantAddSong() {
		Album album = new Album("Off The Wall", "Micheal Jackson", "Pop", "2000");
		// ensure initialized album is empty
		assertTrue(album.getAllSongs().size() == 0); 
		Song song = new Song("Dirty Diana", "Micheal Jackson", "Bad");
		album.addSong(song);
		// after trying to add song, the album is still empty since song is of incorrect album
		assertTrue(album.getAllSongs().size() == 0);
	}
	
	@Test
	void testGetSongByTitle() {
		Album album = new Album("Off The Wall", "Micheal Jackson", "Pop", "2000");
		Song song = new Song("Rock With You", "Micheal Jackson", "Off The Wall");
		album.addSong(song);
		assertEquals(song, album.getSongByTitle("Rock With You"));
	}
	
	@Test
	void testGetAllSongs() {
		Song song = new Song("Rock With You", "Micheal Jackson", "Off The Wall");
		Song song1 = new Song("Girlfriend", "Micheal Jackson", "Off The Wall");
		
		// create album of two Micheal jackson songs
		Album album = new Album("Off The Wall", "Micheal Jackson", "Pop", "2000");
		album.addSong(song);
		album.addSong(song1);
		
		// create list of songs object with same songs
		List<Song> songsList = new ArrayList<Song>();
		songsList.add(song);
		songsList.add(song1);
		
		// assert lists are equal
		assertEquals(songsList, album.getAllSongs());
	}
	
	@Test 
	void testHasSong() {
		Album album = new Album("Off The Wall", "Micheal Jackson", "Pop", "2000");
		Song song = new Song("Rock With You", "Micheal Jackson", "Off The Wall");
		album.addSong(song);
		assertTrue(album.hasSong("Rock With You"));
	}
	
	@Test
	void testToString() {
		Album album = new Album("Off The Wall", "Micheal Jackson", "Pop", "2000");
		Song song = new Song("Rock With You", "Micheal Jackson", "Off The Wall");
		album.addSong(song);
		String result = "Off The Wall" + ", " + "Micheal Jackson" + ", " + "Pop" + 
		", " + "2000" + "\n" + song + "\n";
		assertEquals(result, album.toString());
	}
	
	@Test
    void testCopyConstructor() {
		Song song = new Song("Rock With You", "Micheal Jackson", "Off The Wall");
		Song song1 = new Song("Girlfriend", "Micheal Jackson", "Off The Wall");
		
		// create album
		Album album = new Album("Off The Wall", "Micheal Jackson", "Pop", "2000");
     
        // create a copy using copy constructor
        Album copy = new Album(album);

        // verify that fields are copied 
        assertEquals(album.getTitle(), copy.getTitle());
        assertEquals(album.getArtist(), copy.getArtist());
        assertEquals(album.getGenre(), copy.getGenre());
        assertEquals(album.getYear(), copy.getYear());

   
    }
}

