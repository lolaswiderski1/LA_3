package Model;

import java.util.List;
import java.util.Scanner;

public class View {
	
	    private static MusicStore musicStore = new MusicStore();
	    private static Scanner scanner = new Scanner(System.in);
	    private static LibraryModel lib = new LibraryModel(); 
	    
		public static void main(String[] args) {
			home();
		}
		
		private static void home() {
			//System.out.println("----------------------------------------------------------");
			System.out.println("\nWelcome to your personal music library!\n");
			
			// give initial options
			System.out.println("[0] - add songs or albums from the music store");
			System.out.println("[1] - search for song by title");
			System.out.println("[2] - search for song by artist");
			System.out.println("[3] - search for album by title");
			System.out.println("[4] - search for album by artist");
			System.out.println("[5] - search for playlist by name");
			System.out.println("[6] - display a list of all songs in your library");
			System.out.println("[7] - display a list of all artists in your library");
			System.out.println("[8] - display a list of all albums in your library");
			System.out.println("[9] - display a list of all playlists in your library");
			System.out.println("[10] - display a list of all favorite songs");
			System.out.println("[11] - create a playlist");
			System.out.println("[12] - 	QUIT PROGRAM");
			
			// select choice
			int choice = scanner.nextInt();
			scanner.nextLine();
			mainChoice(choice);
		}
		
		private static void mainChoice(int choice) {
			switch (choice) {
				case 0:
					musicStoreOptions();
					break;
				case 1:
					
					break;
				case 2:	

					break;
				case 3:	

					break;
				case 4:	

					break;
				case 5:	
					searchForPlayListByName();
					break;
				case 6:	

					break;
				case 7:	

					break;
				case 8:	

					break;
				case 9:	
					displayPlayLists();
					break;
				case 10:	

					break;
				case 11:	
					createPlayList();
					break;
				case 12:	

					break;
			}
		}
		
		private static void musicStoreOptions() {
			System.out.println("[0] - return to library");
			System.out.println("[1] - search for song by title");
			System.out.println("[2] - search for song by artist");
			System.out.println("[3] - search for album by title");
			System.out.println("[4] - search for album by artist");
			
			// select choice
			int choice = scanner.nextInt();
			scanner.nextLine();
		
			switch (choice) {
				 
				case 0:
					home();
					
				case 1: 	// song by title
					System.out.println("Enter song title: ");
					String titleS = scanner.nextLine().toLowerCase();
					System.out.println(titleS);
					List<Song> songsByTitle = musicStore.getSongsByTitle(titleS);
					//System.out.println(songsByTitle.size());
					addSongToLibrary(songsByTitle);
					break;
			
				
				case 2:		// song by artist
					System.out.println("Enter artist name: ");
					String artistS = scanner.nextLine().toLowerCase();
					List<Song> songsByArtist = musicStore.getSongsByArtist(artistS);
					addSongToLibrary(songsByArtist);
					break;
					
				case 3:		// album by title
					System.out.println("Enter album title: ");
					String titleA = scanner.nextLine().toLowerCase();
					List<Album> albumsByTitle = musicStore.getAlbumsByTitle(titleA);
					addAlbumToLibrary(albumsByTitle);
					break;
				
				case 4:		// albums by artist
					System.out.println("Enter album artist: ");
					String artistA = scanner.nextLine().toLowerCase();
					List<Album> albumsByArtist = musicStore.getAlbumsByArtist(artistA);
					addAlbumToLibrary(albumsByArtist);
					break;
			}
		}
		
		private static void addSongToLibrary(List<Song> songs) {
			displaySongs(songs);
			
			System.out.println("Select song to add: ");
			Song selectedSong = songs.get(scanner.nextInt());
			if (lib.hasSong(selectedSong)) {
				System.out.println("Song already exists in library. ");	
			}
			
			lib.addSong(selectedSong);
			System.out.println(selectedSong + " has been added to library. \n");
			home();
		}
	    
		private static void displaySongs(List<Song> songs) {
			for (int i = 0; i < songs.size(); i++) {
				System.out.println("[" + i + "] " + songs.get(i) + ", " + songs.get(i).getAlbumTitle());
			}
		}
		
		private static void displayAlbums(List<Album> albums) {			
			for (int i = 0; i < albums.size(); i++) {
				System.out.println("[" + i + "] " + albums.get(i).getTitle()
						+ ",  " + albums.get(i).getArtist()
						+ ", " + albums.get(i).getGenre()
						+ ", " + albums.get(i).getYear());
				System.out.println("     Songs:");
				for (Song song : albums.get(i).getAllSongs()) {
					System.out.println("     " + song.getSongTitle());
				}
			}
		}
		
		private static void addAlbumToLibrary(List<Album> albums) {
			displayAlbums(albums);
			
			System.out.println("Select album to add: ");
			Album selectedAlbum = albums.get(scanner.nextInt());
			if (lib.hasAlbum(selectedAlbum)) {
				System.out.println("Album already exists in library. ");	
			}
			
			lib.addAlbum(selectedAlbum);
			System.out.println(selectedAlbum.getTitle() + " has been added to library. \n");
			home();
		}
		
		private static void displayPlayList(PlayList playList) {
			System.out.println("Selected playlist: " + playList.getName());
			System.out.println("     Songs in " + playList.getName() + ":");
			for (Song song : playList.getSongs()) {
				System.out.println("     " + song.getSongTitle());
			}
		}
		
		// task 5
		private static void searchForPlayListByName() {
			System.out.println("Enter name of playlist: ");
			String name = scanner.nextLine();
			for (PlayList playList : lib.getPlayLists()) {
				if (playList.getName().equalsIgnoreCase(name)) {
					playListChoices(playList);
				}
			}
		}
		
		// task 9
		private static void displayPlayLists() {
			List<PlayList> playLists = lib.getPlayLists();
			for (int i = 0; i < playLists.size(); i++) {
				System.out.println("[" + i + "] " + playLists.get(i).getName()); 
			}
			System.out.println("[" + playLists.size() + "] " + "Return Home");
			
			System.out.println("Select playlist.");
			int choice = scanner.nextInt();
			if (choice == playLists.size()) {
				home();
				return;
			}
			
			PlayList selectedPlayList = playLists.get(choice);
			playListChoices(selectedPlayList);
		}
		
		private static void playListChoices(PlayList playList) {
			displayPlayList(playList);
			System.out.println("[0] - add a song");
			System.out.println("[1] - remove a song");
			System.out.println("[2] - Return Home");
			
			int choice = scanner.nextInt();
			switch(choice) {
				case 0:
					addSongToPlayList(playList);
					break;
				case 1:
					removeSongFromPlayList(playList);
					break;
				case 2:
					home();
			}
		}
		
		private static void addSongToPlayList(PlayList playList) {
			List<Song> allSongs = lib.getAllSongs();
			displaySongs(allSongs);
			System.out.println("Select song to add: ");
			int choice = scanner.nextInt();
			Song selectedSong = allSongs.get(choice);
			if (playList.hasSong(selectedSong)) {
				System.out.println("Song already exists in playlist");
				home();
			}
			playList.addSong(selectedSong);
			System.out.println(selectedSong + "has been added to " + playList.getName() + "\n");
			home();
		}
		
		private static void removeSongFromPlayList(PlayList playList) {
			List<Song> songsInPlayList = playList.getSongs();
			displaySongs(songsInPlayList);
			System.out.println("Select song to remove: ");
			int choice = scanner.nextInt();
			Song selectedSong = songsInPlayList.get(choice);
			playList.removeSong(selectedSong);
			System.out.println(selectedSong + "has been removed from " + playList.getName() + "\n");
			home();
		}
		
		// task 11 
		private static void createPlayList() {
			System.out.println("Enter name of playlist: ");
			String name = scanner.nextLine();
			PlayList newPL = new PlayList(name);
			lib.addPlaylist(newPL);
			home();
		}
		
		
		
}
