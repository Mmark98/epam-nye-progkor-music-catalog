package hu.nye.progkor.musiccatalog.service.impl;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import hu.nye.progkor.musiccatalog.data.model.Genre;
import hu.nye.progkor.musiccatalog.data.model.Song;
import hu.nye.progkor.musiccatalog.data.repository.Repository;
import hu.nye.progkor.musiccatalog.service.SongService;

/**
 * Unit tests for {@link DefaultSongService}.
 */
class DefaultSongServiceTest {

    private static final Long DUMMY_SONG_ID = 1L;
    private static final Song DUMMY_SONG = new Song(DUMMY_SONG_ID, "title", "artist", "album", Genre.CLASSICAL);

    @Mock
    private Repository<Song, Long> songRepository;

    private SongService underTest;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        underTest = new DefaultSongService(songRepository);
    }

    @Test
    void createSongShouldDelegateToTheRepositoryAndReturnSavedSong() {
        // Given
        given(songRepository.save(DUMMY_SONG)).willReturn(DUMMY_SONG);

        // When
        final Song actual = underTest.createSong(DUMMY_SONG);

        // Then
        assertThat(actual, equalTo(DUMMY_SONG));
        verify(songRepository).save(DUMMY_SONG);
        verifyNoMoreInteractions(songRepository);
    }

    @Test
    void retrieveSongByIdShouldDelegateToTheRepositoryAndReturnFoundSong() {
        // Given
        given(songRepository.getById(DUMMY_SONG_ID)).willReturn(Optional.of(DUMMY_SONG));

        // When
        final Optional<Song> actual = underTest.retrieveSongById(DUMMY_SONG_ID);

        // Then
        assertThat(actual, equalTo(Optional.of(DUMMY_SONG)));
        verify(songRepository).getById(DUMMY_SONG_ID);
        verifyNoMoreInteractions(songRepository);
    }

    @Test
    void retrieveAllSongsShouldDelegateToTheRepositoryAndReturnAllFoundSongs() {
        // Given
        given(songRepository.getAll()).willReturn(List.of(DUMMY_SONG));

        // When
        final List<Song> actual = underTest.retrieveAllSongs();

        // Then
        assertThat(actual, equalTo(List.of(DUMMY_SONG)));
        verify(songRepository).getAll();
        verifyNoMoreInteractions(songRepository);
    }

    @Test
    void updateSong() {
    }

    @Test
    void deleteSongById() {
    }
}
