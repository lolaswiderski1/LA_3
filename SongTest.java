import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Test;

class SongTest {
	
	// provides 100% coverage for song class, passes tests 100% 
	
	@Test
	// test get songTitle method
	void testGetSongTitle() {
		Song song = new Song("Jonny", "Faye Webster", "album");
		assertEquals(song.getSongTitle(), "Jonny");
	}
	
	@Test
	// test get songArtist method
	void testGetSongArtist() {
		Song song = new Song("Jonny", "Faye Webster", "album");
		assertEquals(song.getArtist(), "Faye Webster");
	}
	
	@Test
	// test get albumTitle method
	void testGetAlbumTitle() {
		Song song = new Song("Jonny", "Faye Webster", "album");
		assertEquals(song.getAlbumTitle(), "album");
	}
	
	@Test
	// test toString method
	void testToString() {
		Song song = new Song("Jonny", "Faye Webster", "album");
		assertEquals(song.toString(), "Jonny, Faye Webster");
	}
}
