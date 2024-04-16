package br.edu.univas.main.Andre.dto;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnore;

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
	private long orderNumber;
	private long productCode;
	private long cpf;
	private long amount;
	private Date dateTimeSale;
	private double value;
	
	@JsonIgnore
	private boolean active;
}
