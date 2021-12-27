package edu.kpi.iasa.ka97.group_service.model;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;

import javax.persistence.*;


@Entity
@Builder
@AllArgsConstructor
@Table(name = "dgroup")
public class Group {
    public Group() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "idtrain")
    private Long idtrain;

    @NotNull
    @Column(name = "min_age")
    private Long min_age;


    @Column(name = "max_age")
    private Long max_age;

    @NotNull
    @Column(name = "id_hall")
    private Long id_hall;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getIdtrain() {
        return idtrain;
    }

    public void setIdtrain(Long idtrain) {
        this.idtrain = idtrain;
    }

    public Long getMin_age() {
        return min_age;
    }

    public void setMin_age(Long min_age) {
        this.min_age = min_age;
    }

    public Long getMax_age() {
        return max_age;
    }

    public void setMax_age(Long max_age) {
        this.max_age = max_age;
    }

    public Long getId_hall() {
        return id_hall;
    }

    public void setId_hall(Long id_hall) {
        this.id_hall = id_hall;
    }


}