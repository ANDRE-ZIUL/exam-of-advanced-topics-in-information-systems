package br.edu.univas.main.Andre.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatus;
import br.edu.univas.main.Andre.dto.OrderDTO;
import br.edu.univas.main.Andre.entities.OrderEntity;
import br.edu.univas.main.Andre.service.OrderService;
import br.edu.univas.main.Andre.util.OrderEntityConverter;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

	@Autowired
	private OrderService service;

	@GetMapping("")
	@ResponseStatus(HttpStatus.OK)
	public List<OrderDTO> getAllOrders() {
		return service.findAll();
	}

	@GetMapping("/{orderNumber}")
	public OrderDTO getOrderById(@PathVariable Integer orderNumber) {
		OrderEntity entity = service.findById(orderNumber);
		return OrderEntityConverter.toDTO(entity);
	}

	@GetMapping("/active")
	@ResponseStatus(HttpStatus.OK)
	public List<OrderDTO> getAllOrdersActive() {
		return service.findByActive(true);
	}

	@PostMapping("")
	@ResponseStatus(HttpStatus.CREATED)
	public void createOrder(@RequestBody OrderDTO order) {
		service.createOrder(order);
	}

	@PutMapping("/{orderNumber}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void updateOrder(@RequestBody OrderDTO dto, @PathVariable Integer orderNumber) {
		service.updateOrder(dto, orderNumber);
	}
}
