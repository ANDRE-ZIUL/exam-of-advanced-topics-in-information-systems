package br.edu.univas.topics.andre_dias_integration_test.dto;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
public class OrderDTO {
	private int orderNumber;
	private int productid;
	private int cpf;
	private int amount;
	private Date dateTimeSale;
	private float productValue;
	private boolean active;
}
