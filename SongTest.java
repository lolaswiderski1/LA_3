package test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Test;

import dataStructures.Song;

class SongTest {
	
	// provides 100% coverage for song class, passes tests 100% 
	
	@Test
	// test get songTitle method
	void testGetSongTitle() {
		Song song = new Song("Jonny", "Faye Webster", "album", "genre");
		assertEquals(song.getSongTitle(), "Jonny");
	}
	
	@Test
	// test get songArtist method
	void testGetSongArtist() {
		Song song = new Song("Jonny", "Faye Webster", "album", "genre");
		assertEquals(song.getArtist(), "Faye Webster");
	}
	
	@Test
	// test get albumTitle method
	void testGetAlbumTitle() {
		Song song = new Song("Jonny", "Faye Webster", "album", "genre");
		assertEquals(song.getAlbumTitle(), "album");
	}
	
	@Test
	// test toString method
	void testToString() {
		Song song = new Song("Jonny", "Faye Webster", "album", "genre");
		assertEquals(song.toString(), "Jonny, Faye Webster, album, genre");
	}
	
	@Test
	void testGetGenre() {
		Song song = new Song("Jonny", "Faye Webster", "album", "genre");
		assertEquals(song.getGenre(), "genre");
	}
	@Test
    void testEquals_SameObject() {
        Song song = new Song("Title", "Artist", "Album", "Genre");
        assertTrue(song.equals(song)); // Should be true for the same object
    }

    @Test
    void testEquals_NullObject() {
        Song song = new Song("Title", "Artist", "Album", "Genre");
        assertFalse(song.equals(null)); // Should be false when comparing to null
    }

    @Test
    void testEquals_DifferentClass() {
        Song song = new Song("Title", "Artist", "Album", "Genre");
        String notASong = "This is not a Song";
        assertFalse(song.equals(notASong)); // Should be false when comparing to a different class
    }

    @Test
    void testEquals_DifferentValues() {
        Song song1 = new Song("Title1", "Artist1", "Album1", "Genre1");
        Song song2 = new Song("Title2", "Artist2", "Album2", "Genre2");
        assertFalse(song1.equals(song2)); // Should be false when all values are different
    }

    @Test
    void testEquals_SameValues() {
        Song song1 = new Song("Title", "Artist", "Album", "Genre");
        Song song2 = new Song("Title", "Artist", "Album", "Genre");
        assertTrue(song1.equals(song2)); // Should be true when all values match
    }

    @Test
    void testEquals_DifferentTitle() {
        Song song1 = new Song("Title1", "Artist", "Album", "Genre");
        Song song2 = new Song("Title2", "Artist", "Album", "Genre");
        assertFalse(song1.equals(song2)); // Should be false when the title is different
    }

    @Test
    void testEquals_DifferentArtist() {
        Song song1 = new Song("Title", "Artist1", "Album", "Genre");
        Song song2 = new Song("Title", "Artist2", "Album", "Genre");
        assertFalse(song1.equals(song2)); // Should be false when the artist is different
    }

    @Test
    void testEquals_DifferentAlbum() {
        Song song1 = new Song("Title", "Artist", "Album1", "Genre");
        Song song2 = new Song("Title", "Artist", "Album2", "Genre");
        assertFalse(song1.equals(song2)); // Should be false when the album is different
    }

    @Test
    void testEquals_DifferentGenre() {
        Song song1 = new Song("Title", "Artist", "Album", "Genre1");
        Song song2 = new Song("Title", "Artist", "Album", "Genre2");
        assertFalse(song1.equals(song2)); // Should be false when the genre is different
    }
    
    @Test
    void testHashCode_SameObject() {
        Song song = new Song("Title", "Artist", "Album", "Genre");
        assertEquals(song.hashCode(), song.hashCode()); // Hash code should be consistent
    }

    @Test
    void testHashCode_EqualObjects() {
        Song song1 = new Song("Title", "Artist", "Album", "Genre");
        Song song2 = new Song("Title", "Artist", "Album", "Genre");
        assertEquals(song1.hashCode(), song2.hashCode()); // Equal objects should have the same hash code
    }

    @Test
    void testHashCode_DifferentTitle() {
        Song song1 = new Song("Title1", "Artist", "Album", "Genre");
        Song song2 = new Song("Title2", "Artist", "Album", "Genre");
        assertNotEquals(song1.hashCode(), song2.hashCode()); // Different titles should result in different hash codes
    }

    @Test
    void testHashCode_DifferentArtist() {
        Song song1 = new Song("Title", "Artist1", "Album", "Genre");
        Song song2 = new Song("Title", "Artist2", "Album", "Genre");
        assertNotEquals(song1.hashCode(), song2.hashCode()); // Different artists should result in different hash codes
    }

    @Test
    void testHashCode_DifferentAlbum() {
        Song song1 = new Song("Title", "Artist", "Album1", "Genre");
        Song song2 = new Song("Title", "Artist", "Album2", "Genre");
        assertNotEquals(song1.hashCode(), song2.hashCode()); // Different albums should result in different hash codes
    }

    @Test
    void testHashCode_DifferentGenre() {
        Song song1 = new Song("Title", "Artist", "Album", "Genre1");
        Song song2 = new Song("Title", "Artist", "Album", "Genre2");
        assertNotEquals(song1.hashCode(), song2.hashCode()); // Different genres should result in different hash codes
    }
    @Test
    void testzCompareTo() {
    	Song song1 = new Song("Title", "Artist", "Album", "Genre1");
    	assertEquals(0, song1.compareTo(song1));
    }
    @Test
    void testCompare_SongsWithDifferentTitles() {
        Song song1 = new Song("Song A", "Artist 1", "Album 1", "Genre");
        Song song2 = new Song("Song B", "Artist 2", "Album 2", "Genre");

        int result = Song.TitleComparator.compare(song1, song2);
        
        assertTrue(result < 0, "The comparator should return a negative value for 'Song A' vs 'Song B' (lexicographically smaller)");
    }

    @Test
    void testCompare_SongsWithSameTitle() {
        Song song1 = new Song("Song A", "Artist 1", "Album 1", "Genre");
        Song song2 = new Song("Song A", "Artist 2", "Album 2", "Genre");

        int result = Song.TitleComparator.compare(song1, song2);
        
        assertEquals(0, result, "The comparator should return 0 for songs with the same title");
    }

    @Test
    void testCompare_SongsWithLexicographicalOrder() {
        Song song1 = new Song("Song A", "Artist 1", "Album 1", "Genre");
        Song song2 = new Song("Song Z", "Artist 2", "Album 2", "Genre");

        int result = Song.TitleComparator.compare(song1, song2);
        
        assertTrue(result < 0, "The comparator should return a negative value for 'Song A' vs 'Song Z' (lexicographically smaller)");
    }

}
