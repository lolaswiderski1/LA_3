// Sam Hershey, Lola Swiderski
// class to simulate Album object. Albums contain artist, titles, genre, year, and songs

package LA1;

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
}
