
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Test;

class SongTest {
	
	// provides 100% coverage for song class, passes tests 100% 
	
	@Test
	void testGetSongTitle() {
		// test get songTitle method
		Song song = new Song("Jonny", "Faye Webster", "album");
		assertEquals(song.getSongTitle(), "Jonny");
	}
	
	@Test
	void testGetSongArtist() {
		// test get songArtist method
		Song song = new Song("Jonny", "Faye Webster", "album");
		assertEquals(song.getArtist(), "Faye Webster");
	}
	
	@Test
	void testGetAlbumTitle() {
		// test get albumTitle method
		Song song = new Song("Jonny", "Faye Webster", "album");
		assertEquals(song.getAlbumTitle(), "album");
	}
	
	@Test
	void testToString() {
		// test toString method
		Song song = new Song("Jonny", "Faye Webster", "album");
		assertEquals(song.toString(), "Jonny, Faye Webster");
	}
}
