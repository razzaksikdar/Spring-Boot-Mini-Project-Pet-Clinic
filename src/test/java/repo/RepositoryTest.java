package repo;


import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.qa.raz.petClinic.PetClinicApplication;
import com.qa.raz.petClinic.model.MySpringBootDataModel;
import com.qa.raz.petClinic.repository.MySpringBootRepository;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = {PetClinicApplication.class})
@DataJpaTest
public class RepositoryTest {
	
@Autowired
private TestEntityManager entityManager;

@Autowired
private MySpringBootRepository myRepo;

@Test
public void retriveByIdTest()
{
	MySpringBootDataModel model1 = new MySpringBootDataModel("Spax", "home", "dog", 50);
	entityManager.persist(model1);
	entityManager.flush();
	assertTrue(myRepo.findById(model1.getId()).isPresent());
	
}


@Test
public void retriveByNameTest()
{
	MySpringBootDataModel model2 = new MySpringBootDataModel("Mark", "tiger", "royal", 38);
	entityManager.persist(model2);
	entityManager.flush();
	
	MySpringBootDataModel found = myRepo.findByName(model2.getName());
	assertEquals("Mark", found.getName());

	System.out.println(found);
}

}