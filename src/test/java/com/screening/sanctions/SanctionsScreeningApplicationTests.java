package com.screening.sanctions;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SanctionsScreeningApplicationTests {

	@Autowired
	private ScreeningController controller;

	@Test
	void contextLoads() throws Exception {
		Assertions.assertNotNull(controller);
	}

}
