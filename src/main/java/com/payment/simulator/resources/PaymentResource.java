package com.payment.simulator.resources;

import com.payment.simulator.entities.Payment;
import com.payment.simulator.services.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping(value = "/payment")
public class PaymentResource {
	@Autowired
	private PaymentService paymentService;

	@GetMapping
	public ResponseEntity<List<Payment>> getPayments() {
		List<Payment> list = paymentService.getPayments();

		return ResponseEntity.ok().body(list);
	}

	@GetMapping(value = "/{id}")
	public ResponseEntity<Payment> getPaymentById(@PathVariable Long id) {
		Payment payment = paymentService.getPaymentById(id);

		return ResponseEntity.ok().body(payment);
	}

	@PostMapping
	public ResponseEntity<Payment> postPayment(@RequestBody Payment payment) {
		payment = paymentService.addPayment(payment);

		return ResponseEntity.ok().body(payment);
	}

}
