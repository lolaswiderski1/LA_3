// Sam Hershey, Lola Swiderski
// class to model a song object

package LA1;

// songs are immutable
public final class Song {
	// instantiate title, artist, and album title
	private String songTitle;
	private String artist;
	private String albumTitle;
	
	// initialize variables
	public Song(String songTitle, String artist, String albumTitle) {
		this.songTitle = songTitle;
		this.artist = artist;	
		this.albumTitle = albumTitle;
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
}
