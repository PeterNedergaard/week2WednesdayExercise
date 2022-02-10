package entity;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class FacadeTest {
    Facade facade = new Facade();
    EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu");

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }


    @Test
    public void createEmployeeTest(){
        System.out.println("Create employee");
        EmployeeEntity expected = new EmployeeEntity(4321,"Jens","Jensen","1234","jens@jensen.dk","1",1234,"cook");
        EmployeeEntity employeeActual = new EmployeeEntity(4321,"Jens","Jensen","1234","jens@jensen.dk","1",1234,"cook");
        EmployeeEntity actual = facade.createEmployee(employeeActual);
        assertEquals(expected,actual);
    }

    @Test
    public void updateCustomerNameTest(){
        System.out.println("Update customer");
        EntityManager em = emf.createEntityManager();
        String expected = "Ole";
        String actual = facade.updateCustomerName("Ole",em.find(CustomerEntity.class,103));
        assertEquals(expected,actual);
    }

    @Test
    public void getEmployeeCountTest(){
        System.out.println("Get employee count");
        EntityManager em = emf.createEntityManager();

        List<EmployeeEntity> listOfEmployees = new ArrayList<>();
        TypedQuery<EmployeeEntity> tq = em.createQuery("SELECT e FROM EmployeeEntity e",EmployeeEntity.class);

        listOfEmployees = tq.getResultList();

        int expected = listOfEmployees.size();
        int actual = facade.getEmployeeCount();

        assertEquals(expected,actual);
    }

}