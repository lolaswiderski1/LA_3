package Music;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class SongTest {

	@Test
	void testGetTitle() {
		Song song = new Song("long time", "playboi carti");
		assertEquals("long time", song.getTitle());
	}
	@Test
	void TestGetArtist() {
		Song song = new Song("long time", "playboi carti");
		assertEquals("playboi carti", song.getArtist());
	}
	@Test
	void testToString() {
		Song song = new Song("long time", "playboi carti");
		assertEquals("long time, playboi carti", song.toString());
	}
}
