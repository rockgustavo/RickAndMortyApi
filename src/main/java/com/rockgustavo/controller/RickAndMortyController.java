package com.rockgustavo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rockgustavo.model.Episode;
import com.rockgustavo.model.RickAndMorty;
import com.rockgustavo.service.RickAndMortyService;

@RestController
@RequestMapping("/")
public class RickAndMortyController {

	@Autowired
	RickAndMortyService service;

	@GetMapping(value = "/character/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public RickAndMorty findById(@PathVariable(value = "id") String id) {
		return service.findCharacterId(id);
	}
	
	@GetMapping(value = "/characterComplete/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public RickAndMorty findByIdComplete(@PathVariable(value = "id") String id) {
		return service.findCharacterIdComplete(id);
	}
	
	@GetMapping(value = "/episode/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public Episode findEpisodeById(@PathVariable(value = "id") String id) {
		return service.findEpisodeId(id);
	}
	
	@GetMapping("/character")
	public List<RickAndMorty> restListAllCharacteres() {
		return service.findAllChararacteres();
	}
	
	@GetMapping("/episode")
	public List<Episode> restListAllEpisode() {
		return service.findAllEpisodes();
	}
}
