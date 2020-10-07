package ch.fhnw.eaf.rental.model;

import javax.persistence.*;

//@NamedQuery(name = BasicModel.FIND_ALL, query="SELECT b FROM Movie b")
@MappedSuperclass
public abstract class BasicModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //public static final String FIND_ALL = "Basic.all";

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
