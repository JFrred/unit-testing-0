package com.example;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class SongService {

    private List<Song> favSongs;
    private final ExternalApiService externalApiService;

    public SongService(ExternalApiService externalApiService) {
        favSongs = new ArrayList<>();
        this.externalApiService = externalApiService;
    }

    public List<Song> getFavSongs() {
        return favSongs;
    }

    public List<Song> getAllSongs() {
        return externalApiService.fetchDataFromExternalApi();
    }

    public List<Song> getListSortedByArtist() {
        return null;
    }

    public void addSongToFav(Song song) {
        favSongs.add(song);
    }

    public void deleteSongFromFav(String title) {
        Song songToDelete = favSongs.stream().filter(song -> song.getTitle().equals(title)).findFirst()
                .orElseThrow(() -> new SongNotFoundException("This song is not on your fav list"));
        favSongs.remove(songToDelete);
    }
}
