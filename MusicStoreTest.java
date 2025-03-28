package tests;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;

import dataStructures.Album;
import dataStructures.Song;
import model.MusicStore;

class MusicStoreTest {
	
	MusicStore store = new MusicStore("test_albums");
	
	@Test
	void testWrongDirectory() {
		MusicStore newStore = new MusicStore("fake_directory");
	}

	@Test
	void testGetSongsByTitle() {
		List<Song> songsByTitle = store.getSongsByTitle("song1");
		for (Song song : songsByTitle) {
			assertEquals(song.getSongTitle(), "song1");
		}
	}

	@Test
	void testGetSongsByArtist() {
		List<Song> songsByArtist = store.getSongsByArtist("artist1");
		for (Song song : songsByArtist) {
			assertEquals(song.getArtist(), "artist1");
		}
	}

	@Test
	void testGetAlbumsByTitle() {
		List<Album> albumsByTitle = store.getAlbumsByTitle("title1");
		for (Album album : albumsByTitle) {
			assertEquals(album.getTitle(), "title1");
		}
	}

	@Test
	void testGetAlbumsByArtist() {
		List<Album> albumsByArtist = store.getAlbumsByArtist("artist1");
		for (Album album : albumsByArtist) {
			assertEquals(album.getArtist(), "artist1");
		}
	}

	@Test
	void testGetAlbums() {
		List<Album> albums = store.getAlbums();
		assertEquals(15, albums.size());
		
		for (Album album : albums) {
	        assertNotNull(album.getTitle());
	        assertNotNull(album.getArtist());
	        assertNotNull(album.getGenre());
	        assertNotNull(album.getYear());
	    }
	}

}
