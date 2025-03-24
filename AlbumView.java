package view;

import java.util.List;
import java.util.Scanner;

import dataStructures.Album;
import dataStructures.Song;
import model.LibraryModel;

public class AlbumView extends LibraryView {
	
	private static Scanner scanner = new Scanner(System.in);
	private static LibraryModel lib;
	
	public AlbumView(LibraryModel lib) {
		this.lib = lib;
	}
	
	public static void home() {
		System.out.println("[0] - open the albums in your library");
		System.out.println("[1] - search for albums in your library");
		System.out.println("[2] - return to homepage");
		
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
	
	private static void searchAlbums() {
		System.out.println("[0] - search for albums by title");
		System.out.println("[1] - search for albums by artist");
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
            System.out.println("enter number. \n");
            scanner.nextLine(); 
            home(); 
        }
		
	}
	
	// display albums in library
	private static void displayAlbumsInLib() {
		// check if library empty
		//libIsEmpty();
		// print albums
		for (int i = 0; i < lib.getAlbums().size();i++) {
			System.out.println("[" + i + "]" + " " + lib.getAlbums().get(i));
			//System.out.println("["+ i + "]" + " " + lib.getAlbums().get(i).getTitle() + ", " +
		//lib.getAlbums().get(i).getArtist() + ", " + lib.getAlbums().get(i).getGenre() + ", " +
		//			lib.getAlbums().get(i).getYear());
		}
		// go home
		mainHome();
	}
	// get albums by artist 
	private static void albumByArtist() {
		// check if library is empty
		//libIsEmpty();
		System.out.println("Enter album artist: \n");
		String artist = scanner.nextLine();
		// handle artist not being in library
		if (!lib.hasAlbumByArtist(artist)) {
			System.out.println("Can not find artist in library.");
			mainHome();
			return;
		}
		// get songslist using library model method
		List<Album> songsList = lib.getAlbumsByArtist(artist);
		// display the albums
		displayAlbums(songsList);
		// go home
		mainHome();
	}
	// get albums by title
	private static void albumByTitle() {
		//libIsEmpty();
		System.out.println("Enter album title: \n");
		String title = scanner.nextLine();
		// check if lib empty
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
	
