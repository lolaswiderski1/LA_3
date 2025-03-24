
// Sam Hershey, Lola Swiderski
// class to model a playlist of songs
package dataStructures;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class PlayList {
	
	// instantiate playlist name and songs list
	private String name;
	private ArrayList<Song> songs;
	
	// initialize variables
	public PlayList(String name) {
		this.name = name;
		songs = new ArrayList<Song>();
	}

	// copy constructor for encapsulation
	public PlayList(PlayList otherPlayList) {
		this.name = otherPlayList.name;
		this.songs = otherPlayList.songs; 
		
	}
	
	// check if a playlist is empty
	public boolean isEmpty() {
		return songs.size() == 0;
	}
	
	// check if playlist contains a certain song
	public boolean hasSong(Song song) {
		return songs.contains(song);
	}
	
	// get the name of a playlist
	public String getName() {
		return name;
	}
	
	// add a song to the playlist
	public void addSong(Song song) {
		songs.add(song);
	}
	
	// remove a song from the playlist
	public void removeSong(Song song) {
	    songs.remove(song); 
	}
	
	// get the list of songs from the playlist
	public List<Song> getSongs() {
		return new ArrayList<>(songs);
	}
	
	// clear the playlist
	public void clear() {
		songs.clear();
	}
}
