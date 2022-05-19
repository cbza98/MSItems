package com.bankntt.items.infraestructure.Intefaces;

import com.bankntt.items.domain.Entity.Item;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface IItemService {
	public Flux<Item> findAll();

	public Mono<Item> save(Item _account);

	public Mono<Item> findById(String Id);

	public Mono<Item> delete(String Id);
}
