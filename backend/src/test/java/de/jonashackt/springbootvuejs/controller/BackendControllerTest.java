package de.jonashackt.springbootvuejs.controller;

import de.jonashackt.springbootvuejs.SpringBootVuejsApplication;
import io.restassured.RestAssured;
import org.apache.http.HttpStatus;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit4.SpringRunner;

import static io.restassured.RestAssured.when;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

@RunWith(SpringRunner.class)
@SpringBootTest(
		classes = SpringBootVuejsApplication.class,
		webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT
)
public class BackendControllerTest {

	@LocalServerPort
	private int port;

	@Before
    public void init() {
        RestAssured.baseURI = "http://localhost";
        RestAssured.port = port;
    }

	@Test
	public void checkForLatvia() {
		when()
			.post("/api/getCountry/37129702792")
		.then()
			.statusCode(HttpStatus.SC_OK)
			.assertThat()
				.body(is(equalTo("Latvia")));
	}

	@Test
	public void checkForUsa() {
		when()
				.post("/api/getCountry/129702792")
				.then()
				.statusCode(HttpStatus.SC_OK)
				.assertThat()
				.body(is(equalTo("United States")));
	}

	@Test
	public void checkForRussia() {
		when()
				.post("/api/getCountry/7129702792")
				.then()
				.statusCode(HttpStatus.SC_OK)
				.assertThat()
				.body(is(equalTo("Russia")));
	}

}
