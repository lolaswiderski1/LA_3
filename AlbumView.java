// Authors: Sam Hershey, Lola Swiderski
// Description: AlbumView is the user interface for album data. It lets users interact
// with the albums in their library

package view;

import java.util.List;
import java.util.Scanner;

import dataStructures.Album;
import model.LibraryModel;

public class AlbumView extends LibraryView {
	
	// instantiate a scanner for user input and a library object
	private static Scanner scanner = new Scanner(System.in);
	private static LibraryModel lib;
	
	public AlbumView(LibraryModel lib) {
		this.lib = lib;
	}
	
	// the homepage for albumview allows users to open albums, search for albums, or 
	// return to the main library homepage
	public static void home() {
		// print the users options
		System.out.println("[0] - open the albums in your library");
		System.out.println("[1] - search for albums in your library");
		System.out.println("[2] - return to homepage");
		// Handle edge cases. If a non integer input is entered, catch
		// the exception, and display and error message
		try {
            int choice = scanner.nextInt();
            scanner.nextLine(); 
            mainChoice(choice);
        } catch(Exception e) {
        	// print error message
            System.out.println("enter number. \n");
            scanner.nextLine(); 
            home(); 
        }
	}
	
	// diect the users input to the correct functionality
	public static void mainChoice(int choice) {
		// three main choices for albums (display, search, and home)
		switch(choice) {  
		case 0:
			displayAlbumsInLib();
			break;
		case 1:
			searchAlbums();
			break;
		case 2:
			mainHome();
			break;
		}
		
	}	
	
	// searchAlbums gives the user the options to search for an album by a title
	// or by an artist
	private static void searchAlbums() {
		// print out options
		System.out.println("[0] - search for albums by title");
		System.out.println("[1] - search for albums by artist");
		// direct the user to the correct search functionality
		try {
            int choice = scanner.nextInt();
            scanner.nextLine(); 
            switch (choice) {
            case 0:
            	albumByTitle();
            case 1:
            	albumByArtist();
            case 2:
            	home();
            }     
        } catch(Exception e) {
        	// catch edge cases
            System.out.println("enter number. \n");
            scanner.nextLine(); 
            home(); 
        }
		
	}
	
	// displayAlbumsInLib displays the albums in the library
	private static void displayAlbumsInLib() {
		// print out the albums index and the songs inside of it
		for (int i = 0; i < lib.getAlbums().size();i++) {
			System.out.println("[" + i + "]" + " " + lib.getAlbums().get(i));
		}
		mainHome();
	}
	
	// albumByArtist gets every album by an artist 
	private static void albumByArtist() {
		System.out.println("Enter album artist: \n");
		String artist = scanner.nextLine();
		// handle the edge case that an artist is not in library
		if (!lib.hasAlbumByArtist(artist)) {
			System.out.println("Can not find artist in library.");
			mainHome();
			return;
		}
		// get the albumByArtist list using library model method
		List<Album> songsList = lib.getAlbumsByArtist(artist);
		// display the albums
		displayAlbums(songsList);
		// go home
		mainHome();
	}
	
	// albumByTitle gets the albums with a certain title
	private static void albumByTitle() {
		System.out.println("Enter album title: \n");
		String title = scanner.nextLine();
		// handle exceptions
		if (!lib.hasAlbumByTitle(title)) {
			System.out.println("Can not find album in library.");
			mainHome();
			return;
		}
		// create songslist
		List<Album> songsList = lib.getAlbumsByTitle(title);
		// display albums
		displayAlbums(songsList);
		// jump home
		mainHome();
	}		
}
	
