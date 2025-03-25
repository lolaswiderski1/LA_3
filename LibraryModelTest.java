package test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import dataStructures.Album;
import dataStructures.PlayList;
import dataStructures.Song;
import model.LibraryModel;
import model.LibraryModel.Rating;

class LibraryModelTest {
	LibraryModel lib = new LibraryModel();
	Song song1 = new Song("songTitle1", "artist1", "albumTitle1", "genre");
	Song song2 = new Song("songTitle2", "artist1", "albumTitle1", "genre");
	Album album1 = new Album("albumTitle1", "artist1", "genre1", "year1");
	
	Song song3 = new Song("songTitle1", "artist2", "albumTitle2", "genre");
	Song song4 = new Song("songTitle3", "artist2", "albumTitle2", "genre");
	Album album2 = new Album("albumTitle2", "artist2", "genre2", "year2");
	Song song5 = new Song("songTitle5", "artist5", "albumTitle5", "genre5");
	
	PlayList playList1 = new PlayList("pl1");
	PlayList playList2 = new PlayList("pl2");
	
	PlayList originalFavorites = new PlayList("Favorites");
	PlayList originalRecents = new PlayList("Recents");
    PlayList originalFrequents = new PlayList("Frequents");
	
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
		assertEquals(playLists.get(0).getName(), "Most recent songs" );
		assertEquals(playLists.get(1).getName(), "Most played songs");
		
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
	@Test
	void testGetPlaylist() {
		playList1.addSong(song1);
		playList1.addSong(song2);
		playList1.addSong(song3);
		List<Song> plSongs = new ArrayList<Song>();
		for (Song song : playList1.getSongs()) {
			plSongs.add(song);
		}
		lib.addPlaylist(playList1);
		lib.addPlaylist(playList2);
		assertEquals(plSongs, lib.getPlayList(playList1));
	}
	@Test
	void testShuffleSongs() {
		Map<Song, Boolean> songs = new HashMap<>();
        songs.put(song1, true);
        songs.put(song2, true);
        songs.put(song3, true);
        lib.addSong(song1);
        lib.addSong(song2);
        lib.addSong(song3);
        List<Song> shuffledSongs = lib.shuffleSongs();

        assertEquals(3, shuffledSongs.size());
        assertTrue(shuffledSongs.containsAll(songs.keySet()));
    	}
	
	@Test
	void testShufflePlayLists() {
		Map<PlayList, Boolean> playlists = new HashMap<>();
		playlists.put(playList1, true);
		playlists.put(playList2, true);
        lib.addPlaylist(playList1);
        lib.addPlaylist(playList2);
        List<PlayList> shuffledpls = lib.shufflePlaylists();

        assertEquals(5, shuffledpls.size());
        assertTrue(shuffledpls.containsAll(playlists.keySet()));
    	}
	
	@Test
	void testRemoveSong() {
        PlayList playList1 = new PlayList("Playlist 1");
        lib.addSong(song1);
        playList1.addSong(song1);
        lib.addPlaylist(playList1);
        assertTrue(playList1.hasSong(song1));
        lib.removeSong(song1);
        assertFalse(lib.hasSong(song1));
        for (PlayList pl : lib.getPlayLists()) {
            assertFalse(pl.hasSong(song1));
        }
    }
	@Test
	void testSortByTitle() {
        // Add songs to the library
        lib.addSong(song1);
        lib.addSong(song2);
        lib.addSong(song3);
        lib.addSong(song4);

        // Get sorted list of songs
        List<Song> sortedSongs = lib.sortByTitle();

        // Verify that songs are sorted by title
        assertEquals(Arrays.asList(song1, song3, song2, song4), sortedSongs);
	    }
	 
	@Test
	void testSortByArtist() {
        // Add songs to the library
        lib.addSong(song1);
        lib.addSong(song2);
        lib.addSong(song3);
        lib.addSong(song4);

        // Get sorted list of songs
        List<Song> sortedSongs = lib.sortByArtist();

        // Verify that songs are sorted by artist name
        assertEquals(Arrays.asList(song1, song2, song3, song4), sortedSongs);
    }
	@Test
	void testSortByRating() {
	    lib.addSong(song1);
	    lib.addSong(song2);
	    lib.addSong(song3);
	    lib.addSong(song4);
	    lib.addSong(song5);
	    
	    lib.rateSong(song1, Rating.FIVE);
	    lib.rateSong(song2, Rating.THREE);
	    lib.rateSong(song3, Rating.FOUR);
	    lib.rateSong(song4, Rating.ONE);
	    lib.rateSong(song5, Rating.TWO);
	    List<Song> sortedSongs = lib.sortByRating();
	    assertEquals(Arrays.asList(song4, song5, song2, song3, song1), sortedSongs);
}
	@Test
    void testPlaySong() {
        lib.playSong(song1);
        lib.playSong(song2);
        lib.playSong(song3);
        lib.playSong(song4);
        lib.playSong(song5);

        // Verify that the recents list has the 5 most recent songs (it should have exactly 5 songs)
        List<Song> recentsList = lib.getRecents().getSongs(); // Assume getRecents() is available to get the recents list
        assertEquals(5, recentsList.size());
        assertEquals(song5, recentsList.get(0)); // The most recent song played should be the first
        assertEquals(song1, recentsList.get(4));  // The oldest song in recents should be the last

        // Play song1 again (to ensure it's added at the front)
        lib.playSong(song1);

        // Verify that recents still only holds the 5 most recent songs, with song1 at the front
        recentsList = lib.getRecents().getSongs();
        assertEquals(5, recentsList.size());
        assertEquals(song1, recentsList.get(0)); // song1 should now be the most recent
        assertEquals(song2, recentsList.get(4));  // song2 should be the oldest in recents
    }
	
	@Test
	void testHasPlayList() {
		lib.addPlaylist(playList1);
		assertTrue(lib.hasPlaylist("pl1"));
		assertFalse(lib.hasPlaylist("pl2"));
	}
	
	@Test
    void testSetFavoritesPlayList() {
        originalFavorites.addSong(song1);
        originalFavorites.addSong(song2);
        lib.setFavoritesPlayList(originalFavorites);
        PlayList copiedFavorites = lib.getFavoritesPlayList(); 
        assertNotSame(originalFavorites, copiedFavorites);
        assertEquals(originalFavorites.getName(), copiedFavorites.getName());
        assertTrue(copiedFavorites.hasSong(song1));
        assertTrue(copiedFavorites.hasSong(song2));
    }
	
	@Test
    void testSetRecents() {
        originalRecents.addSong(song1);
        originalRecents.addSong(song2);
        lib.setRecents(originalRecents);
        PlayList copiedRecents = lib.getRecents(); 
        assertNotSame(originalRecents, copiedRecents);
        assertEquals(originalRecents.getName(), copiedRecents.getName());
        assertTrue(copiedRecents.hasSong(song1));
        assertTrue(copiedRecents.hasSong(song2));
    }

    @Test
    void testSetFrequents() {
        originalFrequents.addSong(song1);
        originalFrequents.addSong(song2);
        lib.setFrequents(originalFrequents);
        PlayList copiedFrequents = lib.getFrequents(); 
        assertNotSame(originalFrequents, copiedFrequents);
        assertEquals(originalFrequents.getName(), copiedFrequents.getName());
        assertTrue(copiedFrequents.hasSong(song1));
        assertTrue(copiedFrequents.hasSong(song2));
    }
    @Test
    void testGetPlayedSongs() {
        lib.playSong(song1);
        lib.playSong(song2);
        List<Song> playedSongs = lib.getPlayedSongs();
        assertEquals(2, playedSongs.size());
        assertTrue(playedSongs.contains(song1));
        assertTrue(playedSongs.contains(song2));
        playedSongs.add(song3);
        assertFalse(lib.getPlayedSongs().contains(song3));
    }

    @Test
    void testGetGenres() {
        lib.addSong(song1);
        lib.addSong(song2);
        lib.addSong(song3);
        Map<String, List<Song>> genresMap = lib.getGenres();
        assertTrue(genresMap.containsKey("genre"));
        assertTrue(genresMap.containsKey("genre"));
        assertEquals(3, genresMap.get("genre").size());
        assertFalse(lib.getGenres().get("genre").contains(song5));
    }
    @Test
    void testHasAlbumByTitle() {
        assertFalse(lib.hasAlbumByTitle("albumTitle1"));
        assertFalse(lib.hasAlbumByTitle("albumTitle2"));
        lib.addAlbum(album1);
        lib.addAlbum(album2);
        assertTrue(lib.hasAlbumByTitle("albumTitle1"));
        assertTrue(lib.hasAlbumByTitle("albumTitle2"));
        assertTrue(lib.hasAlbumByTitle("ALBUMTITLE1")); 
        assertFalse(lib.hasAlbumByTitle("NonExistentAlbum")); 
    }
    @Test
    void testGetSongsMap() {
        lib.addSong(song1);
        lib.addSong(song2);
        lib.rateSong(song1, Rating.FIVE);
        lib.rateSong(song2, Rating.THREE);
        Map<Song, Rating> songsMap = lib.getSongsMap();
        assertEquals(Rating.FIVE, songsMap.get(song1));
        assertEquals(Rating.THREE, songsMap.get(song2));
        assertTrue(songsMap.containsKey(song1));
        assertTrue(songsMap.containsKey(song2));
        songsMap.put(song1, Rating.ONE);
        assertEquals(Rating.ONE, lib.getSongsMap().get(song1)); 
    }
    @Test
    void testHasAlbumByArtist() {
        assertFalse(lib.hasAlbumByArtist("artist1"));
        assertFalse(lib.hasAlbumByArtist("artist2"));
        lib.addAlbum(album1);
        lib.addAlbum(album2);
        assertTrue(lib.hasAlbumByArtist("artist1"));
        assertTrue(lib.hasAlbumByArtist("artist2"));
        assertTrue(lib.hasAlbumByArtist("ARTIST1")); 
        assertFalse(lib.hasAlbumByArtist("NonExistentArtist")); 
    }
    @Test
    void testAddSongsToAlbum() {
        lib.addAlbum(album1);
        assertFalse(album1.hasSong(song1.getSongTitle()));
        assertFalse(album1.hasSong(song2.getSongTitle()));
        List<Song> songsToAdd = Arrays.asList(song1, song2);
        lib.addSongsToAlbum("albumTitle1", songsToAdd);
        List<Album> albums = lib.getAlbumsByTitle("albumTitle1");
        assertFalse(albums.isEmpty()); 
        Album updatedAlbum = albums.get(0);
        assertTrue(updatedAlbum.hasSong(song1.getSongTitle())); 
        assertTrue(updatedAlbum.hasSong(song2.getSongTitle())); 
        assertTrue(lib.hasSong(song1));
        assertTrue(lib.hasSong(song2));
    }
    @Test
    void testAddSong() {
        assertFalse(lib.hasSong(song1));
        lib.addSong(song1);
        assertTrue(lib.hasSong(song1));
        Map<String, List<Song>> genres = lib.getGenres();
        assertTrue(genres.containsKey("genre"));
        assertTrue(genres.get("genre").contains(song1));
        assertEquals(Rating.UNRATED, lib.getSongsMap().get(song1));
    }

    @Test
    void testGenrePlaylistCreation() {
        for (int i = 1; i <= 10; i++) {
            lib.addSong(new Song("songTitle" + i, "artist" + i, "albumTitle" + i, "genre"));
        }
        assertTrue(lib.hasPlaylist("GENRE"));
    }
    
    @Test
    void testRemoveAlbum() {
    	lib.addAlbum(album1);
    	assertTrue(lib.hasAlbum(album1));
    	lib.removeAlbum(album1);
    	assertFalse(lib.hasAlbum(album1));
    }
    
}

	

