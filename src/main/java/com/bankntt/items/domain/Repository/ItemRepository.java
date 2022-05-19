package com.bankntt.items.domain.Repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

import com.bankntt.items.domain.Entity.Item;

import reactor.core.publisher.Mono;

public interface ItemRepository extends ReactiveMongoRepository<Item, String> {
	public Mono<Long> countByItemPartnerCodeAndType(String bsPartnerCode, String type);
}
