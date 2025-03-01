package LA1;

import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

import LA1.LibraryModel.Rating;

public class LibraryView {
	
	    private static MusicStore musicStore = new MusicStore();
	    private static Scanner scanner = new Scanner(System.in);
	    private static LibraryModel lib = new LibraryModel(); 
	    
		public static void main(String[] args) {
			home();
		}
		
		public static void home() {
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
			System.out.println("[12] - add or remove songs from a playlist");
			System.out.println("[13] - 	QUIT PROGRAM");
			
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

					break;
				case 10:	
					displayFavorites();
					break;
				case 11:	

					break;
				case 12:	

					break;
				case 13:	

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
				default:
					System.out.println("Invalid input: " + choice + "\n");
					home();
					break;
			}
		}
		
		private static void addSongToLibrary(List<Song> songs) {
			displaySongs(songs);
			
			System.out.println("Select song to add: ");
			int selectIndex = scanner.nextInt();
			scanner.nextLine();
			Song selectedSong = songs.get(selectIndex);
			if (lib.hasSong(selectedSong)) {
				System.out.println("Song already exists in library. ");	
				home();
				return;
			}
			
			lib.addSong(selectedSong);
			System.out.println(selectedSong + " has been added to library. \n");
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
			int selectedAlbumIndex = scanner.nextInt();
			Album selectedAlbum = albums.get(selectedAlbumIndex);
			if (selectedAlbumIndex > albums.size() || selectedAlbumIndex < 0) {
				System.out.println("Inavlid input: " + selectedAlbumIndex + "\n");
				home();
				return;
			}
			if (lib.hasAlbum(selectedAlbum)) {
				System.out.println("Album already exists in library. ");	
			}
			
			lib.addAlbum(selectedAlbum);
			System.out.println(selectedAlbum.getTitle() + " has been added to library. \n");
		}
		
		private static int songOptions(Song song) {
			System.out.println("[0] - home");
			System.out.println("[1] - rate song");
			System.out.println("[2] - favorite song");
			int option = scanner.nextInt();
			scanner.nextLine();
			if (option < 0 || option > 2) {
				System.out.println("Invalid input: " + option + "\n");
				home();
			}
			return option;
		}
		
		private static void songByTitle() {
			System.out.println("Enter song title: \n");
			String title = scanner.nextLine();
			List<Song> songsList = lib.getSongsByTitle(title);
			if (!lib.hasSongByTitle(title)) {
				System.out.println("Can not find song in library.");
				home();
				return;
			}
			libIsEmpty();
			displaySongs(songsList);
			selectSong(songsList);
		}
		
		private static void libIsEmpty() {
			if (lib.getSongTitles().size() == 0) {
				System.out.println("Library is empty.");
				home();
				return;
			}
		}
			
		public static Rating rateSong(Song song) {
			System.out.println("Enter song rating 1-5: ");
			Rating setRating = null;
			String songTitle = song.getSongTitle();
			int rating = scanner.nextInt();
			scanner.nextLine();
			if (rating < 0 || rating > 5) {
				System.out.println("Invalid input: " + rating + "\n");
				home();
			}
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
				lib.addFavorite(song);
				System.out.println(songTitle + " has been rated a " + rating);
				System.out.println(songTitle + " has been added to favorites!");
				home();
				break;
			}
			return setRating;
	}
		
		private static void selectSong(List<Song> songsList) {
			libIsEmpty();
			System.out.println("Select song from library: ");
			int selectSongIndex = scanner.nextInt();
			scanner.nextLine();
			Song selectedSong = songsList.get(selectSongIndex);
			if (!lib.getAllSongs().contains(selectedSong)) {
			    System.out.println("Song not found in the library.");
			    home();
			    return;
			}
			switch(songOptions(selectedSong)) {
				case 0:
					home();
				case 1:
					lib.rateSong(selectedSong, rateSong(selectedSong));
					break;
				case 2:
					lib.addFavorite(selectedSong);
					System.out.println(selectedSong.getSongTitle() +" has been added to favorites!");
					home();
			}
		}
		
		private static void songByArtist() {
			libIsEmpty();
			System.out.println("Enter song artist: \n");
			String artist = scanner.nextLine();
			List<Song> songsList = lib.getSongsByArtist(artist);
			libIsEmpty();
			if (!lib.hasSongByArtist(artist)) {
				System.out.println("Can not find artist in library.");
				home();
				return;
			}
			displaySongs(songsList);
			selectSong(songsList);

		}
		
		private static void albumByArtist() {
			System.out.println("Enter album artist: \n");
			String artist = scanner.nextLine();
			libIsEmpty();
			List<Album> songsList = lib.getAlbumsByArtist(artist);
			displayAlbums(songsList);
			home();
		}
		
		private static void albumByTitle() {
			System.out.println("Enter album title: \n");
			String title = scanner.nextLine();
			libIsEmpty();
			List<Album> songsList = lib.getAlbumsByTitle(title);
			displayAlbums(songsList);
			home();
		}
		
		private static void displaySongsInLib() {
			libIsEmpty();
		    int i = 0;
		    for (Song song : lib.getAllSongs()) {
		        System.out.println("[" + i + "] " + song.getSongTitle() + ", " + song.getArtist());
		        i++;
		    }
		    home();
		}
		
		private static void displayArtistsInLib() {
			libIsEmpty();
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
			libIsEmpty();
			for (int i = 0; i < lib.getAlbums().size();i++) {
				System.out.println("["+ i + "]" + " " + lib.getAlbums().get(i).getTitle() + ", " +
			lib.getAlbums().get(i).getArtist() + ", " + lib.getAlbums().get(i).getGenre() + ", " +
						lib.getAlbums().get(i).getYear());
			}
			home();
		}
		
		public static void displayFavorites() {
			libIsEmpty();
			System.out.println("Favorite songs:");
			List<Song> songsList = lib.getFavorites();
			displaySongs(songsList);
			home();
		}
	}
