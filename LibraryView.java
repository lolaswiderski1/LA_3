package Music;

import java.util.List;
import java.util.Scanner;

public class LibraryView {
	
	    private static MusicStore musicStore = new MusicStore();
	    private static Scanner scanner = new Scanner(System.in);
	    private static LibraryModel lib = new LibraryModel(); 
	    
		public static void main(String[] args) {
			
			// give initial options
			System.out.println("[0] - search for song by title");
			System.out.println("[1] - search for song by artist");
			System.out.println("[2] - search for album by title");
			System.out.println("[3] - search for album by artist");
			// select choice
			
			int choice = scanner.nextInt();
			scanner.nextLine();
		
			switch (choice) {
				 
				case 0: 	// song by title
					System.out.println("Enter song title: ");
					String titleS = scanner.nextLine().toLowerCase();
					List<Song> songsByTitle = musicStore.getSongsByTitle(titleS);
					displaySongs(songsByTitle);
					System.out.println("Select song to add: ");
					Song selectedSongT = songsByTitle.get(scanner.nextInt());
					if (lib.hasSong(selectedSongT)) {
						System.out.println("Song already exists in library. ");	
					}
					
					lib.addSong(selectedSongT);
					System.out.println(selectedSongT.getSongTitle() + " has been added to library. \n");
					break;
			
				
				case 1:		// song by artist
					System.out.println("Enter artist name: ");
					String artistS = scanner.nextLine().toLowerCase();
					List<Song> songsByArtist = musicStore.getSongsByArtist(artistS);
					displaySongs(songsByArtist);
					System.out.println("Select song to add: ");
					Song selectedSongA = songsByArtist.get(scanner.nextInt());
					if (lib.hasSong(selectedSongA)) {
						System.out.println("Song already exists in library. ");	
					}
					
					lib.addSong(selectedSongA);
					System.out.println(selectedSongA.getSongTitle() + " has been added to library. \n");
					break;
					
				case 2:		// album by title
					System.out.println("Enter album title: ");
					String titleA = scanner.nextLine().toLowerCase();
					List<Album> albumsByTitle = musicStore.getAlbumByTitle(titleA);
					displayAlbums(albumsByTitle);
					System.out.println("Select album to add: ");
					Album selectedAlbumT = albumsByTitle.get(scanner.nextInt());
					if (lib.hasAlbum(selectedAlbumT)) {
						System.out.println("Album already exists in library. ");	
					}
					
					lib.addAlbum(selectedAlbumT);
					System.out.println(selectedAlbumT.getTitle() + " has been added to library. \n");
					break;
				
				case 3:		// albums by artist
					System.out.println("Enter album artist: ");
					String artistA = scanner.nextLine().toLowerCase();
					List<Album> albumsByArtist = musicStore.getAlbumsByArtist(artistA);
					displayAlbums(albumsByArtist);
					System.out.println("Select album to add: ");
					Album selectedAlbumA = albumsByArtist.get(scanner.nextInt());
					if (lib.hasAlbum(selectedAlbumA)) {
						System.out.println("Album already exists in library. ");	
					}
					
					lib.addAlbum(selectedAlbumA);
					System.out.println(selectedAlbumA.getTitle() + " has been added to library. \n");
					break;
			}
		}
			
		public static void displaySongs(List<Song> songs) {
			for (int i = 0; i < songs.size(); i++) {
				System.out.println("[" + i + "] " + songs.get(i).getSongTitle()  + " " + songs.get(i).getArtist());
			}
		}
		
		public static void displayAlbums(List<Album> albums) {
			for (int i = 0; i < albums.size(); i++) {
				System.out.println("[" + i + "] " + albums.get(i).getTitle()  + " " + albums.get(i).getArtist());
			}
		}
		
}
