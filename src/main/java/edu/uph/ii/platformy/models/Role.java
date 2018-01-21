package edu.uph.ii.platformy.models;

import lombok.*;

import javax.persistence.*;
import java.util.Set;

/**
 * Created by grzesiek on 23.08.2017.
 */

@Entity
@Table(name = "roles")
@Getter
@Setter
@NoArgsConstructor
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Enumerated(EnumType.STRING)//przechowywane w postaci string
    private Types type;
    @ManyToMany(mappedBy = "roles")
    private Set<User> users;

    public Role(Types type){
        this.type = type;
    }

    public enum Types{
        ROLE_ADMIN,
        ROLE_USER
    }

}
