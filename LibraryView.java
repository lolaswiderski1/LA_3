// Authors:
// Descriptionclass for the user to use the music library 
package view;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

import model.LibraryModel.Rating;
import dataStructures.Album;
import dataStructures.PlayList;
import dataStructures.Song;
import model.LibraryModel;
import model.MusicStore;
import userManagement.AccountsManager;
public class LibraryView { 
	// instantiate a music store, a scanner for the user, and a music library
	    protected static MusicStore musicStore = new MusicStore("albums");
	    
	    private static Scanner scanner = new Scanner(System.in);
	    private static LibraryModel lib = new LibraryModel(); 	
	    private static SongsView songsView = new SongsView(lib);
	    private static PlayListView playListView = new PlayListView(lib);
	    private static AlbumView albumView = new AlbumView(lib);
	    private static AccountsManager accountsManager = new AccountsManager("data");
	    private static String username;
	    
	    public static void main(String[] args) {
	    	logInMainDisplay();
		} 
	     
	    
	    private static void logInMainDisplay() {
	        System.out.println("\nLog in or create an account.\n");
	        System.out.println("[0] - Create a new account");
	        System.out.println("[1] - Log in");
	        // keep asking until a valid input is given
	        while (true) {  
	            if (scanner.hasNextInt()) {
	                int choice = scanner.nextInt();
	                scanner.nextLine(); 

	                if (choice == 0 || choice == 1) {
	                    logInOrCreateAccount(choice);
	                    // exit the loop after a valid choice
	                    break; 
	                } else {
	                    System.out.println("Invalid input");
	                }
	            } else {
	                System.out.println("Invalid input");
	                // discard invalid input
	                scanner.next(); 
	            }
	        }
	    }

	    private static void logInOrCreateAccount(int choice) {
	    	switch (choice) {
	    	case 0:
	    		createAccount();
	    		break;
	    	case 1:
	    		logIn();
	    		break;
	    	default:
	    		System.out.println("Invalid input." + choice);
	    		logInMainDisplay();
	    	}
	    }
	    
	    private static void createAccount() {
	    	System.out.println("\nCreate a new account.");
	    	System.out.println("Enter username:");
	    	String username = scanner.nextLine();
	    	if (accountsManager.userExists(username)) {
	    		System.out.println("Username already exists.");
	    		logInMainDisplay();
	    	} else {
	    		LibraryView.username = username;
	    		System.out.println("Enter password:");
	    		String password = scanner.nextLine();
	    		accountsManager.createAccount(username, password);
	    	}
	    	mainHome();
	    }
	    
	    private static void logIn() {
	    	System.out.println("\nLog in.");
	    	System.out.println("Enter username:");
	    	String username = scanner.nextLine();
	    	if (!accountsManager.userExists(username)) {
	    		System.out.println("Username not found.");
	    		logInMainDisplay();
	    	} else {
	    		LibraryView.username = username;
	    		System.out.println("Enter password: ");
	    		String password = scanner.nextLine();
	    		if (!accountsManager.validatePassword(username, password)) {
	    			System.out.println("Invalid password.");
	    			logInMainDisplay();
	    		} else {
	    			// set lib to retrieved data
	    			LibraryView.lib = accountsManager.getUserData(username);
	    			
	    		}
	    	}
	    	//updateAccount();
	    	mainHome(); 
	    }
		
		public static void mainHome() {
			songsView = new SongsView(lib);
		    playListView = new PlayListView(lib);
		    albumView = new AlbumView(lib);
			System.out.println("\nWelcome to your personal music library " + username + "!\n");
			// give initial options
			System.out.println("[0] - shop music store");
			System.out.println("[1] - open songs");
			System.out.println("[2] - open albums");
			System.out.println("[3] - open playlists");
			System.out.println("[4] - show artists");
			System.out.println("[5] - QUIT PROGRAM");
			
			// select choice
			try {
	            int choice = scanner.nextInt();
	            scanner.nextLine(); 
	            mainChoice(choice);
	        } catch(Exception e) {
	            System.out.println("enter number.");
	            scanner.nextLine(); 
	            mainHome(); 
	        }
	    }
		
		protected static void updateAccount() {
			accountsManager.updateAccount(username, lib);
		}
		
		@SuppressWarnings("static-access")
		private static void mainChoice(int choice) {
			// jump to different methods depending on the user input
			switch (choice) {
				case 0:
					// search music store
					musicStoreOptions();
					break;
				case 1:
					// search library for song by title
					songsView.home();
					break;
				case 2:
				    albumView.home();
					break;
				case 3:	
					// search lib for album by title
					playListView.home();
					break;
				case 4:
					displayArtistsInLib();
				case 5:	
					endProgram();
					break;
			}
				
		}
			
		public static void endProgram() {
			System.out.println("\n closing library!");
			updateAccount();
			System.exit(0);
		}
		
		
		private void libIsEmpty() {
			// if library size is 0 -> empty
			if (lib.getSongTitles().size() == 0) {
				System.out.println("Library is empty.");
				// jump home
				mainHome();
				return;
			}
		}
		
		// method to show songs
		protected static void displaySongs(List<Song> songs) {
				// display the songs display a list of songs by index title and album
			for (int i = 0; i < songs.size(); i++) {
				if((!lib.hasSong(songs.get(i)) || lib.getRating(songs.get(i)) == Rating.UNRATED) ){
					System.out.println("[" + i + "] " + songs.get(i) + ", " + songs.get(i).getAlbumTitle());
			}   else {
				System.out.println("[" + i + "] " + songs.get(i) + ", " + lib.getRating(songs.get(i)));
				}
			}
		}
		
	
		// handles searching for a song or album in the music store to add to library
		private static void musicStoreOptions() {
			// give options
			System.out.println("[0] - return to library");
			System.out.println("[1] - search for song by title");
			System.out.println("[2] - search for song by artist");
			System.out.println("[3] - search for album by title");
			System.out.println("[4] - search for album by artist");
			
			if (scanner.hasNextInt()) {
	            int choice = scanner.nextInt();
	            scanner.nextLine(); 
	            musicStoreChoice(choice);
	        } else {
	            System.out.println("Invalid input.");
	            scanner.nextLine(); 
	            musicStoreOptions(); 
	        }
		}
		
		private static void musicStoreChoice(int choice) {
			switch (choice) {
				 
				case 0:
					// option to go home
					mainHome();
					
				case 1: 	// add song by title to lib
					System.out.println("Enter song title: ");
					String titleS = scanner.nextLine().toLowerCase();
					// search for song by title using music store method
					List<Song> songsByTitle = musicStore.getSongsByTitle(titleS);
					// handle exceptions
					if (songsByTitle.size() == 0 ) {
						System.out.println("Song not found: " + titleS + "\n");
						mainHome();
						break;
					}
					// add to lib and go home
					addSongToLibrary(songsByTitle);
					mainHome();
					break;
			
				case 2:		// add song by artist to lib
					System.out.println("Enter artist name: ");
					String artistS = scanner.nextLine().toLowerCase();
					// handle exceptions (not existing in lib)
					if (musicStore.getSongsByArtist(artistS).size() == 0 ) {
						System.out.println("Song not found: " + artistS + "\n");
						mainHome();
						break;
					}
					// search for song by artist using music store methods
					List<Song> songsByArtist = musicStore.getSongsByArtist(artistS);
					// add to lib and go home
					addSongToLibrary(songsByArtist);
					mainHome();
					break;
					
				case 3:		// album by title
					System.out.println("Enter album title: ");
					String titleA = scanner.nextLine().toLowerCase();
					// handle exceptions
					if (musicStore.getAlbumsByTitle(titleA).size() == 0) {
						System.out.println("Album not found: " + titleA + "\n");
						mainHome();
						break;
					}
					// search for album by title using music store method
					List<Album> albumsByTitle = musicStore.getAlbumsByTitle(titleA);
					// add album to lib
					addAlbumToLibrary(albumsByTitle);
					// go home
					mainHome();
					break;
				
				case 4:		// albums by artist
					System.out.println("Enter album artist: ");
					String artistA = scanner.nextLine().toLowerCase();
					// handle exceptions
					if (musicStore.getAlbumsByArtist(artistA).size() == 0 ) {
						System.out.println("Album not found: " + artistA + "\n");
						mainHome();
						break;
					}
					// use music store methods to search for album by artist
					List<Album> albumsByArtist = musicStore.getAlbumsByArtist(artistA);
					// add album to lib
					addAlbumToLibrary(albumsByArtist);
					// go home
					mainHome();
					break;
				default:
					// handle invalid input
					System.out.println("Invalid input: " + choice + "\n");
					mainHome();
					break;
			}
		}
		// add an album to the library
		private static void addAlbumToLibrary(List<Album> albums) {
			// display albums to add
			displayAlbums(albums);
			 
			System.out.println("Select album to add: ");
			if (scanner.hasNextInt()) {
				int selectedAlbumIndex = scanner.nextInt();
	            scanner.nextLine(); 
	            albumChoice(selectedAlbumIndex, albums);
	        } else {
	            System.out.println("Invalid input.");
	            scanner.nextLine(); 
	            addAlbumToLibrary(albums); 
	        }
			
		}
		protected static void albumChoice(int choice, List<Album> albums) {
			// handle out of range exceptions
			if (choice < 0 || choice > albums.size()) {
				System.out.println("Invalid input: " + choice + "\n");
				mainHome();
			}
			// get desired album
			Album selectedAlbum = albums.get(choice);
			
			// handle exception that albums is already in the library
			if (lib.hasAlbum(selectedAlbum)) {
				// add any new songs from selected album in music store to album in library
				lib.addSongsToAlbum(selectedAlbum.getTitle(), selectedAlbum.getAllSongs());
				updateAccount();
				System.out.println(selectedAlbum.getTitle() + " updated in library.");
				mainHome();
				return;
			}
			
			// add album to library
			lib.addAlbum(selectedAlbum);
			updateAccount();
			System.out.println(selectedAlbum.getTitle() + " has been added to library.");
		}

		// method to show albums
		protected static void displayAlbums(List<Album> albums) {		
			// print out albums with the songs on the album
			for (int i = 0; i < albums.size(); i++) {
				System.out.println("[" + i + "] " + albums.get(i).getTitle()
						// print album title
						+ ",  " + albums.get(i).getArtist()
						+ ", " + albums.get(i).getGenre()
						+ ", " + albums.get(i).getYear());
				System.out.println("     Songs:");
				for (Song song : albums.get(i).getAllSongs()) {
					// print songs
					System.out.println("     " + song.getSongTitle());
				}
			}
		}
		
		// add a song to the library
		private static void addSongToLibrary(List<Song> songs) {
			// display songs to add
			displaySongs(songs);
			System.out.println("Select song to add: ");
			try {
				int selectIndex = scanner.nextInt();
				scanner.nextLine();
				addSongToLibrary(selectIndex, songs);
			} catch(Exception e) {
				System.out.println("Invalid input.");
				scanner.nextLine();
				addSongToLibrary(songs);
			}
		}
		
		private static void addSongToLibrary(int i, List<Song> songs) {
			// find desired song
			Song selectedSong = songs.get(i);
			// handle exceptions
			if (lib.hasSong(selectedSong)) {
				System.out.println("Song already exists in library. ");	
				mainHome();
				return;
			}
			// add song to library
			lib.addSong(selectedSong);
			addAlbumBySong(selectedSong);
			updateAccount();
			System.out.println(selectedSong + " has been added to library. \n");
		}
	    
		private static void addAlbumBySong(Song song) {
			String albumTitle = song.getAlbumTitle();
			
			if (lib.hasAlbumByTitle(albumTitle)) { // album exists in lib
				List<Song> songToAdd = new ArrayList<>();
				songToAdd.add(song);
				lib.addSongsToAlbum(albumTitle, songToAdd);
				System.out.println(song.getSongTitle() + " has been added to " + albumTitle);
			}
			else { // album does not exist in lib
				List<Album> albums = musicStore.getAlbumsByTitle(albumTitle);
				Album album = albums.get(0);
				Album newAlbum = new Album(album.getTitle(),album.getArtist(),
						album.getGenre(), album.getYear());
				newAlbum.addSong(song);
				lib.addAlbum(newAlbum);
			}
		}
		
		// display artists in library
		private static void displayArtistsInLib() {
		    // Use HashSet to store unique artists
		    Set<String> artists = new HashSet<>(lib.getSongArtists());
		    // print each artist
		    int index = 0;
		    for (String artist : artists) {
		        System.out.println("[" + index + "] " + artist);
		        index++;
		    }
		    // go home
		    mainHome();
		}
}
