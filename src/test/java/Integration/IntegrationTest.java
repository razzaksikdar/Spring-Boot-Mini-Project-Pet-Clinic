package Integration;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.qa.raz.petClinic.PetClinicApplication;
import com.qa.raz.petClinic.model.MySpringBootDataModel;
import com.qa.raz.petClinic.repository.MySpringBootRepository;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.hamcrest.CoreMatchers.is;



@RunWith(SpringRunner.class)
@SpringBootTest(classes = {PetClinicApplication.class})
@AutoConfigureMockMvc
public class IntegrationTest {

	@Autowired
	private MockMvc mvc;
	
	@Autowired
	private MySpringBootRepository repository;
	
	@Before
	public void clearDB() {
		repository.deleteAll();
	}
	
	@Test
	public void findingAndRetrievingpetsFromDatabase()throws Exception
	{
		
		repository.save(new MySpringBootDataModel("Dale", "bird", "parrot", 2));
		mvc.perform( get("/api/pets").contentType(MediaType.APPLICATION_JSON))
		.andExpect(status().isOk())
		.andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
		.andExpect(jsonPath("$[0].name", is("Dale")));
	}
	
	@Test
	public void addApetsToDatabaseTest() throws Exception {
		mvc.perform(MockMvcRequestBuilders.post("/api/pets").contentType(MediaType.APPLICATION_JSON)
				.content("{\"name\" : \"lolypop\", \"petType\" : \"snake\",  \"breed\" : \"cobra\", \"age\":2000}"))
		.andExpect(status().isOk())
		.andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
		.andExpect(jsonPath("$.name", is("lolypop")));
				
	}
	
}
