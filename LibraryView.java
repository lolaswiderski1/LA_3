package Music;

import java.util.List;
import java.util.Scanner;

public class LibraryView {
	
	    static MusicStore musicStore = new MusicStore();
	
		public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		// give initial options
		System.out.println("[0] - search for song by title");
		System.out.println("[1] - search for song by artist");
		System.out.println("[2] - search for album by title");
		System.out.println("[3] - search for album by title");
		// select choice
		int choice = scanner.nextInt();
		scanner.nextLine();
	
		switch (choice) {
			case 0:
				Scanner scanner0 = new Scanner(System.in);
				// get song title
				System.out.println("Enter song title: ");
				String songTitle = scanner0.nextLine();
				// list of songs in music store of specified title
				List<Song> songsByTitle = musicStore.getSongsByTitle(songTitle);
				// select desired song
				Scanner scanner01 = new Scanner(System.in);
				System.out.println("Select song: ");
				// print options ex: [1] Bohemian Rhapsody, Queen
				for (int i = 0; i < songsByTitle.size(); i ++) {
					System.out.println("[" + (i+1) + "] " + songsByTitle.get(i).
							getSongTitle() + ", " + songsByTitle.get(i).getArtist());
				}
				int selectedSong = scanner.nextInt();
				
		}
	}
}
