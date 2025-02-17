package Music;

import java.util.ArrayList;
import java.util.List;

public class Library<T> {
    private MusicStore store;
    private ArrayList<T> library;

    public Library() {
        store = new MusicStore();
        library = new ArrayList<T>();
    }

    public void addPlayList(String name) {
        PlayList playList = new PlayList(name);
        library.add((T) playList);
    }

    public List<Song> searchPlayListByName(String name) {
        for (T items : library) {
            if (items instanceof PlayList) {
                PlayList playList = (PlayList) items;
                if (name.equals(playList.getName())) {
                    return playList.getSongs();
                }
            }
        }
        return null;
    }

    public void addSong(Song song) {
    	// if song exists in musicStore
    	if (store.getSongByTitle(song.getTitle())[0] == song.getTitle()) {
    		library.add((T) song);
        }
    }
}