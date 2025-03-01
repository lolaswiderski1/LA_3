package Model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

class LibraryModel{
    private HashMap<Song, Rating> songs;
    private ArrayList<PlayList> playLists;
    private ArrayList<Album> albums;
    private ArrayList<Song> favorites;
    
    public enum Rating {
    	// enums to represent rating avoid primitive obsession
    	UNRATED, ONE, TWO, THREE, FOUR, FIVE;
    }

    public LibraryModel() {
        songs = new HashMap<Song, Rating>();
        favorites = new ArrayList<Song>();
        albums = new ArrayList<Album>();  
    }
    
    public boolean hasSong(Song song) {
    	return songs.containsKey(song);
    }
    
    public void addSong(Song song) {
    	songs.put(song, Rating.UNRATED);
    }
    
    public boolean hasAlbum(Album album) {
    	return albums.contains(album);
    }
    
    public void addAlbum(Album album) {
    	albums.add(album);
    	for (Song song : album.getAllSongs()) {
    		if (!hasSong(song)) {
    			addSong(song);
    		}
    	}
    }
    
    public void addPlaylist(PlayList pl) {
    	playLists.add(pl);
    }
    
    public void rateSong(Song song, Rating rating) {
    	
    	for (Song song1 : songs.keySet()) {
    		// if song in songs matches song, set rating enum
    		if (song1.getSongTitle().equals(song.getSongTitle()) &&
    				song1.getArtist().equals(song.getArtist())) {
    			songs.put(song, rating);
    			
    			// if rating = 5, add to favorites
    			if (rating == Rating.FIVE) {	
        			favorites.add(song);
    			}
    		}
    	}
    }
    
    public List<String> getSongTitles() {
    	// add all song titles to string list
    	ArrayList<String> titles = new ArrayList<String>();
    	for (Song song : songs.keySet()) {
    		titles.add(song.getSongTitle());
    	}
    	return titles;
    }
    
    public List<String> getSongArtists() {
    	// add all song artists to string list
    	ArrayList<String> artists = new ArrayList<String>();
    	for (Song song : songs.keySet()) {
    		artists.add(song.getArtist());
    	}
    	return artists;
    }
    
    public List<Album> getAlbums() {
    	// return deep copy of albums list
    	ArrayList<Album> albumsCopy = new ArrayList<Album>();
    	for (Album album : albums) {
    		albumsCopy.add(new Album(album));
    	}
    	return albumsCopy;
    }
    
    public List<PlayList> getPlayLists() {
    	// return deep copy of playlists list
    	List<PlayList> playListsCopy = new ArrayList<PlayList>();
    	for (PlayList playList : playLists) {
    		playListsCopy.add(new PlayList(playList));
    	}
    	return playListsCopy;
    }
    
    public List<Song> getFavorites() {
    	// return immutable songs list
    	return new ArrayList<>(favorites);
    }
    
    public List<Song> getSongsByTitle(String title) {
    	// return all songs with desired title
    	List<Song> songsByTitle = new ArrayList<Song>();
    	for (Song song : songs.keySet()) {
    		if (song.getSongTitle().equalsIgnoreCase(title)) {
    			songsByTitle.add(song);
    		}
    	} return songsByTitle;
    }
    
    public List<Song> getSongsByArtist(String artist) { 
    	// return all songs by a certain artist
    	List<Song> songsByArtist = new ArrayList<Song>();
    	for (Song song : songs.keySet()) {
    		if (song.getArtist().equalsIgnoreCase(artist)) {
    			songsByArtist.add(song);
    		}
    	} return songsByArtist;
    }
   
    public List<Album> getAlbumsByTitle(String title) {
    	// return an album by its title name
    	List<Album> albumsByTitle = new ArrayList<>();
    	for (Album album1 : albums) {
    		if (album1.getTitle().equalsIgnoreCase(title)) {
    			albumsByTitle.add(album1);
    		} 
    	}
    	return albumsByTitle;
    }
    
    public List<Album> getAlbumsByArtist(String artist) {
    	// return immutable albums list
    	List<Album> albumsByArtist = new ArrayList<Album>();
    	for (Album album1 : albums) {
    		if (album1.getArtist().equalsIgnoreCase(artist)) {
    			albumsByArtist.add(album1);
    		}
    	} return albumsByArtist;
    }
    
    public List<Song> getPlayList(PlayList playList) {
    	List<Song> plSongs = new ArrayList<Song>();
    	for (PlayList playList1: playLists) {
    		// check each playlist in playlists 
    		if (playList1.getName().equals(playList.getName())) {
    			// if name matches return deep copy list of songs 
    			// in playlist
    			for (Song song : playList.getSongs()) {
    				plSongs.add(song);
    			}
    			return plSongs;
    		}
    	} return null;
    }
    
}
