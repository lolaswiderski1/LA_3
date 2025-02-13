package Music;

import java.util.ArrayList;

public class Album {
	private String artist;
	private String title;
	private String genre;
	private String year;
	private ArrayList<Song> songs;
	
	public Album(String title, String artist, String genre, String year) {
		this.title = title;
		this.artist = artist;
		this.genre = genre;
		this.year = year;
		this.songs = new ArrayList<Song>();
	}
	
	public ArrayList<Song> getSongs(){
		return new ArrayList<Song>(songs);
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
	
	public void addSong(Song song) {
		songs.add(song);
	}
	
	public String toString() {
		String result = "";
		for (Song song : songs) {
			result += song + "\n";
		}
		return result;
	}
}

