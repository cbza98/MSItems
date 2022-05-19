package com.bankntt.items.infraestructure.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.bankntt.items.domain.Entity.Item;
import com.bankntt.items.domain.Repository.ItemRepository;
import com.bankntt.items.infraestructure.Intefaces.IItemService;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class ItemService implements IItemService {

	@Autowired(required = true)
	private ItemRepository repository;

	@Override
	public Flux<Item> findAll() {
		return repository.findAll();
	}

	@Override
	public Mono<Item> save(Item _request) {
		
		return Mono.just(_request).flatMap(r->{
					
			Item a =  Item.builder()
						.codeBars(r.getCodeBars())
					  	.frgnName(r.getFrgnName())
					  	.invntItem(r.getInvntItem())
					  	.itemName(r.getItemName())
					  	.itemType(r.getItemType())
					  	.itmsGrpCod(r.getItmsGrpCod())
					  	.prchseItem(r.getPrchseItem())
					  	.sellItem(r.getSellItem())
					  	.valid(true)
					  	.build();
							 
			return repository.save(a);						
		});


	}

	@Override
	public Mono<Item> delete(String Id) {
		return repository.findById(Id).flatMap(deleted -> repository.delete(deleted).then(Mono.just(deleted)));
	}

	@Override
	public Mono<Item> findById(String Id) {
		return repository.findById(Id);
	}

	public Mono<ResponseEntity<Item>> update(String id, Item _request) {
		
		return repository.findById(id).flatMap(r -> {
			Item a =  Item.builder()
					.codeBars(r.getCodeBars())
				  	.frgnName(r.getFrgnName())
				  	.invntItem(r.getInvntItem())
				  	.itemName(r.getItemName())
				  	.itemType(r.getItemType())
				  	.itmsGrpCod(r.getItmsGrpCod())
				  	.prchseItem(r.getPrchseItem())
				  	.sellItem(r.getSellItem())
				  	.valid(true)
				  	.build();
			return repository.save(a);
		}).map(updated -> new ResponseEntity<>(updated, HttpStatus.OK))
				.defaultIfEmpty(new ResponseEntity<>(HttpStatus.OK));
	}

	
}
