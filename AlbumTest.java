package test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import dataStructures.Album;
import dataStructures.Song;

class AlbumTest {
	
	private Album album = new Album("albumTitle", "artist", "genre", "year");

	@Test
	void testGetSongs() {
		Song song = new Song("songTitle", "artist", "albumTitle", "genre");
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
		Song song = new Song("songTitle", "artist", "albumTitle","genre");
		Song song1 = new Song("song1Title", "artist1", "albumTitle","genre");
		Song song2 = new Song("song2Title", "artist2", "albumTitle","genre");
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
		Song song = new Song("songTitle", "artist", "albumTitle","genre");
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
		Song song = new Song("songTitle", "artist", "albumTitle","genre");
		album.addSong(song);
		
		assertTrue(album.hasSong("songTitle"));
	}
	
	@Test
	void testToString() {
		Song song = new Song("songTitle", "artist", "albumTitle", "genre");
		Song song1 = new Song("song1Title", "artist1", "albumTitle", "genre");
		
		album.addSong(song);
		album.addSong(song1);
		String expected = "albumTitle, artist, genre, year\nsongTitle, artist, albumTitle, genre\nsong1Title, artist1, albumTitle, genre\n";
		assertEquals(album.toString(), expected);
	}
	
	@Test
	void testCopyConstructor() {
		Song song = new Song("songTitle", "artist", "albumTitle" ,"genre");
		Song song1 = new Song("song1Title", "artist1", "albumTitle" ,"genre");
		Song song2 = new Song("song2Title", "artist2", "albumTitle" ,"genre");
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
	
	@Test
    void testEquals_SameAlbums() {
        Song song1 = new Song("Song Title 1", "Artist 1", "Album Title", "Genre");
        Song song2 = new Song("Song Title 2", "Artist 1", "Album Title", "Genre");
        Album album1 = new Album("Album Title", "Artist 1", "Genre", "Year");
        Album album2 = new Album("Album Title", "Artist 1", "Genre", "Year");

        album1.addSong(song1);
        album1.addSong(song2);
        
        album2.addSong(song1);
        album2.addSong(song2);

        assertTrue(album1.equals(album2), "The albums should be equal");
    }

    @Test
    void testEquals_DifferentAlbums() {
        Song song1 = new Song("Song Title 1", "Artist 1", "Album Title", "Genre");
        Song song2 = new Song("Song Title 2", "Artist 1", "Album Title", "Genre");
        Song song3 = new Song("Song Title 3", "Artist 1", "Album Title", "Genre");
        
        Album album1 = new Album("Album Title", "Artist 1", "Genre", "Year");
        Album album2 = new Album("Album Title", "Artist 1", "Genre", "Year");
        
        album1.addSong(song1);
        album1.addSong(song2);

        album2.addSong(song1);
        album2.addSong(song3); // Different song
        
        assertFalse(album1.equals(album2), "The albums should not be equal because they have different songs");
    }

    @Test
    void testEquals_DifferentNumberOfSongs() {
        Song song1 = new Song("Song Title 1", "Artist 1", "Album Title", "Genre");
        Song song2 = new Song("Song Title 2", "Artist 1", "Album Title", "Genre");
        
        Album album1 = new Album("Album Title", "Artist 1", "Genre", "Year");
        Album album2 = new Album("Album Title", "Artist 1", "Genre", "Year");
        
        album1.addSong(song1);
        album1.addSong(song2);

        album2.addSong(song1); // Only one song
        
        assertFalse(album1.equals(album2), "The albums should not be equal because they have a different number of songs");
    }

    @Test
    void testEquals_NullAlbum() {
        Song song1 = new Song("Song Title 1", "Artist 1", "Album Title", "Genre");
        Album album = new Album("Album Title", "Artist 1", "Genre", "Year");
        
        album.addSong(song1);

        assertFalse(album.equals(null), "An album should not be equal to null");
    }

    @Test
    void testEquals_DifferentObjectType() {
        Song song1 = new Song("Song Title 1", "Artist 1", "Album Title", "Genre");
        Album album = new Album("Album Title", "Artist 1", "Genre", "Year");
        
        album.addSong(song1);

        assertFalse(album.equals("Not an album"), "An album should not be equal to a string or different object type");
    }

    @Test
    void testEquals_SameAlbumInstance() {
        Song song1 = new Song("Song Title 1", "Artist 1", "Album Title", "Genre");
        Album album = new Album("Album Title", "Artist 1", "Genre", "Year");
        
        album.addSong(song1);
        
        assertTrue(album.equals(album), "An album should be equal to itself");
    }
    @Test
    void testHashCode_SameAlbums() {
        Song song1 = new Song("Song Title 1", "Artist 1", "Album Title", "Genre");
        Song song2 = new Song("Song Title 2", "Artist 1", "Album Title", "Genre");
        Album album1 = new Album("Album Title", "Artist 1", "Genre", "Year");
        Album album2 = new Album("Album Title", "Artist 1", "Genre", "Year");

        album1.addSong(song1);
        album1.addSong(song2);
        
        album2.addSong(song1);
        album2.addSong(song2);

        assertEquals(album1.hashCode(), album2.hashCode(), "The hash codes should be the same for equal albums");
    }

    @Test
    void testHashCode_DifferentAlbums() {
        Song song1 = new Song("Song Title 1", "Artist 1", "Album Title", "Genre");
        Song song2 = new Song("Song Title 2", "Artist 1", "Album Title", "Genre");
        Song song3 = new Song("Song Title 3", "Artist 1", "Album Title", "Genre");
        
        Album album1 = new Album("Album Title", "Artist 1", "Genre", "Year");
        Album album2 = new Album("Album Title", "Artist 1", "Genre", "Year");
        
        album1.addSong(song1);
        album1.addSong(song2);

        album2.addSong(song1);
        album2.addSong(song3); // Different song
        
        assertNotEquals(album1.hashCode(), album2.hashCode(), "The hash codes should be different for unequal albums");
    }

    @Test
    void testHashCode_Consistent() {
        Song song1 = new Song("Song Title 1", "Artist 1", "Album Title", "Genre");
        Album album = new Album("Album Title", "Artist 1", "Genre", "Year");
        
        album.addSong(song1);

        int hashCode1 = album.hashCode();
        int hashCode2 = album.hashCode();
        
        assertEquals(hashCode1, hashCode2, "The hash code should be consistent for the same album");
    }

    @Test
    void testHashCode_EmptyAlbum() {
        Album album = new Album("Album Title", "Artist 1", "Genre", "Year");
        
        int hashCode = album.hashCode();
        
        assertNotNull(hashCode, "The hash code should not be null even for an empty album");
    }

    @Test
    void testHashCode_SingleSong() {
        Song song1 = new Song("Song Title 1", "Artist 1", "Album Title", "Genre");
        Album album = new Album("Album Title", "Artist 1", "Genre", "Year");
        
        album.addSong(song1);
        
        int hashCode = album.hashCode();
        
        assertNotNull(hashCode, "The hash code should not be null for an album with a single song");
    }
}
