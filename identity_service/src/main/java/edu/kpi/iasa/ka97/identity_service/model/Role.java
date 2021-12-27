package edu.kpi.iasa.ka97.identity_service.model;


import com.sun.istack.NotNull;
import javax.persistence.*;

@Entity
@Table(name = "role")
public class Role {
    private Long id;
    private String name;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    public Long getId(){
        return id;
    }

    public void setId(Long id){
        this.id=id;
    }

    @NotNull
    @Column(name = "name")
    public String getName(){
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    @Override
    public String toString() {
        return "Role{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}






