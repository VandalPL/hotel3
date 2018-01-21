package edu.uph.ii.platformy.models;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "supply")
@Getter
@Setter
@AllArgsConstructor
public class Supply {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public long id;

    private String name;

    private int ilosc;
    public Supply(){}


}
