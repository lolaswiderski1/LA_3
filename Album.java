package Model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Album {
	private String artist;
	private String title;
	private String genre;
	private String year;
	private Map<String, Song> songs = new HashMap<>();
	
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
	}
	
	public HashMap<String, Song> getSongs(){
		return new HashMap<>(songs); 
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
		return songs.get(songTitle);
	}
	
	public ArrayList<Song> getAllSongs() {
		return new ArrayList<>(songs.values());
	}
	
	public void addSong(Song song) {
		songs.put(song.getTitle(), song);
	}
	
	public boolean hasSong(String title) {
		return songs.containsKey(title);
	}
	
	public String toString() {
		String result = title + ", " + artist + ", " + genre + ", " + year + "\n";
		for (Song song : songs.values()) {
			result += song + "\n";
		}
		return result;
	}
}

