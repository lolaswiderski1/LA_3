package Music;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Test;

class AlbumTest {
	
	@Test
	void getTitle() {
		Album album = new Album("album", "artist", "genre", "year");
		assertEquals(album.getTitle(), "album");
	}
	
	@Test
	void getGenre() {
		Album album = new Album("album", "artist", "genre", "year");
		assertEquals(album.getGenre(), "genre");
	}
	
	@Test
	void getArtist() {
		Album album = new Album("album", "artist", "genre", "year");
		assertEquals(album.getArtist(), "artist");
	}
	
	@Test
	void getYear() {
		Album album = new Album("album", "artist", "genre", "year");
		assertEquals(album.getYear(), "year");
	}
	
	@Test
	void encapsulation() {
		Album album = new Album("album", "artist", "genre", "year");
		List<Song> songs = album.getAllSongs();
		songs.add(new Song("title", "artist"));
		assertNotEquals(songs.size(), album.getAllSongs().size());
	}
	
	@Test
	void getSongByTitle() {
		Album album = new Album("album", "artist", "genre", "year");
		Song song = new Song("title", "artist");
		album.addSong(song);
		assertEquals(song, album.getSongByTitle("title"));
		
	}
	
	@Test
	void getAllSongs() {
		Album album = new Album("album", "artist", "genre", "year");
		Map<String, Song> songs = new LinkedHashMap<>();
		
		Song song = new Song("title", "artist");
		Song song1 = new Song("title1", "artist1");
		Song song2 = new Song("title2", "artist2");
		
		songs.put(song.getTitle(), song);
		songs.put(song1.getTitle(), song1);
		songs.put(song2.getTitle(), song2);
		
		album.addSong(song);
		album.addSong(song1);
		album.addSong(song2);
		
		assertEquals(album.getAllSongs(), new ArrayList<>(songs.values()));
		
	}
	
	@Test
	void addSong() {
		Album album = new Album("album", "artist", "genre", "year");
		assertEquals(album.getAllSongs().size(),0);
		Song song = new Song("title", "artist");
		album.addSong(song);
		assertEquals(album.getAllSongs().size(), 1);
		assertNotEquals(album.getAllSongs().size(), 0);
		assertEquals(album.getSongByTitle("title"), song);
	}
	
	@Test
	void hasSong() {
		Album album = new Album("album", "artist", "genre", "year");
		Song song = new Song("title1", "artist1");
		album.addSong(new Song( "title", "artist"));
		assertTrue(album.hasSong("title"));
		assertFalse(album.hasSong("title1"));
	}
	
	@Test
	void testString() {
		Album album = new Album("album", "artist", "genre", "year");
		album.addSong(new Song("title", "artist"));
		String expected = "album, artist, genre, year\n"
				+ "title, artist\n";
		assertEquals(expected, album.toString());
	}
	
}
