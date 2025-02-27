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
    	ONE, TWO, THREE, FOUR, FIVE;
    }

    public LibraryModel() {
        store = new MusicStore();
        songs = new HashMap<Song, Rating>();
        favorites = new ArrayList<Song>();
        albums = new ArrayList<Album>();  
    }
    
    public void addSong(Song song) {
    	
    	for (Album album : store.getAlbums()) {
    		Object[] albumSongs = album.getAllSongs();
    		ArrayList<Song> songsList = (ArrayList<Song>) albumSongs[1];
    		for (Song song1: songsList) {
    			if (song.getArtist().equals(song1.getArtist()) && 
    					song.getTitle().equals(song1.getTitle()))  {
    				songs.put(song, null);
    			}
    		}
    	}
    }
    
    public void addAlbum(Album album) {
    	
    	for (Album album1 : store.getAlbums()) {
    		if (album1.getArtist().equals(album.getArtist()) && 
    				album1.getTitle().equals(album.getTitle()) && 
    				album1.getGenre().equals(album.getGenre()) &&
    				album1.getYear().equals(album.getYear())) {
    			albums.add(album);
    			
    		}
    	}
    }
    
    public void addPlaylist(PlayList pl) {
    	playLists.add(pl);
    }
    
    public void rateSong(Rating rating, Song song) {
    	
    	for (Song song1 : songs.keySet()) {
    		if (song1.getTitle().equals(song.getTitle()) &&
    				song1.getArtist().equals(song.getArtist())) {
    			songs.put(song, rating);
    			
    			if (rating == Rating.FIVE) {	// if rating 5 add to favorites
        			favorites.add(song);
    			}
    		}
    	}
    }
    
    public ArrayList<String> getSongTitles() {
    	
    	ArrayList<String> titles = new ArrayList<String>();
    	for (Song song : songs.keySet()) {
    		titles.add(song.getTitle());
    	}
    	return titles;
    }
    
    public ArrayList<String> getSongArtists() {
    	
    	ArrayList<String> artists = new ArrayList<String>();
    	for (Song song : songs.keySet()) {
    		artists.add(song.getArtist());
    	}
    	return artists;
    }
    
    public ArrayList<Album> getAlbums() {
    	return new ArrayList<>(albums);
    }
    
    public ArrayList<PlayList> getPlayLists() {
    	return new ArrayList<>(playLists);
    }
    
    public ArrayList<Song> getFavorites() {
    	return new ArrayList<>(favorites);
    }
    
    public List<Song> getSongsByTitle(String title) {
    	return store.getSongsByTitle(title);
    }
    
    public List<Song> getSongsByArtist(String artist) { 
    	return store.getSongsbyArtist(artist);
    }
   
    public Album getAlbumByTitle(String title) {
    	return store.getAlbumByTitle(title);
    }
    
    public List<Album> getAlbumsByArtist(String artist) {
    	return store.getAlbumsByArtist(artist);
    }
    
    public ArrayList<Song> getPlayList(PlayList playList) {
    	ArrayList<Song> pLSongs = new ArrayList<Song>();
    	for (PlayList playList1: playLists) {
    		if (playList1.getName().equals(playList.getName())) {
    			return new ArrayList<>(playList1.getSongs());
    		}
    	} return null;
    }
    
}
