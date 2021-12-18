package trual.com.juggleGame;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import trual.com.juggleGame.model.Customer;

import javax.persistence.*;
import java.util.List;

@SpringBootApplication
public class JuggleGameApplication {
	private static EntityManagerFactory ENTITY_MANAGER_FACTORY = Persistence
			.createEntityManagerFactory("juggleGame");


	public static void main(String[] args) {
		//SpringApplication.run(JuggleGameApplication.class, args);

		addCustomer(1, "Alex", "trujillo");
		getCustomer(1);

		ENTITY_MANAGER_FACTORY.close();
	}

	public static void addCustomer(int id, String fname, String lname) {
		EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
		EntityTransaction et = null;
		try {
			et = em.getTransaction();
			et.begin();
			Customer customer = new Customer();
			customer.setId(id);
			customer.setFName(fname);
			customer.setLName(lname);
			em.persist(customer);
			et.commit();
		}
		catch (Exception ex) {
			if (et != null) {
				et.rollback();
			}
			ex.printStackTrace();
		}
		finally {
			em.close();
		}
	}

	public static void getCustomer(int id) {
		EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
		String query = "SELECT c FROM Customer c WHERE c.id = :custID";
		TypedQuery<Customer> tq = em.createQuery(query, Customer.class);
		tq.setParameter("custID", id);
		Customer customer = null;
		try {
			customer = tq.getSingleResult();
			System.out.println(customer.getFName() + " " + customer.getLName());
		}
		catch (NoResultException ex) {
			ex.printStackTrace();
		}
		finally {
			em.close();
		}
	}

	public static void getCustomers(){
		EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
		String query = "SELECT C FROM Customer c WHERE c.id IS NOT NULL";
		TypedQuery<Customer> tq = em.createQuery(query, Customer.class);
		List<Customer> customers;
		try {
			customers = tq.getResultList();
			customers.forEach(customer -> System.out.println(customer.getFName() + " " + customer.getLName()));
		}
		catch (NoResultException ex) {
			ex.printStackTrace();
		}
		finally {
			em.close();
		}

	}

	public static void changeFName(int id, String fname) {
		EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
		EntityTransaction et = null;
		Customer customer = null;
		try {
			et = em.getTransaction();
			et.begin();
			customer = em.find(Customer.class, id);
			customer.setFName(fname);

			em.persist(customer);
			et.commit();
		}
		catch (Exception ex) {
			if (et != null) {
				et.rollback();
			}
			ex.printStackTrace();
		}
		finally {
			em.close();
		}
	}

	public static void delete(int id) {
		EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
		EntityTransaction et = null;
		Customer customer = null;
		try {
			et = em.getTransaction();
			et.begin();
			customer = em.find(Customer.class, id);
			em.remove(customer);
			em.persist(customer);
			et.commit();
		}
		catch (Exception ex) {
			if (et != null) {
				et.rollback();
			}
			ex.printStackTrace();
		}
		finally {
			em.close();
		}
	}

}
