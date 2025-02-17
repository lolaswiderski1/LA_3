package Music;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

public class PlayList {
	private String name;
	private Map<String, Song> songs = new LinkedHashMap<>();
	
	public PlayList(String name) {
		this.name = name;
	}
	public void addSong(Song song) {
		songs.put(song.getTitle(), song);
	}
	public void removeSong(Song song) {
	    songs.remove(song.getTitle()); 
	}
	public List<Song> getSongs() {
	    return new ArrayList<>(songs.values());
	}
}
