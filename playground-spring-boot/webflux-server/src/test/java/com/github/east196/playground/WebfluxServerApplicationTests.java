package com.github.east196.playground;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class WebfluxServerApplicationTests {

	@Autowired
	private WebTestClient webTestClient;

	/**
	 * Spring 5 WebClient和WebTestClient使用教程
	 * https://blog.csdn.net/HiBoyljw/article/details/82783443
	 */
	@Test
	void contextLoads() {
		webTestClient.get().uri("/api/mvc")
				.exchange()
				.expectStatus().isOk()
				.expectBody().equals("hello mvc");
	}

}
