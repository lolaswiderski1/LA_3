// Sam Hershey, Lola Swiderski
// class to model a song object
package dataStructures;

import java.util.Comparator;

// songs are immutable
public final class Song implements Comparable<Song>{
	// instantiate title, artist, and album title
	private String songTitle;
	private String artist;
	private String albumTitle;
	private String genre;
	
	// initialize variables
	public Song(String songTitle, String artist, String albumTitle, String genre) {
		this.songTitle = songTitle;
		this.artist = artist;	
		this.albumTitle = albumTitle;
		this.genre = genre;
	}
	
	// get song title
	public String getSongTitle() {
		return songTitle;
	}
	
	// get song artist
	public String getArtist() {
		return artist;
	}
	
	// get album title
	public String getAlbumTitle() {
		return albumTitle;
	}
	
	// print song
	public String toString() {
		return songTitle + ", " + artist;
	}
	
	public String getGenre() {
		return genre;
		
	}
	
	// Comparator for sorting by title
    public static Comparator<Song> TitleComparator = new Comparator<Song>() {
        @Override
        public int compare(Song song, Song other) {
            return song.getSongTitle().compareTo(other.getSongTitle());
        }
    };

    // Comparator for sorting by artist
    public static Comparator<Song> ArtistComparator = new Comparator<Song>() {
        @Override
        public int compare(Song song, Song other) {
            return song.getArtist().compareTo(other.getArtist());
        }
    };

	@Override
	public int compareTo(Song o) {
		// TODO Auto-generated method stub
		return 0;
	}
}

