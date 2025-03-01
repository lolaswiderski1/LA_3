package Model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MusicStore {
	private List<Album> albums = new ArrayList<>();
	
	public MusicStore() {
		initializeAlbumsList();
	}
	
	// Iterates through albums.txt and gets each album's filename. Passes
	// the filename into addAlbumToAlbumsList() which creates an Album
	// object and adds it to the albums list.
	private void initializeAlbumsList() {
		List<String> albumFilenames = new ArrayList<>();
		try (BufferedReader br = new BufferedReader(new FileReader("albums/albums.txt"))) {
			// read file line by line
			String currFilename;
            while ((currFilename = br.readLine()) != null) {
            	currFilename = currFilename.replace(',', '_');
            	currFilename += ".txt";
            	addAlbumToAlbumsList(currFilename);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
	}
	
	// Creates new Album object using information from given file and
	// adds the Album to albums
	private void addAlbumToAlbumsList(String albumFilename) {
		try (BufferedReader br = new BufferedReader(new FileReader("albums/" + albumFilename))) {
			// first line of file contains constructor information
            String[] firstLine = br.readLine().split(",");
            String albumTitle = firstLine[0];
            String artist = firstLine[1];
            String genre = firstLine[2];
            String year = firstLine[3];
            Album newAlbum = new Album(albumTitle, artist, genre, year);
            
            // rest of lines contains song titles to add to newAlbum
            String currSongTitle;
            while ((currSongTitle = br.readLine()) != null) {
            	newAlbum.addSong(new Song(currSongTitle, artist, albumTitle));
            }
            
            albums.add(newAlbum);
            
        } catch (IOException e) {
            e.printStackTrace();
        }
	}
	
	// Returns list of all Songs with the given title
	public List<Song> getSongsByTitle(String title) {
		List<Song> songsByTitle = new ArrayList<>();
		// iterate through all albums to find Songs with title
		for (Album album : albums) {
			if (album.hasSong(title)) {
				songsByTitle.add(album.getSongByTitle(title));
				
			}
		}
		return songsByTitle;
	}
	
	// Returns list of all Songs by given artist
	public List<Song> getSongsByArtist(String artist) { 
		List<Song> songsByArtist = new ArrayList<>();
		// iterate through all albums to find Albums with artist
		for (Album album : albums) {
			if (album.getArtist().equalsIgnoreCase(artist)) {
				// adds every Song in album to songsByArtist
				songsByArtist.addAll(album.getAllSongs());
			}
		}
		return songsByArtist; 
	}
	
	// Returns the Album with the given title
	public List<Album>  getAlbumsByTitle(String title) {
		List<Album> albumsByTitle = new ArrayList<Album>();
		 for (Album album : albums) {
			 if (album.getTitle().equalsIgnoreCase(title)) {
				 albumsByTitle.add(new Album(album));
			 }
		 }
		 return albumsByTitle;
	}
	
	// Returns list of all Albums with the given artist
	public List<Album> getAlbumsByArtist(String artist) {
		List<Album> albumsByArtist = new ArrayList<>();
		// iterate through all albums to find Albums with title
		for (Album album : albums) {
			if (album.getArtist().equalsIgnoreCase(artist)) {
				albumsByArtist.add(new Album(album));
			}
		}
		return albumsByArtist;
	}
	
	// returns list of all Albums
	public List<Album> getAlbums() {
		List<Album> albumsCopy = new ArrayList<>(); // deep copy of albums
		for (Album album : albums) {
			albumsCopy.add(new Album(album));
		}
		return albumsCopy;
	}
}
