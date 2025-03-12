// Sam Hershey, Lola Swiderski
// class to simulate library model object. Contains methods necessary for a Library.
// libraries contain playLists, songs, albums, and favorite songs

package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import dataStructures.Album;
import dataStructures.PlayList;
import dataStructures.Song;

public class LibraryModel{
	
	// instantiate variables
    private LinkedHashMap<Song, Rating> songs;
    private ArrayList<PlayList> playLists;
    private PlayList recents;
    private PlayList frequents;
    private ArrayList<Album> albums;
    private ArrayList<Song> favorites;
    private ArrayList<Song> playedSongs;
    
    // enum to represent rating avoid primitive obsession
    public enum Rating {
    	UNRATED, ONE, TWO, THREE, FOUR, FIVE;
    }
    
    // construct/initialize variables
    public LibraryModel() {
        songs = new LinkedHashMap<Song, Rating>();
        favorites = new ArrayList<Song>();
        albums = new ArrayList<Album>();  
        playLists = new ArrayList<PlayList>();
        playedSongs = new ArrayList<Song>();
        recents = new PlayList("Most recent songs: ");	// default playlists come with library
        frequents = new PlayList("Most played songs: ");
        playLists.add(recents);
        playLists.add(frequents);
        
    }
    
    public void removeSong(Song song) {
    	songs.remove(song);
    }
    
    public List<Song> sortByTitle() {
    	List<Song> sort = new ArrayList<Song>();
    	for (Song song: songs.keySet()) {
    		sort.add(song);
    	}
    	Collections.sort(sort, Song.TitleComparator);
    	return sort;
    }
    
    public List<Song> sortByArtist() {
    	List<Song> sort = new ArrayList<Song>();
    	for (Song song: songs.keySet()) {
    		sort.add(song);
    	}
    	Collections.sort(sort, Song.ArtistComparator);
    	return sort;
    }
    
    public List<Song> sortByRating() {
    	ArrayList<Song> result = new ArrayList<Song>();
    	HashMap<Song, Integer> songsCopy = new HashMap<Song, Integer>();
    	for (Song song: songs.keySet()) {
			switch (getRating(song)) {
				case UNRATED:
					continue;
				case ONE:
					songsCopy.put(song, 1);
					break;
				case TWO:
					songsCopy.put(song, 2);
					break;
				case THREE:
					songsCopy.put(song, 3);
					break;
				case FOUR:
					songsCopy.put(song, 4);
					break;
				case FIVE:
					songsCopy.put(song, 5);
					break;
	    		}
    	}
    	List<Map.Entry<Song, Integer>> sorted = bubbleSort(songsCopy);
    	Collections.reverse(sorted);
	    for (Map.Entry<Song, Integer> entry : sorted) {	// get the songs without count
            result.add(entry.getKey());
        }

    	return result;
    }
    
   
    
    public void playSong(Song song) {
       playedSongs.add(song);
       getMostFrequent();
       ArrayList<Song> playedCopy = new ArrayList<Song>();
       
       for (int i = playedSongs.size() - 1; i >= 0; i-- ) {
    	   playedCopy.add(playedSongs.get(i));
       }
       recents.clear();
       if (playedSongs.size() < 10) {
    	   for (int i = 0; i < playedSongs.size(); i++ ) {
        	   recents.addSong(playedCopy.get(i));
           }
       } else {  
    	   for (int i = 0; i < 10; i++ ) {
    		   recents.addSong(playedCopy.get(i));
    	   }  
       }
    }
    
    public void getMostFrequent() {
    	
    	HashMap<Song, Integer> countSongs = new HashMap<>();
    	for (Song song : playedSongs) {
    	    if (countSongs.containsKey(song)) { 
    	        countSongs.put(song, countSongs.get(song) + 1);
    	    } else { 
    	        countSongs.put(song, 1);
    	    }
    	}
        
    	List<Map.Entry<Song, Integer>> sortCount = bubbleSort(countSongs).subList(0, Math.min(10, countSongs.size()));
        ArrayList<Song> mostFrequent = new ArrayList<>();
        for (Map.Entry<Song, Integer> entry : sortCount) {	// get the songs without count
            mostFrequent.add(entry.getKey());
        }

        frequents.clear(); 
        for (Song song : mostFrequent) {
            frequents.addSong(song);
        }

    }

   
    public static List<Map.Entry<Song, Integer>> bubbleSort(HashMap<Song, Integer> map) {
        List<Map.Entry<Song, Integer>> entryList = new ArrayList<>(map.entrySet());

        for (int i = 0; i < entryList.size() - 1; i++) {
            boolean swapped = false;  
            for (int j = 0; j < entryList.size() - i - 1; j++) {
                if (entryList.get(j).getValue() < entryList.get(j + 1).getValue()) {  
                    Collections.swap(entryList, j, j + 1);
                    swapped = true;
                }
            }
            if (!swapped) break;
        }
        return entryList;
    }

       
    // get a list of all songs in library
    public List<Song> getAllSongs() {
        return new ArrayList<>(songs.keySet());
    }
    
    // check is the library has a certain song
    public boolean hasSong(Song song) {
    	return songs.containsKey(song);
    }
    
    // add a song to the library
    public void addSong(Song song) {
    	songs.put(song, Rating.UNRATED);
    }
    
    // check if an album is in the library
    public boolean hasAlbum(Album album) {
    	return albums.contains(album);
    }
    
    // add an album to the library
    public void addAlbum(Album album) {
    	albums.add(album);
    	for (Song song : album.getAllSongs()) {
    		if (!hasSong(song)) {
    			addSong(song);
    		}
    	}
    }
    
    // add a playlist to the library
    public void addPlaylist(PlayList pl) {
    	playLists.add(pl);
    }
    
    // rate a song in the library
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
    
    public Rating getRating(Song song) {
    	return songs.get(song);
    }

    
    // check if a song exists in the library depending on its title
    public boolean hasSongByTitle(String title) {
    	boolean result = false;
    	for (Song song : songs.keySet()) {
    		if (song.getSongTitle().equalsIgnoreCase(title)) {
    			result = true;
    		}
    	}
    	return result;
    }
   
    // check if the library contains songs by a certain artist
    public boolean hasSongByArtist(String artist) {
    	boolean result = false;
    	for (Song song : songs.keySet()) {
    		if (song.getArtist().equalsIgnoreCase(artist)) {
    			result = true;
    		}
    	}
    	return result;
    }
    
    // check if the library contains albums by a certain artist
    public boolean hasAlbumByArtist(String artist) {
    	boolean result = false;
    	for (Album album : albums) {
    		if (album.getArtist().equalsIgnoreCase(artist)) {
    			result = true;
    		}
    	}
    	return result;
    }
   
    // check if an album is in the library from its title
    public boolean hasAlbumByTitle(String title) {
    	boolean result = false;
    	for (Album album : albums) {
    		if (album.getTitle().equalsIgnoreCase(title)) {
    			result = true;
    		}
    	}
    	return result;
    }
    
    // add a favorite to the favorites list
    public void addFavorite(Song song) {
    	rateSong(song, Rating.FIVE);
    	favorites.add(song);
    }

    // get a list of song titles
    public List<String> getSongTitles() {
    	// add all song titles to string list
    	ArrayList<String> titles = new ArrayList<String>();
    	for (Song song : songs.keySet()) {
    		titles.add(song.getSongTitle());
    	}
    	return titles;
    }
    
    // get a list of all song artists
    public Set<String> getSongArtists() {
        // Use HashSet to store unique artist names
        Set<String> artists = new HashSet<>();
        // Iterate over the song keys and collect artist names
        for (Song song : songs.keySet()) {
            artists.add(song.getArtist());
        }

        return artists;
    }
    
    // get a list of all albums
    public List<Album> getAlbums() {
    	// return deep copy of albums list
    	ArrayList<Album> albumsCopy = new ArrayList<Album>();
    	for (Album album : albums) {
    		albumsCopy.add(new Album(album));
    	}
    	return albumsCopy;
    }
    
    // get a list of all album titles
    public List<String> getAlbumTitles() {
    	// return deep copy of albums list
    	ArrayList<String> albumTitles = new ArrayList<String>();
    	for (Album album : albums) {
    		albumTitles.add(album.getTitle());
    	}
    	return albumTitles;
    }
    
    // get a list of all album artists
    public List<String> getAlbumArtists() {
    	// return deep copy of albums list
    	ArrayList<String> albumArtists = new ArrayList<String>();
    	for (Album album : albums) {
    		albumArtists.add(album.getArtist());
    	}
    	return albumArtists;
    }
    
    // get a list of all playlists
    public List<PlayList> getPlayLists() {
    	// return deep copy of playlists list
    	List<PlayList> playListsCopy = new ArrayList<PlayList>();
    	for (PlayList playList : playLists) {
    		playListsCopy.add(new PlayList(playList));
    	}
    	return playListsCopy;
    }
    
    // get a list of the favorite songs
    public List<Song> getFavorites() {
    	// return immutable songs list
    	return new ArrayList<>(favorites);
    }
    
    // get a list of songs with a specified title
    public List<Song> getSongsByTitle(String title) {
    	// return all songs with desired title
    	List<Song> songsByTitle = new ArrayList<Song>();
    	for (Song song : songs.keySet()) {
    		if (song.getSongTitle().equalsIgnoreCase(title)) {
    			songsByTitle.add(song);
    		}
    	} return songsByTitle;
    }
    
    // get a list of songs by a certain artist
    public List<Song> getSongsByArtist(String artist) { 
    	// return all songs by a certain artist
    	List<Song> songsByArtist = new ArrayList<Song>();
    	for (Song song : songs.keySet()) {
    		if (song.getArtist().equalsIgnoreCase(artist)) {
    			songsByArtist.add(song);
    		}
    	} return songsByArtist;
    }
   
    // get a list of albums with a certain title
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
    
    // get a list of albums by an artist
    public List<Album> getAlbumsByArtist(String artist) {
    	// return immutable albums list
    	List<Album> albumsByArtist = new ArrayList<Album>();
    	for (Album album1 : albums) {
    		if (album1.getArtist().equalsIgnoreCase(artist)) {
    			albumsByArtist.add(album1);
    		}
    	} return albumsByArtist;
    }
    
    // get a list of songs within a playlist
    public List<Song> getPlayList(PlayList playList) {
    	System.out.println(playLists);
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
