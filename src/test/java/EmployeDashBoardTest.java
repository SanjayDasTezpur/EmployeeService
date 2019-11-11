import com.general.society.employee.service.Applicatioin;
import com.general.society.employee.service.dtos.Employee;
import com.general.society.employee.service.dtos.Employees;
import com.general.society.employee.service.entities.EmployeeEntity;
import com.general.society.employee.service.repository.EmployeeH2Repository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.concurrent.atomic.AtomicBoolean;

@ActiveProfiles("DEV")
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Applicatioin.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class EmployeDashBoardTest {

    EmployeeEntity employeeEntity;
    @Autowired
    private TestRestTemplate anonymousRestTemplate;
    @Autowired
    private EmployeeH2Repository employeeRepository;

    @Test
    public void checkHealth() throws Exception {
        ResponseEntity<String> response = anonymousRestTemplate.getForEntity("/actuator/health", String.class);
        Assert.assertTrue(response.getStatusCode().value() == 200);
    }

    @Before
    public void before() {
        employeeEntity = new EmployeeEntity(null, "AAA", "BBB", "M", "9th Nov 1992", "HPC");
        employeeEntity = employeeRepository.save(employeeEntity);
    }

    @Test
    public void testForGetAllEmployeesApi() {
        String url = "/api/v1/employee/all";
        ResponseEntity<Employees> response = anonymousRestTemplate.getForEntity(url, Employees.class);
        Employees body = response.getBody();
        Assert.assertNotNull("Should not be NULL", body);
        Assert.assertTrue(response.getStatusCode().value() == 200);
    }

    @Test
    public void testForRegisterApi() {
        String url = "/api/v1/register/employee";
        ResponseEntity<EmployeeEntity> response = anonymousRestTemplate.exchange(url, HttpMethod.POST, new HttpEntity<>(new Employee("PQE","PQR","","PQR","PQR")),  EmployeeEntity.class);
        EmployeeEntity body = response.getBody();
        Assert.assertNotNull("Should not be NULL", body);
        Assert.assertTrue(response.getStatusCode().value() == 201);

        testAfterReg();
    }

    private void testAfterReg() {
        String url = "/api/v1/employee/all";
        AtomicBoolean foundSavedEmployee = new AtomicBoolean(false);
        ResponseEntity<Employees> response = anonymousRestTemplate.getForEntity(url, Employees.class);
        Employees body = response.getBody();
        body.getEmployees().forEach(emp -> {
            if(emp.getFirstName().equalsIgnoreCase("PQE")){
                foundSavedEmployee.set(true);
            }
        });
        Assert.assertTrue("Emplyee Not registered correctly",foundSavedEmployee.get());
    }
}
