package hu.nye.progkor.musiccatalog.web.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import hu.nye.progkor.musiccatalog.data.model.Song;
import hu.nye.progkor.musiccatalog.service.SongService;

/**
 * Controller for the music catalog.
 */
@Controller
@RequestMapping("/music-catalog")
public class MusicCatalogController {

    private final SongService songService;

    @Autowired
    public MusicCatalogController(SongService songService) {
        this.songService = songService;
    }

    /**
     * Shows the song editor screen.
     *
     * @param model the model object to store attributes
     * @param id the id of the song to retrieve
     * @return the name of the edit view to render
     */
    @GetMapping("/{id}")
    public String getSongById(Model model, @PathVariable Long id) {
        Optional<Song> optionalSong = songService.retrieveSongById(id);
        return optionalSong.map(song -> {
            model.addAttribute("song", song);
            return "music-catalog/edit";
        }).orElseGet(() -> {
            model.addAttribute("requestUri", "music-catalog/" + id);
            return "music-catalog/notFound";
        });
    }

    /**
     * Shows the song list screen.
     *
     * @param model the model object to store attributes
     * @return the name of the song list view to render
     */
    @GetMapping
    public String getAllSongs(Model model) {
        List<Song> allSongs = songService.retrieveAllSongs();
        model.addAttribute("songs", allSongs);
        return "music-catalog/list";
    }

    /**
     * Shows the song creation screen.
     *
     * @return the name of the song creation view to render
     */
    @GetMapping("/create")
    public String createSong() {
        return "music-catalog/create";
    }

    /**
     * Creates a new song.
     * Also navigates back to the editor screen.
     *
     * @param model the model object to store attributes
     * @param song the song object to create
     * @return the name of the edit view to render
     */
    @PostMapping("/create")
    public String createSong(Model model, Song song) {
        Song newSong = songService.createSong(song);
        model.addAttribute("song", newSong);
        return "music-catalog/edit";
    }

    /**
     * Updates an existing song.
     * Also navigates back to the editor screen.
     *
     * @param model the model object to store attributes
     * @param song the song object to update
     * @return the name of the edit view to render
     */
    @PostMapping(value = "/update", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public String updateSong(Model model, Song song) {
        Song updatedSong = songService.updateSong(song);
        model.addAttribute("song", updatedSong);
        return "music-catalog/edit";
    }

    /**
     * Deletes a song by ID.
     * Also navigates back to the song list screen.
     *
     * @param model the model object to store attributes
     * @param id the id of the song to delete
     * @return the name of the song list view to render
     */
    @GetMapping("/{id}/delete")
    public String deleteSongById(Model model, @PathVariable Long id) {
        songService.deleteSongById(id);
        List<Song> allSongs = songService.retrieveAllSongs();
        model.addAttribute("songs", allSongs);
        return "music-catalog/list";
    }
}
