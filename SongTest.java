package Model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class SongTest {

	@Test
	void testGetSongTitle() {
		Song song = new Song("long time", "playboi carti", "die lit");
		assertEquals("long time", song.getSongTitle());
	}
	
	@Test
	void testGetArtist() {
		Song song = new Song("long time", "playboi carti", "die lit");
		assertEquals("playboi carti", song.getArtist());
	}
	
	@Test
	void testGetAlbumTitle() {
		Song song = new Song("long time", "playboi carti", "die lit");
		assertEquals("die lit", song.getAlbumTitle());
	}
	
	@Test
	void testToString() {
		Song song = new Song("long time", "playboi carti", "die lit");
		assertEquals("long time, playboi carti", song.toString());
	}
}
