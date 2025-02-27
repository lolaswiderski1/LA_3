package Music;

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
