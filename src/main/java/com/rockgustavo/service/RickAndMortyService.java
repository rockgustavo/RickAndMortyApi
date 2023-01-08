package com.rockgustavo.service;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.rockgustavo.model.Episode;
import com.rockgustavo.model.ListEpisode;
import com.rockgustavo.model.ListRickAndMorty;
import com.rockgustavo.model.RickAndMorty;

@Service
public class RickAndMortyService {
	
	private Logger logger = Logger.getLogger(RickAndMortyService.class.getName());
	
	
	public RickAndMorty findCharacterId(String id) {
		logger.info("Finding one character by id");
		
		RestTemplate restTemplate = new RestTemplateBuilder()
				.rootUri("https://rickandmortyapi.com/api/character")
				.build();
		RickAndMorty response = restTemplate.getForObject("/{id}", RickAndMorty.class, id);

		return response;
	}
	
	public RickAndMorty findCharacterIdComplete(String id) {
		logger.info("Finding one character by id");
		
		RestTemplate restTemplate = new RestTemplateBuilder()
				.rootUri("https://rickandmortyapi.com/api/character")
				.build();
		RickAndMorty response = restTemplate.getForObject("/{id}", RickAndMorty.class, id);
		
		List<Episode> episodios = new ArrayList<>();
		response.getEpisode().forEach(url -> {
			episodios.add(findEpisodeURL(url));
		});
		response.setEpisodes(episodios);
		
		return response;
	}
	
	public Episode findEpisodeId(String id) {
		logger.info("Finding one character by id");
		
		RestTemplate restTemplate = new RestTemplateBuilder()
				.rootUri("https://rickandmortyapi.com/api/episode")
				.build();
		
		return restTemplate.getForObject("/{id}", Episode.class, id);
	}
	
	public List<RickAndMorty> findAllChararacteres() {
		logger.info("Finding All Characteres");
		

		RestTemplate restTemplate = new RestTemplateBuilder()
				.rootUri("https://rickandmortyapi.com/api/character")
				.build();
		
		ListRickAndMorty obj = restTemplate.getForObject("/", ListRickAndMorty.class);
		
		return obj.getResults();
	}
	
	public List<Episode> findAllEpisodes() {
		logger.info("Finding All Episodes");
		
		RestTemplate restTemplate = new RestTemplateBuilder()
				.rootUri("https://rickandmortyapi.com/api/episode")
				.build();
		ListEpisode obj = restTemplate.getForObject("/", ListEpisode.class);
		
		return obj.getResults();
	}
	
	public Episode findEpisodeURL(String url) {
		RestTemplate restTemplate = new RestTemplateBuilder()
				.rootUri(url)
				.build();
		
		return restTemplate.getForObject("/", Episode.class);
	}
}
