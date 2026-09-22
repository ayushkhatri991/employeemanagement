package com.EmployeeManagement.Management;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Disabled("Disabled for cloud build environment where live database is not reachable")
class ManagementApplicationTests {

	@Test
	void contextLoads() {
	}

}
