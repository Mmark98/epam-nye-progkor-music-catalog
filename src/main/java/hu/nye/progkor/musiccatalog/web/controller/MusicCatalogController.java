package hu.nye.progkor.musiccatalog.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import hu.nye.progkor.musiccatalog.data.model.Song;
import hu.nye.progkor.musiccatalog.service.SongService;

@Controller
@RequestMapping("/music-catalog")
public class MusicCatalogController {

    private final SongService songService;

    @Autowired
    public MusicCatalogController(SongService songService) {
        this.songService = songService;
    }

    @GetMapping("/{id}")
    public String getSongById(Model model, @PathVariable Long id) {
        Song song = songService.retrieveSongById(id);
        model.addAttribute("song", song);
        return "edit";
    }

    @GetMapping
    public String getAllSongs(Model model) {
        List<Song> allSongs = songService.retrieveAllSongs();
        model.addAttribute("songs", allSongs);
        return "list";
    }

    @GetMapping("/create")
    public String createSong(Model model) {
        return "create";
    }

    @PostMapping("/create")
    public String createSong(Model model, @RequestBody Song song) {
        Song newSong = songService.createSong(song);
        model.addAttribute("song", newSong);
        return "edit";
    }

    @PostMapping(value = "/update", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public String updateSong(Model model, @RequestBody Song song) {
        Song updatedSong = songService.updateSong(song);
        return "edit";
    }

    @GetMapping("/{id}/delete")
    public String deleteSongById(Model model, @PathVariable Long id) {
        songService.deleteSongById(id);
        List<Song> allSongs = songService.retrieveAllSongs();
        model.addAttribute("songs", allSongs);
        return "list";
    }
}
