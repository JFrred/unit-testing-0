package com.example;

import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class ExternalApiServiceTest {

    @Test
    void shouldFindSongByTitle() {
        // given
        ExternalApiService externalApiService = new ExternalApiService();
        List<Song> songs = List.of(
                new Song("Dark Tranquillity", "Atoma"),
                new Song("In Flames", "Wallflower"),
                new Song("Amorphis", "Message In Amber"),
                new Song("Aether Realm", "GoodBye"),
                new Song("Dark Tranquillity", "Encircled"),
                new Song("Avatar", "Black Waltz"));

        // when
        Optional<Song> songsByTitle = externalApiService.findSongByTitle(songs, "Atoma");

        // then
        assertFalse(songsByTitle.isEmpty());

    }

}