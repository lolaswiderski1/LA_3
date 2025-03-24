package test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import dataStructures.Album;
import dataStructures.PlayList;
import dataStructures.Song;
import model.LibraryModel;

class LibraryModelTest {
	LibraryModel lib = new LibraryModel();
	Song song1 = new Song("songTitle1", "artist1", "albumTitle1", "genre");
	Song song2 = new Song("songTitle2", "artist1", "albumTitle1", "genre");
	Album album1 = new Album("albumTitle1", "artist1", "genre1", "year1");
	
	Song song3 = new Song("songTitle1", "artist2", "albumTitle2", "genre");
	Song song4 = new Song("songTitle3", "artist2", "albumTitle2", "genre");
	Album album2 = new Album("albumTitle2", "artist2", "genre2", "year2");
	
	PlayList playList1 = new PlayList("pl1");
	PlayList playList2 = new PlayList("pl2");
	
	void initialize() {
		album1.addSong(song1);
		album1.addSong(song2);
		album2.addSong(song3);
		album2.addSong(song4);
	}

	@Test
	void testGetAllSongs() {
		List<Song> expected = new ArrayList<>();
		expected.add(song1);
		expected.add(song2);
		
		lib.addSong(song1);
		lib.addSong(song2);
		
		assertEquals(lib.getAllSongs(), expected);
	}

	@Test
	void testHasSong() {
		lib.addSong(song1);
		assertTrue(lib.hasSong(song1));
		assertFalse(lib.hasSong(song2));
	}

	@Test
	void testHasAlbum() {
		lib.addAlbum(album1);
		assertTrue(lib.hasAlbum(album1));
		assertFalse(lib.hasAlbum(album2));
	}

	@Test
	void testRateSong() {
		lib.addSong(song1);
		lib.rateSong(song1, LibraryModel.Rating.THREE);
		lib.addSong(song2);
		lib.rateSong(song2, LibraryModel.Rating.FIVE);
		
	}

	@Test
	void testGetFavorites() {
		List<Song> expected = new ArrayList<>();
		expected.add(song1);
		expected.add(song2);
		
		lib.addSong(song1);
		lib.addFavorite(song1);
		lib.addSong(song2);
		lib.addFavorite(song2);
		
		assertEquals(lib.getFavorites(), expected);
	}

	@Test
	void testGetSongTitles() {
		List<String> expected = new ArrayList<>();
		expected.add("songTitle1");
		expected.add("songTitle2");
		
		lib.addSong(song1);
		lib.addSong(song2);
		
		assertEquals(lib.getSongTitles(), expected);
	}
	
	@Test
	void testGetAlbumTitles() {
		List<String> expected = new ArrayList<>();
		expected.add("albumTitle1");
		expected.add("albumTitle2");
		
		lib.addAlbum(album1);
		lib.addAlbum(album2);
		
		assertEquals(lib.getAlbumTitles(), expected);
	}
	
	@Test
	void testGetAlbumArtists() {
		List<String> expected = new ArrayList();
		expected.add("artist1");
		expected.add("artist2");
		
		lib.addAlbum(album1);
		lib.addAlbum(album2);
		
		assertEquals(lib.getAlbumArtists(), expected);
	}

	@Test
	void testGetSongArtists() {
		Set<String> expected = new HashSet<>();
		expected.add("artist1");
		expected.add("artist2");
		
		lib.addSong(song1);
		lib.addSong(song4);
		
		assertEquals(lib.getSongArtists(), expected);
	}

	@Test
	void testGetAlbums() {	
		List<Album> expected = new ArrayList<>();
		expected.add(album1);
		expected.add(album2);
		
		lib.addAlbum(album1);
		lib.addAlbum(album2);

		List<Album> albums = lib.getAlbums();
		
		for (int i = 0; i < albums.size(); i++) {
			assertEquals(albums.get(i).getTitle(), expected.get(i).getTitle());
		}
		
	}

	@Test
	void testGetPlayLists() {
		List<PlayList> expected = new ArrayList<>();
		expected.add(playList1);
		expected.add(playList2);
		
		lib.addPlaylist(playList1);
		lib.addPlaylist(playList2);
		
		List<PlayList> playLists = lib.getPlayLists();
		
		for (int i = 0; i < playLists.size(); i++) {
			assertEquals(playLists.get(i).getName(), expected.get(i).getName());
		}
	}
	
	@Test
	void getHasSongByTitle() {
		lib.addSong(song1);
		
		assertTrue(lib.hasSongByTitle("songTitle1"));
		assertFalse(lib.hasSongByTitle("songTitle2"));
	}
	
	@Test
	void getHasSongByArtist() {
		lib.addSong(song1);
		
		assertTrue(lib.hasSongByArtist("artist1"));
		assertFalse(lib.hasSongByArtist("artist2"));
	}

	@Test
	void testGetSongsByTitle() {
		List<Song> expected = new ArrayList<>();
		expected.add(song1);
		expected.add(song3);
		
		lib.addSong(song1);
		lib.addSong(song2);
		lib.addSong(song3);
		
		assertEquals(lib.getSongsByTitle("songTitle1"), expected);
		
	}

	@Test
	void testGetSongsByArtist() {
		List<Song> expected = new ArrayList<>();
		expected.add(song1);
		expected.add(song2);
		
		lib.addSong(song1);
		lib.addSong(song2);
		lib.addSong(song3);
		
		assertEquals(lib.getSongsByArtist("artist1"), expected);
	}

	@Test
	void testGetAlbumsByTitle() {
		List<Album> expected = new ArrayList<>();
		expected.add(album1);
		
		lib.addAlbum(album1);
		lib.addAlbum(album2);
		
		assertEquals(lib.getAlbumsByTitle("albumTitle1"), expected);
	}

	@Test
	void testGetAlbumsByArtist() {
		List<Album> expected = new ArrayList<>();
		expected.add(album1);
		
		lib.addAlbum(album1);
		lib.addAlbum(album2);
		
		assertEquals(lib.getAlbumsByArtist("artist1"), expected);
	}

}
