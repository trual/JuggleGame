package trual.com.juggleGame.model;

import lombok.Data;
import trual.com.juggleGame.db.EmProvider;
import trual.com.juggleGame.dto.TrickDto;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.Set;

@Data
@Entity
@Table(name = "Trick")
public class Trick implements Serializable {

    @Id
    @Column(name = "trickId", unique = true)
    private int id;

    @Column(name = "balls", nullable = false)
    private int balls;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "animation")
    private String animation;

    @ElementCollection (fetch = FetchType.EAGER)
    private Set<Integer> prereq;

    public Trick() {
    }

    public Trick(int id, int balls, String name, String animation, Set<Integer> prereq) {
        this.id = id;
        this.balls = balls;
        this.name = name;
        this.animation = animation;
        this.prereq = prereq;
    }

    public Trick(TrickDto trick) {
        this.id = trick.getId();
        this.balls = trick.getBalls();
        this.name = trick.getName();
        this.animation = trick.getAnimation();
        this.prereq = trick.getPrereq();
    }


    public static void addTrick(EmProvider emf, int id, int balls, String name, String animation, Set<Integer> prereq){
        EntityManager em = emf.createEntityManger();
        EntityTransaction et = null;
        try {
            et = em.getTransaction();
            et.begin();
            Trick trick = new Trick(id, balls, name, animation, prereq);
            em.persist(trick);
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

    public static Trick getTrick(EmProvider emf, int id) {
        EntityManager em = emf.createEntityManger();
        String query = "SELECT t FROM Trick t where t.id = :trickID";
        TypedQuery<Trick> tq = em.createQuery(query, Trick.class);
        tq.setParameter("trickID", id);
        Trick trick = null;
        try {
            trick = tq.getSingleResult();
            return trick;
        }
        catch (NoResultException ex) {
            ex.printStackTrace();
            return null;
        }
        finally {
            em.close();
        }
    }

    public static List<Trick> getAllTricks(EmProvider emf) {
        EntityManager em = emf.createEntityManger();
        String query = "SELECT t from Trick t WHERE t.id IS NOT NULL";
        TypedQuery<Trick> tq = em.createQuery(query, Trick.class);
        List<Trick> trickList;
        try {
            trickList = tq.getResultList();
            return trickList;
        }
        catch (NoResultException ex) {
            ex.printStackTrace();
            return  null;
        }
        finally {
            em.close();
        }
    }

    public static void deleteTrick(EmProvider emf, int id) {
        EntityManager em = emf.createEntityManger();
        EntityTransaction et = null;
        Trick trick = null;
        try {
            et = em.getTransaction();
            et.begin();
            trick = em.find(Trick.class, id);
            em.remove(trick);
            em.persist(trick);
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
