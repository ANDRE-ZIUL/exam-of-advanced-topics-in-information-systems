package br.edu.univas.topics.andre_dias_integration_test;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import java.util.Date;
import org.springframework.http.HttpStatus;
import br.edu.univas.topics.andre_dias_integration_test.dto.OrderDTO;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

class AndreDiasIntegrationTestApplicationTests {

	private final String orderURL = "/orders";

	@BeforeAll
	public static void setup() {
		RestAssured.baseURI = "http://localhost:8080/api";
	}

	@Test
	public void testSuccessPostOrder() {
		Date date = new Date();
		OrderDTO order = new OrderDTO(123, 1234, 12345678, 1, date, 120, true);

		Response resp = RestAssured
				.given()
				.contentType(ContentType.JSON)
				.body(order)
				.post(orderURL);
		assertEquals(HttpStatus.CREATED.value(), resp.getStatusCode());
	}

	@Test
	public void testPostOrderExists() {
		Date date = new Date();
		OrderDTO order = new OrderDTO(123, 1234, 12345678, 1, date, 120, true);

		Response resp = RestAssured
				.given()
				.contentType(ContentType.JSON)
				.body(order)
				.post(orderURL);

		Response resp2 = RestAssured
				.given()
				.contentType(ContentType.JSON)
				.body(order)
				.post(orderURL);
		assertEquals(HttpStatus.CONFLICT.value(), resp2.getStatusCode());
	}

	@Test
	public void testPostOrderInvalidJSON() {
		Response resp = RestAssured
				.given()
				.contentType(ContentType.JSON)
				.body("{invalidJson}")
				.post(orderURL);
		assertEquals(HttpStatus.BAD_REQUEST.value(), resp.getStatusCode());
	}

	@Test
	public void testPutOrderUpdateActiveSuccess() {
		Date date = new Date();
		OrderDTO order = new OrderDTO(124, 1234, 12345679, 1, date, 120, true);

		Response resp = RestAssured
				.given()
				.contentType(ContentType.JSON)
				.body(order)
				.post(orderURL);
		assertEquals(HttpStatus.CREATED.value(), resp.getStatusCode());

		order.setActive(false);

		Response updateResp = RestAssured
				.given()
				.contentType(ContentType.JSON)
				.body(order)
				.put(orderURL + "/" + 124);
		assertEquals(HttpStatus.NO_CONTENT.value(), updateResp.getStatusCode());
	}

	@Test
	public void testPutOrderUpdateActiveFailure() {
		int noExistingOrderNumber = 2;
		OrderDTO order = new OrderDTO(1, 1234, 12345679, 1, new Date(), 120, false);

		Response resp = RestAssured
				.given()
				.contentType(ContentType.JSON)
				.body(order)
				.post(orderURL);

		Response updateResp = RestAssured
				.given()
				.contentType(ContentType.JSON)
				.body(order)
				.put(orderURL + "/" + noExistingOrderNumber);
		assertEquals(HttpStatus.NOT_FOUND.value(), updateResp.getStatusCode());
	}

}
