// Sam Hershey, Lola Swiderski
// class for the user to use the music library 
package view;

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
public class LibraryView {
	// instantiate a music store, a scanner for the user, and a music library
	    private static MusicStore musicStore = new MusicStore("albums");
	    private static Scanner scanner = new Scanner(System.in);
	    private static LibraryModel lib = new LibraryModel(); 
	    private static boolean isPlaying = false;
	    private static Song songPlaying = new Song(null, null, null, null);		
	    public static void main(String[] args) {
			// call homepage
			home();
		}
		
		public static void home() {
			// home page for the program to jump back to after actions
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
			System.out.println("[12] - sort songs");
			System.out.println("[13] - 	QUIT PROGRAM");
			if (isPlaying) {
				System.out.println("[14] - pause song: " + songPlaying.getSongTitle());
			}
			
			// select choice
			try {
	            int choice = scanner.nextInt();
	            scanner.nextLine(); 
	            mainChoice(choice);
	        } catch(Exception e) {
	            System.out.println("Invalid input.");
	            scanner.nextLine(); 
	            home(); 
	        }
	    }

		
		private static void mainChoice(int choice) {
			// jump to different methods depending on the user input
			switch (choice) {
				case 0:
					// search music store
					musicStoreOptions();
					break;
				case 1:
					// search library for song by title
					songByTitle();
					break;
				case 2:	
					// search library for song by artist
					songByArtist();
					break;
				case 3:	
					// search lib for album by title
					albumByTitle();
					break;
				case 4:	
					// search lib for album by artist
					albumByArtist();
					break;
				case 5:	
					// search lib for playlist by name
					searchForPlayListByName();
					break;
				case 6:	
					// display the songs added to the library
					displaySongsInLib();
					break;
				case 7:	
					// display the artists in the library
					displayArtistsInLib();
					break;
				case 8:	
					// display the albums in a library
					displayAlbumsInLib();
					break;
				case 9:	
					// display playlists
					displayPlayLists();
					break;
				case 10:	
					// display favorite songs
					displayFavorites();
					break;
				case 11:	
					// create a playlist
					createPlayList();
					break;
				case 12:
					sortSongs();
				case 13:	
					// end the program
					endProgram();
					break;
				case 14:
					pauseSong();
				
				default:
					// handle invalid output
					System.out.println("Invalid input");
					home();
					break;
			}
		}
			
		public static void endProgram() {
			System.exit(0);
		}
		
		
		public static void sortSongs() {
			
			System.out.println("[0] - sort songs by title");
			System.out.println("[1] - sort songs by artist");
			System.out.println("[2] - sort songs by rating");
			System.out.println("[3] - home");
			try {
				int choice = scanner.nextInt();
				scanner.nextLine();
				switch (choice) {
				case 0:
					System.out.println("Songs sorted by title: \n");
					displaySongs((lib.sortByTitle()));
					home();
					break;
				case 1:
					System.out.println("Songs sorted by artist: \n");
					displaySongs((lib.sortByArtist()));
					break;
				case 2:
					System.out.println("Songs sorted by rating: \n");
					displaySongs((lib.sortByRating()));
					home();
					break;
				case 3:
					home();
					break;
				}
				
			}
			catch(Exception e) {
				System.out.println("Please enter number.");
				sortSongs();
			}
		}
		
		
		public static void pauseSong() {
			System.out.println("Pause song?: " + songPlaying.getSongTitle() + "\n");
			System.out.println("[0] - yes");
			System.out.println("[1] - home");
			try {
				int choice = scanner.nextInt();
				scanner.nextLine();
				switch(choice) {
				case 0:
					System.out.println(songPlaying + " paused.");
					isPlaying = false;
					home();
					break;	
				case 1:
					home();
					break;
				}
			}
			catch (Exception e) {
				System.out.println("please enter number. \n");
				pauseSong();
			}
		}
		// creates playlist with entered name
		private static void createPlayList() {
			System.out.println("Create name of playlist: ");
			String name = scanner.nextLine();
			PlayList newPL = new PlayList(name);
			lib.addPlaylist(newPL);
			System.out.println("PlayList created: " + name + "\n");
			home();
		}
		
		// Shows list of playlists and lets user select one.
		private static void displayPlayLists() {
			// Show default playlists
			
			List<PlayList> playLists = lib.getPlayLists();
			for (int i = 0; i < playLists.size(); i++) {
				// print numbered list of playlists
				System.out.println("[" + (i) + "] " + playLists.get(i).getName()); 
			}
			System.out.println("[" + playLists.size() + "] " + "Return Home\n");
			// allow user to select playlist
			System.out.println("Select playlist.\n");
			int choice = scanner.nextInt();
			scanner.nextLine();
			if (choice == playLists.size()) {
				// user has option to go home
				home();
				return;
			}
			
			PlayList selectedPlayList = playLists.get(choice);
			if (choice == 0) {	// default playlists
				displayPlayList(selectedPlayList);
				home();
			}
			if (choice == 1) {
				displayPlayList(selectedPlayList);
				home();
			} else {
				playListChoices(selectedPlayList);
			}
		}
		
		// user enters name and associated playlist is selected
		private static void searchForPlayListByName() {
			System.out.println("Enter name of playlist: ");
			String name = scanner.nextLine();
			// search for playlist
			for (PlayList playList : lib.getPlayLists()) {
				if (playList.getName().equalsIgnoreCase(name)) {
					playListChoices(playList);
				}
			}
			// handle exceptions
			System.out.println("Playlist not found. ");
			home();
		}
		// allows user to add/remove a song from playlist
		
	
		private static void playListChoices(PlayList playList) {
			// method to handle removing and adding songs to a playlist
			displayPlayList(playList);
			// give user playlist options 
			System.out.println("[0] - add a song");
			System.out.println("[1] - remove a song");
			System.out.println("[2] - Return Home");
			// read option
			int choice = scanner.nextInt();
			scanner.nextLine();
			switch(choice) {
				case 0:
					// add song
					addSongToPlayList(playList);
					break;
				case 1:
					// remove song
					removeSongFromPlayList(playList);
					break;
				case 2:
					// go home
					home();
			}
		}
		
		// user selects a song in playlist to remove
		private static void removeSongFromPlayList(PlayList playList) {
			// method to remove a song from a playlist
			List<Song> songsInPlayList = playList.getSongs();
			// display the songs in lib to add
			displaySongs(songsInPlayList);
			System.out.println("Select song to remove: ");
			int choice = scanner.nextInt();
			scanner.nextLine();
			Song selectedSong = songsInPlayList.get(choice);
			// use playlist class to remove song from playlist list
			playList.removeSong(selectedSong);
			System.out.println(selectedSong + "has been removed from " + playList.getName() + "\n");
			// go home
			home();
		}
		
		// user selects a song in library to add to playlist
		private static void addSongToPlayList(PlayList playList) {
			List<Song> allSongs = lib.getAllSongs();
			// display songs to add
			displaySongs(allSongs);
			System.out.println("Select song to add: ");
			int choice = scanner.nextInt();
			scanner.nextLine();
			// get song
			Song selectedSong = allSongs.get(choice);
			// handle exceptions
			if (playList.hasSong(selectedSong)) {
				System.out.println("Song already exists in playlist");
				home();
			}
			// add a song to the playlist list 
			playList.addSong(selectedSong);
			System.out.println(selectedSong + "has been added to " + playList.getName() + "\n");
			// go home
			home();
		}
		
		// displays a playlist and each song in it
		private static void displayPlayList(PlayList playList) {
			// print playlist name + songs
			System.out.println("Selected playlist: " + playList.getName());
			System.out.println("     Songs in " + playList.getName() + ":");
			for (Song song : playList.getSongs()) {
				System.out.println("     " + song.getSongTitle());
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
					home();
					
				case 1: 	// add song by title to lib
					System.out.println("Enter song title: ");
					String titleS = scanner.nextLine().toLowerCase();
					// handle exceptions
					if (musicStore.getSongsByTitle(titleS).size() == 0 ) {
						System.out.println("Song not found: " + titleS + "\n");
						home();
						break;
					}
					// search for song by title using music store method
					List<Song> songsByTitle = musicStore.getSongsByTitle(titleS);
					// add to lib and go home
					addSongToLibrary(songsByTitle);
					home();
					break;
			
				case 2:		// add song by artist to lib
					System.out.println("Enter artist name: ");
					String artistS = scanner.nextLine().toLowerCase();
					// handle exceptions (not existing in lib)
					if (musicStore.getSongsByArtist(artistS).size() == 0 ) {
						System.out.println("Song not found: " + artistS + "\n");
						home();
						break;
					}
					// search for song by artist using music store methods
					List<Song> songsByArtist = musicStore.getSongsByArtist(artistS);
					// add to lib and go home
					addSongToLibrary(songsByArtist);
					home();
					break;
					
				case 3:		// album by title
					System.out.println("Enter album title: ");
					String titleA = scanner.nextLine().toLowerCase();
					// handle exceptions
					if (musicStore.getAlbumsByTitle(titleA).size() == 0) {
						System.out.println("Album not found: " + titleA + "\n");
						home();
						break;
					}
					// search for album by title using music store method
					List<Album> albumsByTitle = musicStore.getAlbumsByTitle(titleA);
					// add album to lib
					addAlbumToLibrary(albumsByTitle);
					// go home
					home();
					break;
				
				case 4:		// albums by artist
					System.out.println("Enter album artist: ");
					String artistA = scanner.nextLine().toLowerCase();
					// handle exceptions
					if (musicStore.getAlbumsByArtist(artistA).size() == 0 ) {
						System.out.println("Album not found: " + artistA + "\n");
						home();
						break;
					}
					// use music store methods to search for album by artist
					List<Album> albumsByArtist = musicStore.getAlbumsByArtist(artistA);
					// add album to lib
					addAlbumToLibrary(albumsByArtist);
					// go home
					home();
					break;
				default:
					// handle invalid input
					System.out.println("Invalid input: " + choice + "\n");
					home();
					break;
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
			}catch(Exception e){
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
				home();
				return;
			}
			// add song to library
			lib.addSong(selectedSong);
			System.out.println(selectedSong + " has been added to library. \n");
		}
	    
		// method to show songs
		private static void displaySongs(List<Song> songs) {
			// display the songs display a list of songs by index title and album
			for (int i = 0; i < songs.size(); i++) {
				if(lib.getRating(songs.get(i)) == Rating.UNRATED) {
					System.out.println("[" + i + "] " + songs.get(i) + ", " + songs.get(i).getAlbumTitle());
			}   else {
				System.out.println("[" + i + "] " + songs.get(i) + ", " + songs.get(i).getAlbumTitle() + ", " + 
			lib.getRating(songs.get(i)));
			}
		}
		}
		
		// method to show albums
		private static void displayAlbums(List<Album> albums) {		
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
		
		private static void albumChoice(int choice, List<Album> albums) {
			// handle out of range exceptions
			if (choice < 0 || choice > albums.size()) {
				System.out.println("Invalid input: " + choice + "\n");
				home();
			}
			// get desired album
			Album selectedAlbum = albums.get(choice);
			
			// handle exception that albums is already in the library
			if (lib.hasAlbum(selectedAlbum)) {
				System.out.println("Album already exists in library. ");	
				home();
				return;
			}
			
			// add album to library
			lib.addAlbum(selectedAlbum);
			System.out.println(selectedAlbum.getTitle() + " has been added to library. \n");
		}
		
		// gives users options after selecting song
		private static int songOptions() {
			// either go home, rate a song, or favorite a song
			System.out.println("[0] - home");
			System.out.println("[1] - rate song");
			System.out.println("[2] - favorite song");
			System.out.println("[3] - play song");
			System.out.println("[4] - remove song");
			
			
			try{
				int option = scanner.nextInt();
	            //scanner.nextLine(); 
	            
	            if (option < 0 || option > 4) {
					System.out.println("Invalid input: " + option + "\n");
					// return to homepage
					home();
				
	            } else {        	         	       
	           
					return option;
				
				}
			} catch (Exception e) {
	            System.out.println("Invalid input.");
	            songOptions(); 
	        }
			return 0;
		}
		
		// get a song in library by the title
		private static void songByTitle() {
			libIsEmpty();
			System.out.println("Enter song title: \n");
			String title = scanner.nextLine();
			List<Song> songsList = lib.getSongsByTitle(title);
			// if song isnt in library, handle exception
			if (!lib.hasSongByTitle(title)) {
				System.out.println("Can not find song in library.");
				// jump back home
				home();
				return;
			}
			// check if library is empty
			libIsEmpty();
			// display songs to add
			displaySongs(songsList);
			// select the song
			selectSong(songsList);
		}
		
		// method to check if the library is empty
		private static void libIsEmpty() {
			// if library size is 0 -> empty
			if (lib.getSongTitles().size() == 0) {
				System.out.println("Library is empty.");
				// jump home
				home();
				return;
			}
		}
		
		// method used to rate a song 
		public static Rating rateSong(Song song) {
			String songTitle = song.getSongTitle();
			Rating setRating = null;
			System.out.println("Enter song rating 1-5: ");
			// get desired song and rating
			if (scanner.hasNextInt()) {
				int rating = scanner.nextInt();
				scanner.nextLine();
				if (rating <= 0 || rating > 5) {
					System.out.println("Invalid input: " + rating + "\n");
					home();
				}
				// ratings can be 1-5
				
				switch (rating) {
				case 1:
					// set enum to 1, rate song, print confirmation, go home
					setRating = setRating.ONE;
					lib.rateSong(song,setRating);
					System.out.println(songTitle + " has been rated a " + rating);
					home();
					break;
				
				case 2:
					// set enum to 2, rate song, print confirmation, go home
					setRating = setRating.TWO;
					lib.rateSong(song,setRating);
					System.out.println(songTitle + " has been rated a " + rating);
					home();
					break;
				case 3:
					// set enum to 3, rate song, print confirmation, go home
					setRating = setRating.THREE;
					lib.rateSong(song,setRating);
					System.out.println(songTitle + " has been rated a " + rating);
					home();
					break;
				case 4:
					// set enum to 3, rate song, print confirmation, go home
					setRating = setRating.FOUR;
					lib.rateSong(song,setRating);
					System.out.println(songTitle + " has been rated a " + rating);
					home();
					break;
				case 5:
					// set enum to 5, rate song, print confirmation, go home
					setRating = setRating.FIVE;
					lib.rateSong(song,setRating);
					System.out.println(songTitle + " has been rated a " + rating);
					System.out.println(songTitle + " has been added to favorites!");
					home();
					break;
				}
				// return enum
				return setRating;
			
			
			} else {
				System.out.println("Invalid input.");
				home();
			}
			return null;
		}
		
		
		// method to select a song
		private static void selectSong(List<Song> songsList) {
			// check if library is empty
			libIsEmpty();
			System.out.println("Select song from library: ");
			try {
				
				int selectSongIndex = scanner.nextInt();
				selectSong(selectSongIndex, songsList);
				
			} catch(Exception e) {
	            System.out.println("Invalid input.");
	            home(); 
	        }
		}
		
		private static void selectSong(int index, List<Song> songsList) {
			
			Song selectedSong = songsList.get(index);
			// check if song is in library
			if (!lib.getAllSongs().contains(selectedSong)) {
			    System.out.println("Song not found in the library.");
			    home();
			    return;
			}
			switch(songOptions()) {
			// perform on song accordingly (go home, rate, favorite, play)
				case 0:
					// go home
					home();
					break;
				case 1:
					// use rate song methods
					lib.rateSong(selectedSong, rateSong(selectedSong));
					break;
				case 2:
					// add to favorites list in lib model
					lib.addFavorite(selectedSong);
					System.out.println(selectedSong.getSongTitle() +" has been added to favorites!");
					System.out.println(selectedSong.getSongTitle() + " has been rated a 5! ");
					home();
					break;
				case 3:
					for (int i = 0; i < 3; i++) {
						System.out.println(selectedSong.getSongTitle() + " is playing...\n");
					}	
					lib.playSong(selectedSong);	
					isPlaying = true;
					songPlaying = selectedSong;
					home();
					break;
				case 4:
					lib.removeSong(selectedSong);
					System.out.println(selectedSong + " has been removed from your library. \n");
					home();
					
			}
		}
		
		// method to loo for songs by artists
		private static void songByArtist() {
			// check if library empty
			libIsEmpty();
			System.out.println("Enter song artist: \n");
			String artist = scanner.nextLine();
			// get desired artist and find the list using lib model method
			List<Song> songsList = lib.getSongsByArtist(artist);
			// handle artist not being in library
			if (!lib.hasSongByArtist(artist)) {
				System.out.println("Can not find artist in library.");
				home();
				return;
			}
			// display the songs by artist
			displaySongs(songsList);
			// select a song 
			selectSong(songsList);

		}
		
		// get albums by artist 
		private static void albumByArtist() {
			// check if library is empty
			libIsEmpty();
			System.out.println("Enter album artist: \n");
			String artist = scanner.nextLine();
			// handle artist not being in library
			if (!lib.hasAlbumByArtist(artist)) {
				System.out.println("Can not find artist in library.");
				home();
				return;
			}
			// get songslist using library model method
			List<Album> songsList = lib.getAlbumsByArtist(artist);
			// display the albums
			displayAlbums(songsList);
			// go home
			home();
		}
		// get albums by title
		private static void albumByTitle() {
			libIsEmpty();
			System.out.println("Enter album title: \n");
			String title = scanner.nextLine();
			// check if lib empty
			// handle exceptions
			if (!lib.hasAlbumByTitle(title)) {
				System.out.println("Can not find album in library.");
				home();
				return;
			}
			// create songslist
			List<Album> songsList = lib.getAlbumsByTitle(title);
			// display albums
			displayAlbums(songsList);
			// jump home
			home();
		}
		
		// display songs with ratings in library
		private static void displaySongsInLib() {
			// check library is empty
			libIsEmpty();
		    int i = 0;
		    // print songs and ratings
		    for (Song song : lib.getAllSongs()) {
		    	if (lib.getRating(song) == Rating.UNRATED) {
		    		System.out.println("[" + i + "] " + song.getSongTitle() + ", " + song.getArtist());
		    		i++;
		    	} else {
		        System.out.println("[" + i + "] " + song.getSongTitle() + ", " + song.getArtist() + ", " + lib.getRating(song));
		        i++;
		    	}
		        
		    }
		    home();
		}
		
		// display artists in library
		private static void displayArtistsInLib() {
			// check if empty lib
			libIsEmpty();
		    // Use HashSet to store unique artists
		    Set<String> artists = new HashSet<>(lib.getSongArtists());
		    // print each artist
		    int index = 0;
		    for (String artist : artists) {
		        System.out.println("[" + index + "] " + artist);
		        index++;
		    }
		    // go home
		    home();
		}
	
		// display albums in library
		private static void displayAlbumsInLib() {
			// check if library empty
			libIsEmpty();
			// print albums
			for (int i = 0; i < lib.getAlbums().size();i++) {
				System.out.println("["+ i + "]" + " " + lib.getAlbums().get(i).getTitle() + ", " +
			lib.getAlbums().get(i).getArtist() + ", " + lib.getAlbums().get(i).getGenre() + ", " +
						lib.getAlbums().get(i).getYear());
			}
			// go home
			home();
		}
		// display favorite songs in library
		private static void displayFavorites() {
			// check if empty
			libIsEmpty();
			System.out.println("Favorite songs:");
			List<Song> songsList = lib.getFavorites();
			// print list of favorite songs
			displaySongs(songsList);
			// go home
			home();
		}
	}
