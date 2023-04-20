package hu.nye.progkor.musiccatalog.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import hu.nye.progkor.musiccatalog.data.model.Song;
import hu.nye.progkor.musiccatalog.service.SongService;

@RestController
@RequestMapping("/api/v1/song")
public class MusicCatalogRestController {

    private final SongService songService;

    @Autowired
    public MusicCatalogRestController(SongService songService) {
        this.songService = songService;
    }

    @GetMapping("/{id}")
    public Song getSongById(@PathVariable Long id) {
        return songService.retrieveSongById(id);
    }

    @GetMapping
    public List<Song> getAllSongs() {
        return songService.retrieveAllSongs();
    }

    @PostMapping
    public Song createSong(@RequestBody Song song) {
        return songService.createSong(song);
    }

    @PutMapping
    public Song updateSong(@RequestBody Song song) {
        return songService.updateSong(song);
    }

    @DeleteMapping("/{id}")
    public void deleteSongById(@PathVariable Long id) {
        songService.deleteSongById(id);
    }
}
