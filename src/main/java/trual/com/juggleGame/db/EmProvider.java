package trual.com.juggleGame.db;


import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class EmProvider {
    private static EmProvider instance = null;
    private static EntityManagerFactory emf;
    private EmProvider(){
        emf = Persistence.createEntityManagerFactory("juggleGame");
    }
    public static EmProvider getInstance()
    {
        if(instance == null)
        {
            instance = new EmProvider();
        }
        return instance;
    }

    public void closeEmProvider() {
        if (instance != null){
            emf.close();
        }
    }

    public EntityManager createEntityManger(){
        return emf.createEntityManager();
    }
}
