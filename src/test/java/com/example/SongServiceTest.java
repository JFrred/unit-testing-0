package com.example;

import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;

class SongServiceTest {

    @Test
    void shouldReturnListOfSongs() {
        // given
        ExternalApiService externalApiService = mock(ExternalApiService.class);
        SongService songService = new SongService(externalApiService);
        BDDMockito.given(songService.getAllSongs()).willReturn(preparedMockDataForListOfSongs());

        // when
        List<Song> allSongs = songService.getAllSongs();

        // then
        assertFalse(allSongs.isEmpty());
    }

    @Test
    void shouldAddSongToFav() {
        // given
        ExternalApiService externalApiService = mock(ExternalApiService.class);
        SongService songService = new SongService(externalApiService);
        songService.addSongToFav(new Song("artist", "title"));

        // when
        List<Song> favSongs = songService.getFavSongs();

        // then
        assertFalse(favSongs.isEmpty());
    }

    @Test
    void shouldRemoveSongFromFav() {
        // given
        ExternalApiService externalApiService = mock(ExternalApiService.class);
        SongService songService = new SongService(externalApiService);
        List<Song> favSongs = songService.getFavSongs();
        favSongs.add(new Song("artist", "title"));
        songService.deleteSongFromFav("title");

        // then
        assertTrue(favSongs.isEmpty(), "List should be empty");
    }

    @Test
    void shouldThrowSongNotFoundExceptionIfListIsEmpty() {
        // given
        ExternalApiService externalApiService = mock(ExternalApiService.class);
        SongService songService = new SongService(externalApiService);

        // then
        assertThrows(SongNotFoundException.class, () -> songService.deleteSongFromFav(anyString()),
                "Should throw SongNotFoundException");
    }

//    @Test
//    void shouldReturnSortedListByArtist() {
//        // given
//        ExternalApiService externalApiService = mock(ExternalApiService.class);
//        SongService songService = new SongService(externalApiService);
//        BDDMockito.given(songService.getListSortedByArtist()).willReturn(null);
//
//        // when
//        List<Song> sortedListByArtist = songService.getListSortedByArtist();
//
//        // then
//
//    }

    private List<Song> preparedMockDataForListOfSongs() {
        return List.of(
                new Song("Dark Tranquillity", "Atoma"),
                new Song("In Flames", "Wallflower"),
                new Song("Amorphis", "Message In Amber"),
                new Song("Aether Realm", "GoodBye"),
                new Song("Dark Tranquillity", "Encircled"),
                new Song("Avatar", "Black Waltz")
        );
    }
}