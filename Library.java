package Music;

import java.util.ArrayList;
import java.util.List;

public class Library<Object> {
    private MusicStore store;
    private ArrayList<Object> library;

    public Library() {
        store = new MusicStore();
        library = new ArrayList<Object>();
    }

    public void addPlayList(String name) {
        PlayList playList = new PlayList(name);
        library.add( (Object) playList);
    }

    public List<Song> searchPlayListByName(String name) {
        for (Object items : library) {
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
    		library.add((Object) song);
        }
    }
}
