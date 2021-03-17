package com.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;

@Service
public class ExternalApiService {

    @Autowired
    private RestTemplate restTemplate;

    public List<Song> fetchDataFromExternalApi() {
        ResponseEntity<List<Song>> exchange = restTemplate.exchange(
                "http://localhost:8081/api/songs",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<Song>>() {}
        );
        List<Song> songs = exchange.getBody();

        return songs;
    }

    public Optional<Song> findSongByTitle(List<Song> songList, String title) {
        return songList.stream()
                .filter(song -> song.getTitle().equals(title)).findFirst();
    }
}
