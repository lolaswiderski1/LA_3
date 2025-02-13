package Music;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

class AlbumTest {

	@Test
	void testGetSongs() {
		Song song = new Song("songTitle", "artist");
		Album album = new Album("albumTitle", "artist", "genre", "year");
		album.addSong(song);
		ArrayList<Song> songs = album.getSongs();
		
		ArrayList<Song> expected = new ArrayList<Song>();
		expected.add(song);
		assertEquals(songs,expected);
		
	}
	
	@Test
	void testGetTitle() {
		Album album = new Album("albumTitle", "artist", "genre", "year");
		assertEquals(album.getTitle(), "albumTitle");
	}
	
	@Test
	void testGetGenre() {
		Album album = new Album("albumTitle", "artist", "genre", "year");
		assertEquals(album.getGenre(), "genre");
	}
	
	@Test
	void testGetArtist() {
		Album album = new Album("albumTitle", "artist", "genre", "year");
		assertEquals(album.getArtist(), "artist");
	}
	
	@Test
	void testGetYear() {
		Album album = new Album("die lit", "playboi carti", "genre", "year");
		assertEquals(album.getYear(), "year");
	}
	
	@Test
	void testAddSong() {
		Album album = new Album("albumTitle", "artist", "genre", "year");
		Song song = new Song("songTitle", "artist");
		Song song1 = new Song("song1Title", "artist1");
		Song song2 = new Song("song2Title", "artist2");
		album.addSong(song);
		album.addSong(song1);
		album.addSong(song2);
		
		ArrayList<Song> expected = new ArrayList<Song>();
		expected.add(song);
		expected.add(song1);
		expected.add(song2);
		assertEquals(album.getSongs(), expected);
	}
	
	@Test
	void testToString() {
		String expected = "title, artist\n";
		Song song = new Song("title", "artist");
		Album album = new Album("dieLit", "playboiCarti", "genre", "year");
		album.addSong(song);
		System.out.println(album);
		assertEquals(expected, album.toString());
	}
	
}
