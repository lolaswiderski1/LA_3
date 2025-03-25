// Authors: Sam Hershey, Lola Swiderski
// Description:	Library model contains the users information of a music library
// it allows them to preform tasks and saves their data.

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
	
	// instantiate playlists, songs, favorites, 
	// playedsongs, albums, and genres
	// library features
	private LinkedHashMap<Song, Rating> songs;
    private ArrayList<PlayList> playLists;
    public ArrayList<Song> favorites;
    private ArrayList<Song> playedSongs;
    private ArrayList<Album> albums;
    private HashMap<String, List<Song>> genres;
    // instantiate default playlists in library
    private PlayList recents;
    private PlayList frequents;
    private PlayList favoritesPlayList;
    
    // stores a rating system so that users can rate songs
    public enum Rating {
    	UNRATED, ONE, TWO, THREE, FOUR, FIVE;
    }
    
    // library model contructor
    public LibraryModel() {
    	songs = new LinkedHashMap<Song, Rating>();	// songs contain song object ane rating
        favorites = new ArrayList<Song>();	// store favorite songs
        albums = new ArrayList<Album>();	// store albums 
        playLists = new ArrayList<PlayList>();    // store playlists
        playedSongs = new ArrayList<Song>();	// store played songs
        genres = new HashMap<>();
        // store default playlists automatically in library object
        recents = new PlayList("Most recent songs");	// store recently played songs pl
        frequents = new PlayList("Most played songs");    // store most frequently played songs pl
        favoritesPlayList = new PlayList("Favorited songs");	// store favorite songs pl
        // add the playlists to the library
        playLists.add(recents);
        playLists.add(frequents);
        playLists.add(favoritesPlayList);
    }
    
    // create a shallow copy constructor for encapsulation
    public LibraryModel(LibraryModel other) {
    	// make all features equal to another library's features
    	this.songs = other.songs;
    	this.favorites = other.favorites;
    	this.albums = other.albums;  
    	this.playLists = other.playLists;
    	this.playedSongs = other.playedSongs;
    	this.recents = other.recents;
    	this.frequents = other.frequents;
    	this.favoritesPlayList = other.favoritesPlayList;
    	this.genres = other.genres; 
    }
    
    // shuffleSongs shuffles the songs in the song map randomly using 
    // Collections.shuffle.
    public List<Song> shuffleSongs() {
    	// new list of shuffled songs list
    	List<Song> songList = new ArrayList<>(songs.keySet());
        Collections.shuffle(songList);
        return songList;
    }
    
    // shufflePlaylists shuffles the playlists in the playlists list
    // using Collections.shuffle.
    public List<PlayList> shufflePlaylists() {
        Collections.shuffle(playLists);
        return playLists;
    }
    
    // removeSong removes a song from the songs map as well as any playlist
    // that the song was in.
    public void removeSong(Song song) {
    	// remove from songs map
    	songs.remove(song);
    	// remove from playlists
    	for (PlayList pl : playLists) {
    		if (pl.hasSong(song)) {
    			pl.removeSong(song);
    		}
    	}
    	for (Album album : albums) {
    		if (album.hasSong(song.getSongTitle())) {
    			album.removeSong(song); 
    		}
    	}
    }
    
    public void removeAlbum(Album album) {
		while (album.getAllSongs().size() != 0) {
			removeSong(album.getAllSongs().get(0));
		}
		int i = 0;
		while (i < albums.size())
		{
			if (albums.get(i).getTitle().equals(album.getTitle())) {
				albums.remove(i);
			}
			else {
				i++;
			}
		}
		
	}
    
    // sortByTitle sorts the songs in the songs map in alphabetical order by the
    // song titles
    public List<Song> sortByTitle() {
    	List<Song> sort = new ArrayList<Song>();
    	// iterate through song objects
    	for (Song song: songs.keySet()) {
    		sort.add(song);
    	}
    	// use the Title comparison method in Song
    	Collections.sort(sort, Song.TitleComparator);
    	return sort;
    }
   
    // sortByArtist sorts the songs in the songs map in alphabetical order by the
    // song artists
    public List<Song> sortByArtist() {
    	List<Song> sort = new ArrayList<Song>();
    	// iterate through song objects
    	for (Song song: songs.keySet()) {
    		sort.add(song);
    	}
    	// use the Title comparison method in Song
    	Collections.sort(sort, Song.ArtistComparator);
    	return sort;
    }
    
    // songByRating sorts songs in the songs map in ascending order by ratings
    public List<Song> sortByRating() {
    	// create a list of the sorted values
    	ArrayList<Song> result = new ArrayList<Song>();
    	// hashmap to store the ratings as integers instead of as enums. 
    	HashMap<Song, Integer> songsCopy = new HashMap<Song, Integer>();
    	for (Song song: songs.keySet()) {
    		// one = 1, two = 2, three = 3, four = 4, 5= five
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
    	// sort the hashmap in descending order using Collections.sort
    	List<Map.Entry<Song, Integer>> sorted = sortByValue(songsCopy);
    	// put the list in ascending by reversing the list
    	Collections.reverse(sorted);
    	// add the songs without rating to the sorted result list
	    for (Map.Entry<Song, Integer> entry : sorted) {
            result.add(entry.getKey());
        }
    	return result;
    }
    
	 // playSong plays a song by adding it to the playedSongs list, recents
	 // playlist, and sorts it in the frequents playlist.
	 public void playSong(Song song) {
	     // add the song to playedSongs
	     playedSongs.add(song); 
	     // sort through most frequents
	     getMostFrequent();
	     // add songs to played list backwards
	     ArrayList<Song> playedCopy = new ArrayList<>();
	     for (int i = playedSongs.size() - 1; i >= 0; i--) {
	         playedCopy.add(playedSongs.get(i));
	     }
	     // clear the recents playlist
	     recents.clear();
	     // only add the first 10 most recently played songs to recents
	     int count = 0;
	     for (int i = 0; i < playedCopy.size() && count < 10; i++) {
	         Song currentSong = playedCopy.get(i);
	         // Only add if it's not already in recents
	         if (!recents.hasSong(currentSong)) {
	             recents.addSong(currentSong);
	             count++; // Increase the count of unique songs added
	         }
	     }
	 }

    
    // countSongs counts how many times a song appears in a list
    public HashMap<Song, Integer> countSongs(List<Song> songsList){
    	// create a hashmap containing the song and its integer count value
    	HashMap<Song, Integer> countSongs = new HashMap<>();
        // for each song in the songs list, if it's not in the countSongs hashmap, 
    	// its initialize count to 1, if it is, up the count by 1.
    	for (Song song : songsList) {
    	    if (countSongs.containsKey(song)) { 
    	        countSongs.put(song, countSongs.get(song) + 1);
    	    } else { 
    	        countSongs.put(song, 1);
    	    }
    	}
        return countSongs;
    }
    
    // check to see if the library contains a playlist so that the user can search
    // for a playlist
    public boolean hasPlaylist(String playListName) {
    	// search each playlist in the library to find if it exists
    	for (PlayList pl : playLists) {
    		if (pl.getName().equals(playListName)) {
    			return true;
    		}
    	}
    	return false;
    }
    
    // getMostFrequent sorts the songs in the songs map by which ones are played the most frequently
    // in ascendng order.
    public void getMostFrequent() {
    	// count how many times each song is played
        HashMap<Song, Integer> countSongs = countSongs(playedSongs);
        // sort the values of the played song counts
        List<Map.Entry<Song, Integer>> sortCount = sortByValue(countSongs).subList(0, Math.min(10, countSongs.size()));
        ArrayList<Song> mostFrequent = new ArrayList<>();
        // extract songs without count
        for (Map.Entry<Song, Integer> entry : sortCount) { 
            mostFrequent.add(entry.getKey());
        }
        // clear the frequents playlist each time to avoid duplicates
        frequents.clear();
        HashSet<String> addedSongTitles = new HashSet<>(); // To track added song titles
        // ensure the uniqueness of each song to avoid duplicates
        for (Song song : mostFrequent) {
            if (!addedSongTitles.contains(song.getSongTitle())) { 
                frequents.addSong(song);
                addedSongTitles.add(song.getSongTitle()); 
            }
        }
    }

    // sortByValue sorts a hashmap descending order
    public static List<Map.Entry<Song, Integer>> sortByValue(HashMap<Song, Integer> map) {
    	// sort using Collections.sort() with a custom comparator 
        List<Map.Entry<Song, Integer>> entryList = new ArrayList<>(map.entrySet());
        Collections.sort(entryList, (a, b) -> b.getValue().compareTo(a.getValue()));
        // returns the sorted list
        return entryList; 
    }
    
    // addSongsToAlbum adds a list of songs to an album
    public void addSongsToAlbum(String albumTitle, List<Song> songs) {
    	// get the album to add songs to
    	List<Album> albums = getAlbumsByTitle(albumTitle);
    	// get and remove album with matching title
    	Album album = albums.remove(0); 
    	for (Song song : songs) {
    		// add each song to album if it wasn't already there
    		if (!album.hasSong(song.getSongTitle())) {
    			album.addSong(song);
    		}
    		// add each song to songs if it wasn't already there
    		if (!hasSong(song)) {
    			addSong(song);
    		}
    	}
    	// re-add album with the new songs
    	albums.add(album);
    }
       
    // getAllSongs gets a list of all songs in library
    public List<Song> getAllSongs() {
    	// return copy of songs key set
        return new ArrayList<>(songs.keySet());
    }
    
    // hasSong checks if the library has a certain song
    public boolean hasSong(Song song) {
    	return songs.containsKey(song);
    }
    
    // addSong adds a song to the library and stores its genre in case
    // a default playlist is to be made
    public void addSong(Song song) {
    	// put the song in the songs map
    	songs.put(song, Rating.UNRATED);
    	// initialize genre map containing the genre and the songs of that genre
        if (!genres.containsKey(song.getGenre())) {
            genres.put(song.getGenre(), new ArrayList<>());
        }
        // add the song to its genre map
        genres.get(song.getGenre()).add(song);
        // if more than 10 songs are in the genre key, make a playlist of the songs
        // and add it to the library automatically
        if (genres.get(song.getGenre()).size() == 10) {
            PlayList genrePl = new PlayList(song.getGenre().toUpperCase());
            // add the songs to the genres playlist
            for (Song song1 : genres.get(song.getGenre())) {
                genrePl.addSong(song1);
            }
            // check if the playlist is already in the library
            boolean exists = false;
            for (PlayList pl : playLists) {
                if (pl.getName().equalsIgnoreCase(song.getGenre().toUpperCase())) {
                    exists = true;
                    break;
                }
            }
            // add the playlist to library
            if (!exists) {
                playLists.add(genrePl);
            }
        }
    }
    
    // hasAlbum checks if an album is in the library
    public boolean hasAlbum(Album album) {
	    // check if each album in the library matches the name and artist of the passed
	    // in album
    	for (Album album1 : albums) {
    		if (album.getTitle().equals(album1.getTitle()) && album.getArtist().
    				equals(album1.getArtist())) {
    			return true;
    		}
    	}
    	// if the album is not found, return false
    	return false;
    }
    
    // addAlbum adds an album to the library
    public void addAlbum(Album album) {
    	// add the album to the albums list
		albums.add(album);
		// add each song in the album to the songs map
		for (Song song : album.getAllSongs()) {
    		if (!hasSong(song)) {
    			addSong(song); 
    		}
    	}
	} 
    
    
    // addPlaylist adds a playlist to the library
    public void addPlaylist(PlayList pl) {
    	playLists.add(pl);
    }
    
    // rateSong rates a song in the library with a value from 1 to 5
    public void rateSong(Song song, Rating rating) {
        // find the song in the songs map
        for (Map.Entry<Song, Rating> entry : songs.entrySet()) {
            Song song1 = entry.getKey();
            // if the song matches, update its rating
            if (song1.getSongTitle().equals(song.getSongTitle()) &&
                song1.getArtist().equals(song.getArtist())) {
                
                entry.setValue(rating);

                // if rating is FIVE, add to favorites if not already present
                if (rating == Rating.FIVE && !favorites.contains(song1)) {
                    favoritesPlayList.addSong(song1);
                    favorites.add(song);
                } 
                // if rating is less than FIVE, remove from favorites if present
                else if (rating != Rating.FIVE) {
                	favoritesPlayList.removeSong(song1);
                	favorites.remove(song);
                }

                // exit the loop once the song is updated
                break;
            }
        }
    }

    
    // getRating returns the rating of a song
    public Rating getRating(Song song) {
    	// the rating is the value of the ley value pairs in the songs map
    	return songs.get(song);
    }

    
    // hasSongByTitle checks if a song exists in the library depending on its title
    public boolean hasSongByTitle(String title) {
    	// initialize the is found value as false for default
    	boolean result = false;
    	// search through each song in the songs map and check if the titles match
    	for (Song song : songs.keySet()) {
    		if (song.getSongTitle().equalsIgnoreCase(title)) {
    			result = true;
    		}
    	}
    	// result is whether a song exists by title or not
    	return result;
    }
   
    // hasSongByArtist checks if the library contains songs by a certain artist
    public boolean hasSongByArtist(String artist) {
    	// initialize the is found value as false for default
    	boolean result = false;
    	// search through each song in the songs map and check if the artists match
    	for (Song song : songs.keySet()) {
    		if (song.getArtist().equalsIgnoreCase(artist)) {
    			result = true;
    		}
    	}
    	// result is whether a song exists by artist or not
    	return result;
    }
    
    // hasAlbumByArtist checks if the library contains albums by a certain artist
    public boolean hasAlbumByArtist(String artist) {
    	// initialize the is found value as false for default
    	boolean result = false;
    	// search through each album in the albums list and check if the artists match
    	for (Album album : albums) {
    		if (album.getArtist().equalsIgnoreCase(artist)) { 
    			result = true;
    		}
    	}
    	// result is whether a album exists by artist or not
    	return result;
    }
   
    // hasAlbumByTitle checks if an album is in the library from its title
    public boolean hasAlbumByTitle(String title) {
    	// initialize the is found value as false for default
    	boolean result = false;
    	// search through each album in the albums list and check if the titles match
    	for (Album album : albums) {
    		if (album.getTitle().equalsIgnoreCase(title)) {
    			result = true;
    		}
    	}
    	// result is whether an album exists by title or not
    	return result;
    }
    
    // addFavorite adds a favorite to the favorites list
    public void addFavorite(Song song) {
    	// when a song is favorited, it's automatically rated a 5
    	rateSong(song, Rating.FIVE);
    }
    
    // getSongsMap returns the map of songs in library to ratings
    public Map<Song, Rating> getSongsMap() {
    	return songs;
    }
    
    // getRecents returns the most recently played playlist
    public PlayList getRecents() {
    	return new PlayList(recents);
    }
    
    // getFrequents returns the most frequently played playlist
    public PlayList getFrequents() {
    	return new PlayList(frequents);
    }
    
    // getFavoritesPlayList returns the favorited songs playList
    public PlayList getFavoritesPlayList() {
		return new PlayList(favoritesPlayList);
	}
    
    // getPlayedSongs returns the list of played songs
    public List<Song> getPlayedSongs() {
    	return new ArrayList<>(playedSongs);
    }
    
    // getGenres returns the map of genres in the library to their list
    // of songs
    public Map<String, List<Song>> getGenres() {
    	return new HashMap<>(genres);
    }
    
    // getSongTitles returns a list of all song titles in the library
    public List<String> getSongTitles() {
    	// initalize a list of strings to store the titles
    	ArrayList<String> titles = new ArrayList<String>();
    	// add all of the song titles to String list
    	for (Song song : songs.keySet()) {
    		titles.add(song.getSongTitle());
    	}
    	// return the list of titles
    	return titles;
    }
    
    // getSongArtists returns a list of all song artists
    public Set<String> getSongArtists() {
        // use HashSet to store unique artist names
        Set<String> artists = new HashSet<>();
        // iterate over the song keys and store artist names
        for (Song song : songs.keySet()) {
            artists.add(song.getArtist());
        }
        // return the set of artists
        return artists;
    }
    
    // getAlbums returns a list of all albums in the library
    public List<Album> getAlbums() {
    	// return deep copy of albums list
    	ArrayList<Album> albumsCopy = new ArrayList<Album>();
    	// add the albums to the albums copy
    	for (Album album : albums) {
    		albumsCopy.add(new Album(album));
    	}
    	return albumsCopy;
    }
    
    // getAlbumTitles returns a list of all album titles in the library
    public List<String> getAlbumTitles() {
    	// make a list of strings to store the album titles
    	ArrayList<String> albumTitles = new ArrayList<String>();
    	// add the album titles to the String list
    	for (Album album : albums) {
    		albumTitles.add(album.getTitle());
    	}
    	return albumTitles;
    }
    
    // getAlbumArtists returns a list of all album artists
    public List<String> getAlbumArtists() {
    	// make a list of strings to store the album artists
    	ArrayList<String> albumArtists = new ArrayList<String>();
    	// add the album artists to the String list
    	for (Album album : albums) {
    		albumArtists.add(album.getArtist());
    	}
    	return albumArtists;
    }
    
    //getPlayLists returns a list of all playlists
    public List<PlayList> getPlayLists() {
    	// return a copy of the playlists in the playlists list
    	List<PlayList> playListsCopy = new ArrayList<PlayList>();
    	// add each playlist to the playlists copy
    	for (PlayList playList : playLists) {
    		playListsCopy.add(new PlayList(playList));
    	}
    	return playListsCopy;
    }
    
    public void setPlayList(PlayList newPlayList) {
    	for (int i = 0; i < playLists.size(); i++) {
    		if (playLists.get(i).getName().equals(newPlayList.getName())) {
    			playLists.set(i, new PlayList(newPlayList));
    		}
    	}
    }
    
    // getFavorites returns a list of the users favorite songs in library
    public List<Song> getFavorites() {
    	// return an immutable songs list
    	return new ArrayList<>(favorites);
    }
    
    // getSongsByTitle returns a list of songs with a specified title
    public List<Song> getSongsByTitle(String title) {
    	// check if for each song in the songs map, one of them matches the 
    	// desired title
    	List<Song> songsByTitle = new ArrayList<Song>();
    	for (Song song : songs.keySet()) { 
    		// ensure case insensitivity
    		if (song.getSongTitle().equalsIgnoreCase(title)) {
    			songsByTitle.add(song);
    		}
    	} return songsByTitle;
    }
    
    // getSongsByArtist returns a list of songs with a specified artist
    public List<Song> getSongsByArtist(String artist) { 
    	// check if for each song in the songs map, one of them matches the 
    	// desired artist
    	List<Song> songsByArtist = new ArrayList<Song>();
    	for (Song song : songs.keySet()) {
    		// ensure case insensitivity
    		if (song.getArtist().equalsIgnoreCase(artist)) {
    			songsByArtist.add(song);
    		}
    	} return songsByArtist;
    }
   
    // getAlbumsByTitle returns a list of albums with a specified title
    public List<Album> getAlbumsByTitle(String title) {
    	// check if for each album in the albums list, one of them matches the 
    	// desired title
    	List<Album> albumsByTitle = new ArrayList<>();
    	for (Album album1 : albums) {
    		// ensure case insensitivity
    		if (album1.getTitle().equalsIgnoreCase(title)) {
    			albumsByTitle.add(album1);
    		} 
    	}
    	return albumsByTitle;
    }
    
    // getAlbumsByArtist returns a list of albums with a specified artist
    public List<Album> getAlbumsByArtist(String artist) {
    	// check if for each album in the albums list, one of them matches the 
    	// desired artist
    	List<Album> albumsByArtist = new ArrayList<Album>();
    	for (Album album1 : albums) {
    		// ensure case insensitivity
    		if (album1.getArtist().equalsIgnoreCase(artist)) {
    			albumsByArtist.add(album1);
    		}
    	} return albumsByArtist;
    }
    
    // getPlayList returns a list of songs within a playlist
    public List<Song> getPlayList(PlayList playList) {
    	List<Song> plSongs = new ArrayList<Song>();
    	for (PlayList playList1: playLists) {
    		// check each playlist in playlists 
    		if (playList1.getName().equals(playList.getName())) {
    			// if name matches return a copy list of songs 
    			// inside of the playlist
    			for (Song song : playList.getSongs()) {
    				plSongs.add(song);
    			}
    			return plSongs;
    		}
    	} return null;
    }
    
    // setRecents sets the recents playlist to a new playlist
	public void setRecents(PlayList recents) {
		this.recents = new PlayList(recents);
	}
    
	// setFrequents sets the frequents playlist to a new playlist
	public void setFrequents(PlayList frequents) {
		this.frequents = new PlayList(frequents);
	}
    
	// setFavorites sets the favorites playlist to a new playlist
	public void setFavoritesPlayList(PlayList favoritesPlayList) {
		this.favoritesPlayList = new PlayList(favoritesPlayList);
	} 
	
	
	
}
