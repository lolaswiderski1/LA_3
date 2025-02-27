package Model;

public final class Song {
	private String songTitle;
	private String artist;
	private String albumTitle;
	
	public Song(String songTitle, String artist, String albumTitle) {
		this.songTitle = songTitle;
		this.artist = artist;	
		this.albumTitle = albumTitle;
	}
	
	public String getSongTitle() {
		return songTitle;
	}
	
	public String getArtist() {
		return artist;
	}
	
	public String getAlbumTitle() {
		return albumTitle;
	}
	
	public String toString() {
		return songTitle + ", " + artist;
	}
}
