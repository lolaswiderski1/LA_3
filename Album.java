// Sam Hershey, Lola Swiderski
// class to simulate Album object. Albums contain artist, titles, genre, year, and songs

package dataStructures;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class Album {
	// instantiate variables
	private String artist;
	private String title;
	private String genre;
	private String year;
	private List<Song> songs = new ArrayList<>();
	
	// initialize variables
	public Album(String title, String artist, String genre, String year) {
		this.title = title;
		this.artist = artist;
		this.genre = genre;
		this.year = year;
	}
	
	// Copy constructor
	public Album(Album otherAlbum) {
		// encapsulation
		this.artist = otherAlbum.artist;
		this.title = otherAlbum.title;
		this.genre = otherAlbum.genre;
		this.year = otherAlbum.year;
		this.songs = otherAlbum.songs;
	}
	
	// get title
	public String getTitle() {
		return title;
	}
	
	// get genre
	public String getGenre() {
		return genre;
	}
	
	// get artist
	public String getArtist() {
		return artist;
	}
	
	// get year
	public String getYear() {
		return year;
	}
	
	// retrieve a song by its title name from songs list
	public Song getSongByTitle(String songTitle) {
		for (Song song : songs) {
			if (song.getSongTitle().equalsIgnoreCase(songTitle)) {
				return song;
			}
		}
		return null;
		
	}
	
	// returns all songs in Album in order
	public List<Song> getAllSongs() {
		return new ArrayList<>(songs);
	}
	
	// add a song to the album
	public void addSong(Song song) {
		songs.add(song);
	}
	
	// check if the album contains a certain song
	public boolean hasSong(String songTitle) {
		boolean hasSong = false;
		for (Song song : songs) {
			if (song.getSongTitle().equalsIgnoreCase(songTitle)) {
				hasSong = true;
			}
		}
		return hasSong;
	}
	
	// print album
	public String toString() {
		String result = title + ", " + artist + ", " + genre + ", " + year + "\n";
		for (Song song : songs) {
			result += song + "\n";
		}
		return result;
	}
	
	@Override
	public boolean equals(Object obj) {
	    if (this == obj) return true;
	    // Check if obj is null or of a different class
	    if (obj == null || getClass() != obj.getClass()) return false; 

	    Album otherAlbum = (Album) obj; // Cast obj to Album
	    
	    // Check if the two albums have the same number of songs
	    if (songs.size() != otherAlbum.songs.size()) return false;
	    
	    // Create a set of song titles from this album
	    java.util.Set<String> thisSongTitles = new java.util.HashSet<>();
	    for (Song song : songs) {
	        thisSongTitles.add(song.getSongTitle().toLowerCase());
	    }
	    
	    // Check if all songs in the other album are in this album
	    for (Song song : otherAlbum.songs) {
	        if (!thisSongTitles.contains(song.getSongTitle().toLowerCase())) {
	            return false;
	        }
	    }

	    return true; // If all songs match, the albums are equal
	}

	@Override
	public int hashCode() {
	    // Use a set to compute the hash code, since the order of songs doesn't matter
	    java.util.Set<String> songTitles = new java.util.HashSet<>();
	    for (Song song : songs) {
	        songTitles.add(song.getSongTitle().toLowerCase()); // Use lowercase for consistency
	    }

	    // Compute the hash code based on the set of song titles
	    return songTitles.hashCode();
	}
}
