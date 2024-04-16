package br.edu.univas.main.Andre.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import br.edu.univas.main.Andre.support.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.univas.main.Andre.dto.OrderDTO;
import br.edu.univas.main.Andre.entities.OrderEntity;
import br.edu.univas.main.Andre.repositories.OrderRepository;
import br.edu.univas.main.Andre.support.OrderException;
import br.edu.univas.main.Andre.util.OrderEntityConverter;

@Service
public class OrderService {

	private OrderRepository repo;

	@Autowired
	private OrderEntityConverter converter;

	@Autowired
	public OrderService(OrderRepository repo) {
		this.repo = repo;
	}

	public List<OrderDTO> findAll() {
		return repo.findAll().stream().map(OrderEntityConverter::toDTO) 
				.collect(Collectors.toList());
	}

	public OrderEntity findById(Integer orderNumber) {
		Optional<OrderEntity> obj = repo.findById(orderNumber);
		OrderEntity entity = obj.orElseThrow(() -> new ObjectNotFoundException("Object not found: " + orderNumber));
		return entity;
	}

	public List<OrderDTO> findByActive(boolean b) {
		return repo.findByActive(true).stream().map(OrderEntityConverter::toDTO).collect(Collectors.toList());
	}

	public void createOrder(OrderDTO order) {
		repo.save(converter.toEntity(order));
	}

	public void updateOrder(OrderDTO order, Integer orderNumber) {
		if (orderNumber == null || order == null || !orderNumber.equals(order.getOrderNumber())) {
			throw new OrderException("Invalid order number.");
		}
		OrderEntity existingObj = findById(orderNumber);
		updateOrderData(existingObj, order);
		repo.save(existingObj);
	}

	private void updateOrderData(OrderEntity existingObj, OrderDTO newObj) {
		existingObj.setActive(newObj.isActive());
	}
}
