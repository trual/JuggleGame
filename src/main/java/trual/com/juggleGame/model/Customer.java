package trual.com.juggleGame.model;

import lombok.Data;

import java.io.Serializable;
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


}
