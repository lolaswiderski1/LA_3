package Model;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

class AlbumTest {
	
	private Album album = new Album("albumTitle", "artist", "genre", "year");

	@Test
	void testGetSongs() {
		Song song = new Song("songTitle", "artist", "albumTitle");
		album.addSong(song);
		List<Song> songs = album.getAllSongs();
		
		List<Song> expected = new ArrayList<Song>();
		expected.add(song);
		assertEquals(songs,expected);
		
	}
	
	@Test
	void testGetTitle() {
		assertEquals(album.getTitle(), "albumTitle");
	}
	
	@Test
	void testGetGenre() {
		assertEquals(album.getGenre(), "genre");
	}
	
	@Test
	void testGetArtist() {
		assertEquals(album.getArtist(), "artist");
	}
	
	@Test
	void testGetYear() {
		assertEquals(album.getYear(), "year");
	}
	
	@Test
	void testAddSong() {
		Song song = new Song("songTitle", "artist", "albumTitle");
		Song song1 = new Song("song1Title", "artist1", "albumTitle");
		Song song2 = new Song("song2Title", "artist2", "albumTitle");
		album.addSong(song);
		album.addSong(song1);
		album.addSong(song2);
		
		ArrayList<Song> expected = new ArrayList<Song>();
		expected.add(song);
		expected.add(song1);
		expected.add(song2);
		assertEquals(album.getAllSongs(), expected);
	}
	
	@Test
	void testGetSongByTitle() {
		Song song = new Song("songTitle", "artist", "albumTitle");
		album.addSong(song);
		
		Song testSong = album.getSongByTitle("songTitle");
		assertEquals(testSong, song);
		
	}
	
	@Test
	void testGetSongByTitleNull() {
		Song testSong = album.getSongByTitle("songTitle");
		assertEquals(testSong, null);
	}
	
	@Test
	void testHasSong() {
		Song song = new Song("songTitle", "artist", "albumTitle");
		album.addSong(song);
		
		assertTrue(album.hasSong("songTitle"));
	}
	
	@Test
	void testToString() {
		Song song = new Song("songTitle", "artist", "albumTitle");
		Song song1 = new Song("song1Title", "artist1", "albumTitle");
		
		album.addSong(song);
		album.addSong(song1);
		
		String expected = "albumTitle, artist, genre, year\nsongTitle, artist\nsong1Title, artist1\n";
		assertEquals(album.toString(), expected);
	}
	
	@Test
	void testCopyConstructor() {
		Song song = new Song("songTitle", "artist", "albumTitle");
		Song song1 = new Song("song1Title", "artist1", "albumTitle");
		Song song2 = new Song("song2Title", "artist2", "albumTitle");
		album.addSong(song);
		album.addSong(song1);
		album.addSong(song2);
		
		ArrayList<Song> expected = new ArrayList<Song>();
		expected.add(song);
		expected.add(song1);
		expected.add(song2);
		
		Album album1 = new Album(album);
		
		assertEquals(album.getTitle(), album1.getTitle());
        assertEquals(album.getArtist(), album1.getArtist());
        assertEquals(album.getGenre(), album1.getGenre());
        assertEquals(album.getYear(), album1.getYear());
	}
}
