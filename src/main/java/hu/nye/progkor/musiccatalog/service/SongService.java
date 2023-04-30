package hu.nye.progkor.musiccatalog.service;

import java.util.List;
import java.util.Optional;

import hu.nye.progkor.musiccatalog.data.model.Song;

/**
 * A service for managing songs in the music catalog.
 */
public interface SongService {

    /**
     * Creates a new song.
     *
     * @param song the song to create
     * @return the created song
     */
    Song createSong(Song song);

    /**
     * Retrieves a song by its id.
     *
     * @param id the id of the song to retrieve
     * @return the retrieved song or empty optional if it was not found
     */
    Optional<Song> retrieveSongById(Long id);

    /**
     * Retrieves all the songs in the catalog.
     *
     * @return the list of found songs
     */
    List<Song> retrieveAllSongs();

    /**
     * Updates an existing song.
     *
     * @param song the song to update
     * @return the updated song
     */
    Song updateSong(Song song);

    /**
     * Deletes a song by its id.
     *
     * @param id the id of the song to delete
     */
    void deleteSongById(Long id);
}
