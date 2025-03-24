
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
	
	// Default constructor
    public Song() { }
	
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
		return songTitle + ", " + artist + ", " + albumTitle + ", " + genre;
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
	
	@Override
	public boolean equals(Object obj) {
	    if (this == obj) return true; 
	    if (obj == null || getClass() != obj.getClass()) {
	    	return false; 
	    }

	    Song song = (Song) obj; // Cast to Song

	    // Compare all relevant fields
	    return songTitle.equals(song.songTitle) &&
	           artist.equals(song.artist) &&
	           albumTitle.equals(song.albumTitle) &&
	           genre.equals(song.genre);
	}

	@Override
	public int hashCode() {
	    // Use a combination of the fields to generate a hash code
	    int result = songTitle.hashCode();
	    result = 31 * result + artist.hashCode();
	    result = 31 * result + albumTitle.hashCode();
	    result = 31 * result + genre.hashCode();
	    return result;
	}
}
