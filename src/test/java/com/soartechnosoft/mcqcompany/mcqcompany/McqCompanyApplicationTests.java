package com.soartechnosoft.mcqcompany.mcqcompany;

//import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

@SpringBootTest
@TestPropertySource(value="application-${spring.profiles.active}.properties")
class McqCompanyApplicationTests {


}
