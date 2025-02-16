package Model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MusicStore {
	private Map<String, Album> albumsByTitle = new HashMap<>(); // title, Album
	private Map<String, List<Album>> albumsByArtist = new HashMap<>(); // artist, Albums by artist
	
	public MusicStore() {
		initializeAlbumsLists();
	}
	
	// Iterates through albums.txt and gets each album's filename. Passes
	// the filename into addAlbumToAlbumsMaps() which creates an Album
	// object and adds it to the Album HashMaps.
	private void initializeAlbumsLists() {
		List<String> albumFilenames = new ArrayList<>();
		try (BufferedReader br = new BufferedReader(new FileReader("albums/albums.txt"))) {
            String currFilename;
            while ((currFilename = br.readLine()) != null) { // Read line-by-line
            	currFilename = currFilename.replace(',', '_');
            	currFilename += ".txt";
            	addAlbumToAlbumsMaps(currFilename);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
	}
	
	// Creates new Album object using information from given file and
	// adds the Album to albumsByTitle and albumsByArtist 
	private void addAlbumToAlbumsMaps(String albumFilename) {
		try (BufferedReader br = new BufferedReader(new FileReader("albums/" + albumFilename))) {
			// first line of file contains constructor information
            String[] firstLine = br.readLine().split(",");
            String title = firstLine[0];
            String artist = firstLine[1];
            String genre = firstLine[2];
            String year = firstLine[3];
            Album newAlbum = new Album(title, artist, genre, year);
            
            // rest of lines contains song titles to add to newAlbum
            String currSongTitle;
            while ((currSongTitle = br.readLine()) != null) { // Read line-by-line
            	newAlbum.addSong(new Song(currSongTitle, artist));
            }
            
            albumsByTitle.put(title, newAlbum);
            
            if (albumsByArtist.containsKey(artist)) {
            	albumsByArtist.get(artist).add(newAlbum);
            }
            else {
            	List<Album> albums = new ArrayList<>();
            	albums.add(newAlbum);
            	albumsByArtist.put(artist, albums);
            }  
        } catch (IOException e) {
            e.printStackTrace();
        }
	}
	
	// Iterates through all Albums until the given Song title is found
	// in one of them. The associated Song is returned.
	public Song getSongByTitle(String title) {
		for (Album album : albumsByTitle.values()) {
			if (album.hasSong(title)) {
				return album.getSongByTitle(title);
			}
		}
		// TODO: song not found
		return null;
	}
	
	// Returns list of all Songs by given artist
	public List<Song> getSongsByArtist(String artist) { 
		List<Song> songsByArtist = new ArrayList<>();
		// iterates through list of Albums with given artist
		for (Album album : albumsByArtist.get(artist)) {
			// adds every Song in album to songsByArtist
			songsByArtist.addAll(album.getAllSongs());
		}
		return songsByArtist;
	}
	
	public Album getAlbumByTitle(String title) {
		return new Album(albumsByTitle.get(title)); 
	}
	
	public List<Album> getAlbumsByArtist(String artist) {
		return new ArrayList<>(albumsByArtist.get(artist)); 
	}
}
