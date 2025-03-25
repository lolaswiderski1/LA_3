
package view;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import dataStructures.PlayList;
import dataStructures.Song;
import model.LibraryModel;

public class PlayListView extends LibraryView {
	
	private static Scanner scanner = new Scanner(System.in);
	private static LibraryModel lib;
	
	public PlayListView(LibraryModel lib) {
		this.lib = lib;
	}
	
	public static void home() {
		System.out.println("[0] - create a playlist");
		System.out.println("[1] - open the playlists in your library");
		System.out.println("[2] - search for a playlist in your library");
		System.out.println("[3] - shuffle playlists");
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
			createPlayList();
			break;
		case 1:
			displayPlayLists(lib.getPlayLists());
			break;
		case 2:
			searchForPlayList();
			break;
		case 3:
			shufflePlaylists();
			break;
		case 4:
			mainHome();
		}
	}
	
	private static void shufflePlaylists() {
		System.out.println("select a random playlist. \n");
		System.out.println("shuffled playlists: \n");
		List<PlayList> shuffledPlaylists = lib.shufflePlaylists();
		displayPlayLists(shuffledPlaylists);
	}
	
	
	private static void createPlayList() {
		System.out.println("Create name of playlist: ");
		String name = scanner.nextLine();
		PlayList newPL = new PlayList(name);
		lib.addPlaylist(newPL);
		updateAccount();
		System.out.println("PlayList created: " + name + "\n");
		mainHome();
	}
	

	// Shows a list of playlists and lets user select one.
	private static void displayPlayLists(List<PlayList> playLists) {
		// Show default playlists
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
			mainHome();
			return;
		}
		
		PlayList selectedPlayList = playLists.get(choice);
		String[] defaultNames = {"ROCK", "POP", "ALTERNATIVE", "TRADITIONAL COUNTRY", "SINGER/SONGWRITER",
				"LATIN", "Most recent songs", "Most played songs", "Favorited songs"};
		
		if (Arrays.asList(defaultNames).contains(selectedPlayList.getName())) {
			displayPlayList(selectedPlayList);
			home();
			
		} else {
			playListChoices(selectedPlayList);
			home();
		}
	}
	
	// user enters name and associated playlist is selected
	private static void searchForPlayList() {
		System.out.println("Enter name of playlist: ");
		String name = scanner.nextLine();
		// search for playlist
		for (PlayList playList : lib.getPlayLists()) {
			if (playList.getName().equalsIgnoreCase(name)) {
				playListChoices(playList);
			}
		}
		// handle exceptions
		System.out.println("Playlist not found. \n");
		home();
	}
	// allows user to add/remove a song from playlist
	
	private static void playListChoices(PlayList playList) {
		// method to handle removing and adding songs to a playlist
		displayPlayList(playList);
		// give user playlist options 
		System.out.println("[0] - add a song"); 
		System.out.println("[1] - remove a song");
		System.out.println("[2] - home");
		// read option
		try {
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
		} }catch (Exception e) {
			System.out.print("enter a number.");
			home();
		}
	}
	
	// user selects a song in library to add to playlist
	private static void addSongToPlayList(PlayList playList) {
		List<Song> allSongs = lib.getAllSongs();
		// display songs to add
		displaySongs(allSongs);
		System.out.println("select song: ");
		int choice = scanner.nextInt();
		scanner.nextLine();
		// get song
		Song selectedSong = allSongs.get(choice);
		// handle exceptions
		if (playList.hasSong(selectedSong)) {
			System.out.println("this song is already in your playlist. \n");
			mainHome();
		}
		// add a song to the playlist list 
		playList.addSong(selectedSong); 
		System.out.println(selectedSong + "has been added to " + playList.getName() + "\n");
		// go home
		mainHome();
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
		mainHome();
	}
}
		
