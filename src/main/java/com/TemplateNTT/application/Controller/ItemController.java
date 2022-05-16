package com.TemplateNTT.application.Controller;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.TemplateNTT.domain.Entity.Item;
import com.TemplateNTT.infraestructure.Services.ItemService;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/ItemService/Item")
public class ItemController {
	@Autowired
	private ItemService service;

	@GetMapping
	public Mono<ResponseEntity<Flux<Item>>> findAll() {
		return Mono.just(ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(service.findAll()));
	}

	@GetMapping("/{id}")
	public Mono<Item> findById(@PathVariable String id) {
		return service.findById(id);
	}

	@PostMapping
	public Mono<ResponseEntity<Map<String, Object>>> create(@Valid @RequestBody Mono<Item> request) {
		Map<String, Object> response = new HashMap<>();

		return request.flatMap(a -> {
			return service.save(a).map(c -> {
				response.put("Item", c);
				response.put("Message", "Item has been created successfuly");
				return ResponseEntity.created(URI.create("/ItemService/Item".concat(c.getItemId().toString())))
						.contentType(MediaType.APPLICATION_JSON).body(response);
			});
		});
	}
	
	
	@PutMapping("/{id}")
	public Mono<ResponseEntity<Item>> update(@PathVariable String id, @RequestBody Item request) {
		return service.update(id, request);

	}

	@DeleteMapping("/{id}")
	public Mono<ResponseEntity<Void>> delete(@PathVariable String id) {
		return service.delete(id).map(r -> ResponseEntity.ok().<Void>build())
				.defaultIfEmpty(ResponseEntity.notFound().build());
	}
	

		
		
}
