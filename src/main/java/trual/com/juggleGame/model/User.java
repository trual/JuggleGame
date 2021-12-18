package trual.com.juggleGame.model;

import lombok.Data;
import trual.com.juggleGame.db.EmProvider;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
@Table(name = "juggler")
public class User implements Serializable {

    @Id
    @Column(name = "userId", unique = true)
    private int id;

    @Column(name = "firstName", nullable = false)
    private String firstName;

    @Column(name = "lastName", nullable = false)
    private String lastName;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "phone")
    private String phone;

    public User() {
    }

    private User(int id, String firstName, String lastName, String email, String phone) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phone = phone;
    }

    public static void addUser(EmProvider emf, int id, String firstName, String lastName, String email, String phone){
        EntityManager em = emf.createEntityManger();
        EntityTransaction et = null;
        try {
            et = em.getTransaction();
            et.begin();
            User user = new User(id, firstName, lastName, email, phone);
            em.persist(user);
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

    public static User getUser(EmProvider emf, int id){
        EntityManager em = emf.createEntityManger();
        String query = "SELECT j FROM User j WHERE j.id = :userID";
        TypedQuery<User> tq = em.createQuery(query, User.class);
        tq.setParameter("userID", id);
        User user = null;
        try {
            user = tq.getSingleResult();
            return user;
        }
        catch (NoResultException ex) {
            ex.printStackTrace();
            return null;
        }
        finally {
            em.close();
        }
    }

}
