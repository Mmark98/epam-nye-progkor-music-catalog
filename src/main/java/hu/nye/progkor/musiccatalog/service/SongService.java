package hu.nye.progkor.musiccatalog.service;

import java.util.List;

import hu.nye.progkor.musiccatalog.data.model.Song;

/**
 * TODO.
 */
public interface SongService {

    /**
     * TODO.
     *
     * @param song TODO.
     * @return TODO.
     */
    Song createSong(Song song);

    Song retrieveSongById(Long id);

    List<Song> retrieveAllSongs();

    Song updateSong(Song song);

    void deleteSongById(Long id);
}
