
package model;

import com.google.gson.*;
import dataStructures.Song;
import dataStructures.PlayList;
import dataStructures.Album;

import java.lang.reflect.Type;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.ArrayList;

public class LibraryModelSerializer implements JsonSerializer<LibraryModel>, JsonDeserializer<LibraryModel> {
	
	@Override
	public JsonElement serialize(LibraryModel library, Type type, JsonSerializationContext jSContext) {
		JsonObject jsonObject = new JsonObject();
		
		// Serialize songs
        JsonObject songsObject = new JsonObject();
        for (Map.Entry<Song, LibraryModel.Rating> entry : library.getSongsMap().entrySet()) {
            Song song = entry.getKey();
            songsObject.add(song.toString(), jSContext.serialize(entry.getValue()));
        }
        jsonObject.add("songs", songsObject);
        
        // Serialize playLists
        jsonObject.add("playLists", jSContext.serialize(library.getPlayLists()));

        // Serialize recents
        jsonObject.add("recents", jSContext.serialize(library.getRecents()));

        // Serialize frequents
        jsonObject.add("frequents", jSContext.serialize(library.getFrequents()));
        
        // Serialize favoritesPlayList
        jsonObject.add("favoritesPlayList", jSContext.serialize(library.getFavoritesPlayList()));

        // Serialize albums
        jsonObject.add("albums", jSContext.serialize(library.getAlbums()));

        // Serialize favorites
        jsonObject.add("favorites", jSContext.serialize(library.getFavorites()));

        // Serialize playedSongs
        jsonObject.add("playedSongs", jSContext.serialize(library.getPlayedSongs()));
        
        // Serialize genres
        JsonObject genresObject = new JsonObject();
        for (Map.Entry<String, List<Song>> entry : library.getGenres().entrySet()) {
            String genre = entry.getKey();
            List<Song> songs = entry.getValue();
            genresObject.add(genre, jSContext.serialize(songs));
        }
        jsonObject.add("songs", songsObject);
        
        return jsonObject;
	}
	
	@Override
	public LibraryModel deserialize(JsonElement json, Type type, JsonDeserializationContext jDsContext) {
		JsonObject jsonObject = json.getAsJsonObject();
        LibraryModel library = new LibraryModel();
        
        // Use Gson for deserialization of nested objects
        Gson gson = new Gson();
        
        // Deserialize songs
        JsonObject songsObject = jsonObject.getAsJsonObject("songs");
        for (Map.Entry<String, JsonElement> entry : songsObject.entrySet()) {
            String[] parts = entry.getKey().split(", ");
            Song song = new Song(parts[0], parts[1], parts[2], parts[3]);
            LibraryModel.Rating rating = jDsContext.deserialize(entry.getValue(), LibraryModel.Rating.class);
            library.addSong(song);
            library.rateSong(song, rating);
        }
        
        // Deserialize recents
        PlayList recents = gson.fromJson(jsonObject.get("recents"), PlayList.class);
        library.setRecents(recents);
        
        // Deserialize frequents
        PlayList frequents = jDsContext.deserialize(jsonObject.get("frequents"), PlayList.class);
        library.setFrequents(frequents);
        
        // Deserialize favoritesPlayList
        PlayList favoritesPlayList = jDsContext.deserialize(jsonObject.get("favoritesPlayList"), PlayList.class);
        library.setFavoritesPlayList(favoritesPlayList);
        
        // Deserialize playLists
        JsonArray playListsArray = jsonObject.getAsJsonArray("playLists");
        for (JsonElement element : playListsArray) {
            PlayList playList = gson.fromJson(element, PlayList.class);
            if (!library.hasPlaylist(playList.getName())) {
            	library.addPlaylist(playList);
            }
     
        }

        // Deserialize albums
        JsonArray albumsArray = jsonObject.getAsJsonArray("albums");
        for (JsonElement element : albumsArray) {
            Album album = jDsContext.deserialize(element, Album.class);
            library.addAlbum(album);
        }
        
        // Deserialize favorites
        JsonArray favoritesArray = jsonObject.getAsJsonArray("favorites");
        for (JsonElement element : favoritesArray) {
            Song favorite = jDsContext.deserialize(element, Song.class);
            library.addFavorite(favorite);
        }

        // Deserialize playedSongs
        JsonArray playedSongsArray = jsonObject.getAsJsonArray("playedSongs");
        for (JsonElement element : playedSongsArray) {
            Song playedSong = jDsContext.deserialize(element, Song.class);
            library.playSong(playedSong);
        }
        
        return library;
	}
}
