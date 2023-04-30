package hu.nye.progkor.musiccatalog.data.repository.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import hu.nye.progkor.musiccatalog.data.model.Song;
import hu.nye.progkor.musiccatalog.data.repository.Repository;

/**
 * Map-based in-memory implementation of {@link Repository}.
 */
@org.springframework.stereotype.Repository
public class InMemorySongRepository implements Repository<Song, Long> {

    private static final Map<Long, Song> STORAGE = new HashMap<>();

    @Override
    public Song save(Song song) {
        Long id = STORAGE.size() + 1L;
        song.setId(id);
        STORAGE.put(id, song);
        return STORAGE.get(id);
    }

    @Override
    public Optional<Song> getById(Long id) {
        return Optional.ofNullable(STORAGE.get(id));
    }

    @Override
    public List<Song> getAll() {
        return STORAGE.values().stream().toList();
    }

    @Override
    public Song update(Song song) {
        Long id = song.getId();
        STORAGE.put(id, song);
        return STORAGE.get(id);
    }

    @Override
    public void deleteById(Long id) {
        STORAGE.remove(id);
    }
}
