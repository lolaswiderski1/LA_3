package view;

import java.util.List;
import java.util.Scanner;

import dataStructures.Song;
import model.LibraryModel;
import model.LibraryModel.Rating;

public class SongsView extends LibraryView{
	
	private static Scanner scanner = new Scanner(System.in);
	private static LibraryModel lib;
	
	public SongsView(LibraryModel lib) {
		this.lib = lib;
	}
	
	public static void home() {
		
		System.out.println("[0] - open the songs in your library");
		System.out.println("[1] - search for a song in your library");
		System.out.println("[2] - shuffle songs");
		System.out.println("[3] - sort songs");
		System.out.println("[4] - return to homepage");
		
		
		try {
            int choice = scanner.nextInt();
            scanner.nextLine(); 
            mainChoice(choice);
        } catch(Exception e) {
            System.out.println("enter number. \n");
            scanner.nextLine(); 
            home(); 
        }
	}
	
	public static void mainChoice(int choice) {
		switch(choice) {
		case 0:
			choiceDisplay();
		case 1:
			choiceSearch();
		case 2:
			choiceShuffle();
		case 3:
			sortSongs();
		case 4:
			mainHome();
		}
	}
	
	public static void choiceSearch() {
		System.out.println("[0] - search for song by title");
		System.out.println("[1] - search for song by artist");
		System.out.println("[2] - home");
		try {
            int choice = scanner.nextInt();
            scanner.nextLine(); 
            switch (choice) {
            case 0:
            	songByTitle();
            case 1:
            	songByArtist();
            case 2:
            	home();
            }        
        } catch(Exception e) {
            System.out.println("enter number. \n");
            scanner.nextLine(); 
            home(); 
        }
	}	

	public static void choiceDisplay() {
		System.out.println("[0] - show all songs in your library");
		System.out.println("[1] - show favorite songs in your library");
		System.out.println("[2] - home");
		try {
            int choice = scanner.nextInt();
            scanner.nextLine(); 
            switch (choice) {
            case 0:
            	displaySongsInLib();
            case 1:
            	displayFavorites();
            case 2:
            	home();
            }
        } catch(Exception e) {
            System.out.println("enter number. \n");
            scanner.nextLine(); 
            home(); 
        }
	}
	
	public static void sortSongs() {
		
		System.out.println("[0] - sort by title");
		System.out.println("[1] - sort by artist");
		System.out.println("[2] - sort by rating");
		System.out.println("[3] - home");
		
		try {
			int choice = scanner.nextInt();
			scanner.nextLine();
			switch (choice) {
			case 0:
				System.out.println("Songs sorted by title: \n");
				displaySongs((lib.sortByTitle()));
				mainHome();
				break;
			case 1:
				System.out.println("Songs sorted by artist: \n");
				displaySongs((lib.sortByArtist()));
				break;
			case 2:
				System.out.println("Songs sorted by rating: \n");
				displaySongs((lib.sortByRating()));
				mainHome();
				break;
			case 3:
				mainHome();
				break;
			}
			
		}
		catch(Exception e) {
			System.out.println("enter number.");
			mainHome();
		}
	}
	
	public static void choiceShuffle() { 
		System.out.println("shuffled songs: \n");
		List<Song> shuffledSongs = lib.shuffleSongs();
		displaySongs(shuffledSongs);
		try {
			System.out.println("select a random song. \n");
			int choice = scanner.nextInt();
			scanner.nextLine();
			selectSong(choice, shuffledSongs);
		}
		catch (Exception e) {
			System.out.println("enter number.");
			home();
		}
	}
	
	public static void songByTitle() {
		
		libIsEmpty();
		System.out.println("Enter song title: \n");
		String title = scanner.nextLine();
		List<Song> songsList = lib.getSongsByTitle(title);
		// if song isnt in library, handle exception
		if (!lib.hasSongByTitle(title)) {
			System.out.println("Can not find song in library. \n");
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
	
	// method to look for songs by artists
	private static void songByArtist() {
		// check if library empty
		//libIsEmpty();
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
	
	// gives users options after selecting song
		private static int songOptions() {
			// either go home, rate a song, or favorite a song
			System.out.println("[0] - play song");
			System.out.println("[1] - favorite song");
			System.out.println("[2] - rate song");
			System.out.println("[3] - remove song from your library");
			System.out.println("[4] - display album information");
			System.out.println("[5] - home");
			
			try{
				int option = scanner.nextInt();
	            //scanner.nextLine(); 
	            
	            if (option < 0 || option > 5) {
					System.out.println("invalid: out of range \n");
					// return to homepage
					home();				
	            } else {        	         	       		   
					return option;
				}
			} catch (Exception e) {
	            System.out.println("enter number. \n");
	            songOptions(); 
	        }
			return 0;
		}
		
		// display songs with ratings in library
		private static void displaySongsInLib() {
			// check library is empty
			libIsEmpty();
		    int i = 0;
		    // print songs and ratings
		    System.out.println("lib.getAllSongs().size() = " + lib.getAllSongs().size());
		    for (Song song : lib.getAllSongs()) {
		    	if (lib.getRating(song) == Rating.UNRATED) {
		    		System.out.println("[" + i + "] " + song.getSongTitle() + ", " + song.getArtist());
		    		i++;
		    	} else {
		        System.out.println("[" + i + "] " + song.getSongTitle() + ", " + song.getArtist() + ", " + lib.getRating(song));
		        i++;
		    	}
		        
		    }
		    selectSong(lib.getAllSongs());
		}
				
	    // method to select a song
		private static void selectSong(List<Song> songsList) {
			// check if library is empty
			//libIsEmpty();
			System.out.println("select song from library: ");
			try {
				
				int selectSongIndex = scanner.nextInt();
				selectSong(selectSongIndex, songsList);
				
			} catch(Exception e) {
	            System.out.println("enter number.");
	            home(); 
	        }
		}
		
		private static void selectSong(int index, List<Song> songsList) {
			
			Song selectedSong = songsList.get(index);
			// check if song is in library
			if (!lib.getAllSongs().contains(selectedSong)) {
			    System.out.println("song not in library.");
			    home();
			    return;
			}
			switch(songOptions()) {
			case 0:
				for (int i = 0; i < 3; i++) {
					System.out.println(selectedSong.getSongTitle() + " is playing...\n");
				}	
				lib.playSong(selectedSong);	
				updateAccount();
				home();
			case 1:
				// add to favorites list in lib model
				lib.addFavorite(selectedSong);
				updateAccount();
				System.out.println(selectedSong.getSongTitle() +" has been added to favorites!");
				System.out.println(selectedSong.getSongTitle() + " has been rated a 5! \n");
				home();
			case 2:
				lib.rateSong(selectedSong, rateSong(selectedSong));
				updateAccount();
			case 3:
				lib.removeSong(selectedSong);
				updateAccount();
				System.out.println(selectedSong + " has been removed from your library. \n");
				home();
			case 4:
				displayAlbums(musicStore.getAlbumsByTitle(selectedSong.getAlbumTitle()));
				//displayAlbums(lib.getAlbumsByTitle(selectedSong.getAlbumTitle()));
				home();
				break;
			case 5:
				// go home
				home();
				break;
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
					System.out.println("invalid: out of range \n");
					home();
				}
				// ratings can be 1-5
				
				switch (rating) {
				case 1:
					// set enum to 1, rate song, print confirmation, go home
					setRating = setRating.ONE;
					lib.rateSong(song,setRating);
					updateAccount();
					System.out.println(songTitle + " has been rated a " + rating + "\n");
					home();
					break;
				
				case 2:
					// set enum to 2, rate song, print confirmation, go home
					setRating = setRating.TWO;
					lib.rateSong(song,setRating);
					updateAccount();
					System.out.println(songTitle + " has been rated a " + rating + "\n");
					home();
					break;
				case 3:
					// set enum to 3, rate song, print confirmation, go home
					setRating = setRating.THREE;
					lib.rateSong(song,setRating);
					updateAccount();
					System.out.println(songTitle + " has been rated a " + rating + "\n");
					home();
					break;
				case 4:
					// set enum to 3, rate song, print confirmation, go home
					setRating = setRating.FOUR;
					lib.rateSong(song,setRating);
					updateAccount();
					System.out.println(songTitle + " has been rated a " + rating + "\n");
					home();
					break;
				case 5:
					// set enum to 5, rate song, print confirmation, go home
					setRating = setRating.FIVE;
					lib.rateSong(song,setRating);
					updateAccount();
					System.out.println(songTitle + " has been rated a " + rating);
					System.out.println(songTitle + " has been added to favorites!\n");
					home();
					break;
				}
				// return enum
				return setRating;
			} else {
				System.out.println("enter number. \n");
				home();
			}
			return null;
		}
				

	// display favorite songs in library
	private static void displayFavorites() {
		// check if empty
		//libIsEmpty();
		System.out.println("Favorite songs:");
		List<Song> songsList = lib.getFavorites();
		// print list of favorite songs
		displaySongs(songsList);
		// go home
		home();
	}
	
	private static void libIsEmpty() {
		// if library size is 0 -> empty
		if (lib.getSongTitles().size() == 0) {
			System.out.println("Library is empty.");
			// jump home
			home();
			return;
		}
	}
}
