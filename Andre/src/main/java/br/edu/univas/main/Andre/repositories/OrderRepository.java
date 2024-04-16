package br.edu.univas.main.Andre.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.edu.univas.main.Andre.entities.OrderEntity;

@Repository
public interface OrderRepository extends JpaRepository<OrderEntity, Integer> {
	public List<OrderEntity> findByActive(Boolean active);
}