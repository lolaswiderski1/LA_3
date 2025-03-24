package test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import dataStructures.PlayList;
import dataStructures.Song;

class PlayListTest {
	
	// provides 100% coverage of playlist class, and passes every case.
	
	PlayList playList = new PlayList("Spring 2025");
	Song song = new Song("Rock With You", "Micheal Jackson", "Off The Wall", "Pop");
	
	@Test
	void testCopyConstructor() {
		PlayList copy = new PlayList(playList);
		assertEquals(copy.getName(), playList.getName());
	}
	
	@Test
	void testGetName() {
		assertEquals(playList.getName(), "Spring 2025");
	}
	
	@Test
	void testAddSong() {
		// ensure playlist is empty before adding song
		assertTrue(playList.getSongs().size() == 0);
		playList.addSong(song);
		// now the playlist should no longer be empty
		assertFalse(playList.getSongs().size() == 0);
	}
	
	@Test
	void testRemoveSong() {
		playList.addSong(song);
		// playlist should not be empty
		assertFalse(playList.getSongs().size() == 0);
		playList.removeSong(song);
		// playlist should now be empty
		assertTrue(playList.getSongs().size() == 0);
	}
	
	@Test 
	void testGetSong() {
		Song song1 = new Song("Girlfriend", "Micheal Jackson", "Off The Wall", "Pop");
		
		PlayList playList = new PlayList("Spring 2025");
		List<Song> songsList = new ArrayList<Song>();
		
		playList.addSong(song);
		playList.addSong(song1);
		
		songsList.add(song);
		songsList.add(song1);
		
		assertEquals(playList.getSongs(), songsList);
	}
	
	@Test
	void testHasSong() {
		assertFalse(playList.hasSong(song));
		playList.addSong(song);
		assertTrue(playList.hasSong(song));
	}
	
	 @Test
	 void testClear() {
		PlayList playlist = new PlayList("Spring 2025");
        // Add some songs
        playlist.addSong(new Song("Title1", "Artist1", "Album1", "Genre1"));
        playlist.addSong(new Song("Title2", "Artist2", "Album2", "Genre2"));
        
        // Ensure playlist is not empty before clearing
        assertFalse(playlist.isEmpty(), "Playlist should not be empty before clearing.");

        // Clear the playlist
        playlist.clear();

        // Ensure the playlist is empty after clearing
        assertTrue(playlist.isEmpty(), "Playlist should be empty after clearing.");
    }
	@Test
	void testIsEmpty() {
		PlayList pl = new PlayList("pl");
		assertTrue(pl.isEmpty());
		pl.addSong(song);
		assertFalse(pl.isEmpty());
	}
}
	
