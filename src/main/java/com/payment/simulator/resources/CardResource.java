package com.payment.simulator.resources;

import com.payment.simulator.entities.Card;
import com.payment.simulator.services.CardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping(value = "/card")
public class CardResource {
	@Autowired
	private CardService cardService;

	@GetMapping
	public ResponseEntity<List<Card>> getCards() {
		List<Card> list = cardService.getCards();
		return ResponseEntity.ok().body(list);
	}

	@GetMapping(value = "/{id}")
	public ResponseEntity<Card> getCardById(@PathVariable Long id) {
		Card obj = cardService.getCardById(id);
		return ResponseEntity.ok().body(obj);
	}

	@PostMapping
	public ResponseEntity<Card> postCard(@RequestBody Card card) {
		card = cardService.addCard(card);

		return ResponseEntity
				.status(HttpStatus.CREATED)
				.body(card);
	}

	@PutMapping(value = "/{id}")
	public ResponseEntity<Card> putCard(@PathVariable Long id, @RequestBody Card card) {
		card = cardService.updateCardById(id, card);

		return ResponseEntity
				.status(HttpStatus.OK)
				.body(card);
	}

	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> deleteCard(@PathVariable Long id) {
		cardService.deleteCardById(id);

		return ResponseEntity.noContent().build();
	}
}
