package trual.com.juggleGame.model;

import lombok.Data;
import trual.com.juggleGame.db.EmProvider;
import trual.com.juggleGame.dto.UserDto;

import java.io.Serializable;
import java.util.List;
import javax.persistence.*;

@Data
@Entity
@Table(name = "customer")
public class Customer implements Serializable {

    ///@GeneratedValue(strategy = GenerationType.AUTO)needed for auto generation
    @Id
    @Column(name = "custID", unique = true)
    private int id;

    @Column(name = "firstName", nullable = false)
    private String fName;

    @Column(name = "lastName", nullable = false)
    private String lName;



    public static void addCustomer(int id, String fname, String lname, EmProvider emf) {
        EntityManager em = emf.createEntityManger();
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

    public static Customer getCustomer(int id, EmProvider emf) {
        EntityManager em = emf.createEntityManger();
        String query = "SELECT c FROM Customer c WHERE c.id = :custID";
        TypedQuery<Customer> tq = em.createQuery(query, Customer.class);
        tq.setParameter("custID", id);
        Customer customer = null;
        try {
            customer = tq.getSingleResult();
            return customer;
        }
        catch (NoResultException ex) {
            ex.printStackTrace();
            return null;
        }
        finally {
            em.close();
        }

    }

	public static void getCustomers(EmProvider emf){
		EntityManager em = emf.createEntityManger();
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

	public static void changeFName(int id, String fname, EmProvider emf) {
		EntityManager em = emf.createEntityManger();
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

	public static void delete(int id, EmProvider emf) {
		EntityManager em = emf.createEntityManger();
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
