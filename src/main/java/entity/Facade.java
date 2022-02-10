package entity;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

public class Facade {
    EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu");

    public EmployeeEntity createEmployee(EmployeeEntity e) {
        EntityManager em = emf.createEntityManager();

        try{
            em.getTransaction().begin();
            em.persist(e);
            em.getTransaction().commit();
        } finally {
            em.close();
        }

        return e;
    }

    public String updateCustomerName(String newName, CustomerEntity c) {
        EntityManager em = emf.createEntityManager();

        CustomerEntity found = em.find(CustomerEntity.class,c.getCustomerNumber());
        found.setCustomerName(newName);

        try{
          em.getTransaction().begin();
          em.merge(found);
          em.getTransaction().commit();

          return em.find(CustomerEntity.class,c.getCustomerNumber()).getCustomerName();

        } finally {
          em.close();
        }

    }

    public int getEmployeeCount() {
        EntityManager em = emf.createEntityManager();

        TypedQuery<EmployeeEntity> tq = em.createQuery("SELECT COUNT(e) FROM EmployeeEntity e",EmployeeEntity.class);

        return (int) tq.getSingleResult();
    }
}
