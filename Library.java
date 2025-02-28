package Music;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

class LibraryModel{
   
	private MusicStore store;
    private HashMap<Song, Rating> songs;
    private ArrayList<PlayList> playLists;
    private ArrayList<Album> albums;
    private ArrayList<Song> favorites;
    
    public enum Rating {
    	// enums to represent rating avoid primitive obsession
    	ONE, TWO, THREE, FOUR, FIVE;
    }

    public LibraryModel() {
    	// music store
        store = new MusicStore();
        songs = new HashMap<Song, Rating>();
        favorites = new ArrayList<Song>();
        albums = new ArrayList<Album>();  
    }
    
    public void addSong(Song song) {
    	
    	for (Album album : store.getAlbums()) {
    		// for each album in music store, find list of songs
    		List<Song> songsList = album.getAllSongs();
    		// iterate through songs
    		for (Song song1: songsList) {
    			// check if song is in music store
    			if (song.getArtist().equals(song1.getArtist()) && 
    					song.getSongTitle().equals(song1.getSongTitle()))  {
    				// add song
    				songs.put(song, null);
    			}
    		}
    	}
    }
    
    public void addAlbum(Album album) {
    	
    	for (Album album1 : store.getAlbums()) {
    		// iterate through all albums in music store
    		if (album1.getArtist().equals(album.getArtist()) && 
    		// if album data matches, add album
    				album1.getTitle().equals(album.getTitle()) && 
    				album1.getGenre().equals(album.getGenre()) &&
    				album1.getYear().equals(album.getYear())) {
    			// add album
    			albums.add(album);
    			
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
    		albumCopy.add(new Album(album);
    	}
    	return albumsCopy;
    }
    
    public List<PlayList> getPlayLists() {
    	// return deep copy of playlists list
    	List<PlayList> playListsCopy = new ArrayList<PlayList>();
    	for (PlayList playList : playLists) {
    		playListsCopy.add(new PlayList(playList);
    	}
    	return playListsCopy;
    }
    
    public List<Song> getFavorites() {
    	// return immutable songs list
    	return new ArrayList<>(favorites);
    }
    
    public List<Song> getSongsByTitle(String title) {
    	// return immutable songs list
    	return store.getSongsByTitle(title);
    }
    
    public List<Song> getSongsByArtist(String artist) { 
    	// return immutable songs list
    	return store.getSongsByArtist(artist);
    }
   
    public Album getAlbumByTitle(String title) {
    	// return immutable album
    	return store.getAlbumByTitle(title);
    }
    
    public List<Album> getAlbumsByArtist(String artist) {
    	// return immutable albums list
    	return store.getAlbumsByArtist(artist);
    }
    
    public List<Song> getPlayList(PlayList playList) {
    	List<Song> plSongs = new ArrayList<Song>();
    	for (PlayList playList1: playLists) {
    		// check each plalist in playlists 
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
