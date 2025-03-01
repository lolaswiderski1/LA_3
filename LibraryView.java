
package Model;

import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

import Model.LibraryModel.Rating;

public class View {
	
	    private static MusicStore musicStore = new MusicStore();
	    private static Scanner scanner = new Scanner(System.in);
	    private static LibraryModel lib = new LibraryModel(); 
	    
		public static void main(String[] args) {
			home();
		}
		
		// main display page for library
		private static void home() {
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
		
		// switch case statement for main choices
		private static void mainChoice(int choice) {
			switch (choice) {
				case 0:
					musicStoreOptions();
					break;
				case 1:
					songByTitle();
					break;
				case 2:	
					songByArtist();
					break;
				case 3:	
					albumByTitle();
					break;
				case 4:	
					albumByArtist();
					break;
				case 5:	
					searchForPlayListByName();
					break;
				case 6:	
					displaySongsInLib();
					break;
				case 7:	
					displayArtistsInLib();
					break;
				case 8:	
					displayAlbumsInLib();
					break;
				case 9:	
					displayPlayLists();
					break;
				case 10:	
					displayFavorites();
					break;
				case 11:	
					createPlayList();
					break;
				case 12:	
					endProgram();
					break;
				default:
					System.out.println("Invalid input");
					home();
					return;
			}
		}
		
		// user enters music store
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
					addSongToLibrary(songsByTitle);
					home();
					break;
			
				case 2:		// song by artist
					System.out.println("Enter artist name: ");
					String artistS = scanner.nextLine().toLowerCase();
					List<Song> songsByArtist = musicStore.getSongsByArtist(artistS);
					addSongToLibrary(songsByArtist);
					home();
					break;
					
				case 3:		// album by title
					System.out.println("Enter album title: ");
					String titleA = scanner.nextLine().toLowerCase();
					List<Album> albumsByTitle = musicStore.getAlbumsByTitle(titleA);
					addAlbumToLibrary(albumsByTitle);
					home();
					break;
				
				case 4:		// albums by artist
					System.out.println("Enter album artist: ");
					String artistA = scanner.nextLine().toLowerCase();
					List<Album> albumsByArtist = musicStore.getAlbumsByArtist(artistA);
					addAlbumToLibrary(albumsByArtist);
					home();
					break;
			}
		}
		
		// Takes list of songs. User selects a song and it is added to library.
		private static void addSongToLibrary(List<Song> songs) {
			displaySongs(songs);
			
			System.out.println("Select song to add: ");
			int choice = scanner.nextInt();
			Song selectedSong = songs.get(scanner.nextInt());
			if (lib.hasSong(selectedSong)) {
				System.out.println("Song already exists in library. ");	
			}
			
			lib.addSong(selectedSong);
			System.out.println(selectedSong + " has been added to library. \n");
			home();
		}
	    
		// displays a list of songs with their indexes
		private static void displaySongs(List<Song> songs) {
			for (int i = 0; i < songs.size(); i++) {
				System.out.println("[" + i + "] " + songs.get(i) + ", " + songs.get(i).getAlbumTitle());
			}
		}
		
		// displays a list of albums and each song in each one
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
		
		// Takes list of albums. User selects a album and it is added to library.
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
		
		// displays a playlist and each song in it
		private static void displayPlayList(PlayList playList) {
			System.out.println("Selected playlist: " + playList.getName());
			System.out.println("     Songs in " + playList.getName() + ":");
			for (Song song : playList.getSongs()) {
				System.out.println("     " + song.getSongTitle());
			}
		}
		
		// user enters name and associated playlist is selected
		private static void searchForPlayListByName() {
			System.out.println("Enter name of playlist: ");
			String name = scanner.nextLine();
			for (PlayList playList : lib.getPlayLists()) {
				if (playList.getName().equalsIgnoreCase(name)) {
					playListChoices(playList);
				}
			}
			System.out.println("Playlist not found. ");
			home();
		}
		
		// Shows list of playlists and lets user select one.
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
		
		// allows user to add/remove a song from playlist
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
		
		// user selects a song in library to add to playlist
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
		
		// user selects a song in playlist to remove
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
		
		// creates playlist with entered name
		private static void createPlayList() {
			System.out.println("Enter name of playlist: ");
			String name = scanner.nextLine();
			PlayList newPL = new PlayList(name);
			lib.addPlaylist(newPL);
			home();
		}
		
		// options for what to do with song
		private static int songOptions(Song song) {
			System.out.println("[0] - rate song");
			System.out.println("[1] - favorite song");
			int option = scanner.nextInt();
			return option;
		}
		
		// displays songs with matching title to user input
		private static void songByTitle() {
			System.out.println("Enter song title: \n");
			String title = scanner.nextLine();
			List<Song> songsList = lib.getSongsByTitle(title);
			System.out.println(lib.getSongTitles().size());
			displaySongs(songsList);
			selectSong(songsList);
		}
			
		// user rates song
		public static Rating rateSong(String songTitle) {
			System.out.println("Enter song rating 1-5: ");
			Rating setRating = null;
			int rating = scanner.nextInt();
			switch (rating) {
			case 1:
				setRating = setRating.ONE;
				System.out.println(songTitle + " has been rated a " + rating);
				home();
				break;
			
			case 2:
				setRating = setRating.TWO;
				System.out.println(songTitle + " has been rated a " + rating);
				home();
				break;
			case 3:
				setRating = setRating.THREE;
				System.out.println(songTitle + " has been rated a " + rating);
				home();
				break;
			case 4:
				setRating = setRating.FOUR;
				System.out.println(songTitle + " has been rated a " + rating);
				home();
				break;
			case 5:
				setRating = setRating.FIVE;
				System.out.println(songTitle + " has been rated a " + rating);
				System.out.println(songTitle + " has been added to favorites!");
				home();
				break;
			}
			return setRating;
		}
		
		
		private static void selectSong(List<Song> songsList) {
			if (lib.getSongTitles().size() == 0) {
				System.out.println("Library is empty.");
				home();
				return;
			}
			System.out.println("Select song from library: ");
			Song selectedSong = songsList.get(scanner.nextInt());
			switch(songOptions(selectedSong)) {
				case 0:
					lib.rateSong(selectedSong, rateSong(selectedSong.getSongTitle()));
					break;
				case 1:
					lib.addFavorite(selectedSong);
					System.out.println(selectedSong.getSongTitle() +" has been added to favorites!");
					home();
			}
		}
		private static void songByArtist() {
			System.out.println("Enter song artist: \n");
			String artist = scanner.nextLine();
			List<Song> songsList = lib.getSongsByTitle(artist);
			displaySongs(songsList);
			selectSong(songsList);

		}
		
		private static void albumByArtist() {
			System.out.println("Enter album artist: \n");
			String artist = scanner.nextLine();
			List<Album> songsList = lib.getAlbumsByArtist(artist);
			displayAlbums(songsList);
			home();
		}
		
		private static void albumByTitle() {
			System.out.println("Enter album title: \n");
			String title = scanner.nextLine();
			List<Album> songsList = lib.getAlbumsByTitle(title);
			displayAlbums(songsList);
			home();
		}
		private static void displaySongsInLib() {
		    int i = 0;
		    for (Song song : lib.getAllSongs()) {
		        System.out.println("[" + i + "] " + song.getSongTitle() + ", " + song.getArtist());
		        i++;
		    }
		    home();
		}
		
		private static void displayArtistsInLib() {
		    // Use HashSet to store unique artists
		    Set<String> artists = new HashSet<>(lib.getSongArtists());
		    
		    int index = 0;
		    for (String artist : artists) {
		        System.out.println("[" + index + "] " + artist);
		        index++;
		    }

		    home();
		}
		
		public static void displayAlbumsInLib() {
			for (int i = 0; i < lib.getAlbums().size();i++) {
				System.out.println("["+ i + "]" + " " + lib.getAlbums().get(i).getTitle() + ", " +
			lib.getAlbums().get(i).getArtist() + ", " + lib.getAlbums().get(i).getGenre() + ", " +
						lib.getAlbums().get(i).getYear());
			}
			home();
		}
		
		public static void displayFavorites() {
			System.out.println("Favorite songs:");
			List<Song> songsList = lib.getFavorites();
			displaySongs(songsList);
			home();
		}
		
		public static void endProgram() {
			System.exit(0);
		}
	}
