package Model;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class Album {
	private String artist;
	private String title;
	private String genre;
	private String year;
	private List<Song> songs = new ArrayList<>();
	
	public Album(String title, String artist, String genre, String year) {
		this.title = title;
		this.artist = artist;
		this.genre = genre;
		this.year = year;
	}
	
	// Copy constructor
	public Album(Album otherAlbum) {
		this.artist = otherAlbum.artist;
		this.title = otherAlbum.title;
		this.genre = otherAlbum.genre;
		this.year = otherAlbum.year;
		this.songs = otherAlbum.songs;
	}
	
	public String getTitle() {
		return title;
	}
	
	public String getGenre() {
		return genre;
	}
	
	public String getArtist() {
		return artist;
	}
	
	public String getYear() {
		return year;
	}
	
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
	
	public void addSong(Song song) {
		songs.add(song);
	}
	
	public boolean hasSong(String songTitle) {
		boolean hasSong = false;
		for (Song song : songs) {
			if (song.getSongTitle().equalsIgnoreCase(songTitle)) {
				hasSong = true;
			}
		}
		return hasSong;
	}
	
	public String toString() {
		String result = title + ", " + artist + ", " + genre + ", " + year + "\n";
		for (Song song : songs) {
			result += song + "\n";
		}
		return result;
	}
}
