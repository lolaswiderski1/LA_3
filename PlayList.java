package Model;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class PlayList {
	private String name;
	private ArrayList<Song> songs;
	
	public PlayList(String name) {
		this.name = name;
		songs = new ArrayList<Song>();
	}

	// Copy constructor
	public PlayList(PlayList otherPlayList) {
		this.name = otherPlayList.name;
		this.songs = otherPlayList.songs;
	}
	
	public boolean hasSong(Song song) {
		return songs.contains(song);
	}
	
	public String getName() {
		return name;
	}
	public void addSong(Song song) {
		songs.add(song);
	}
	public void removeSong(Song song) {
	    songs.remove(song); 
	}
	public List<Song> getSongs() {
		return new ArrayList<>(songs);
	}
}
