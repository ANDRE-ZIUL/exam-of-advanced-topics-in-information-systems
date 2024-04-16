package br.edu.univas.main.Andre.util;

import org.springframework.stereotype.Component;

import br.edu.univas.main.Andre.dto.OrderDTO;
import br.edu.univas.main.Andre.entities.OrderEntity;

@Component
public class OrderEntityConverter {
	public static OrderDTO toDTO(OrderEntity order) {
		return new OrderDTO(
				order.getOrderNumber(), order.getProductCode(),
				order.getCpf(),order.getAmount(), 
				order.getDateTimeSale(),
				order.getValue(), order.isActive()
				);
	}
	
	public OrderEntity toEntity(OrderDTO order) {
		return new OrderEntity(
				order.getOrderNumber(), order.getProductCode(),
				order.getAmount(),order.getCpf(), 
				order.getDateTimeSale(), order.getValue(), order.isActive()
				);
	}
}
