package com.example.azurespring.Modle;



import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;


@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    //access level is set to NONE, so that the id cannot be changed
    private Long id;

    @Column(nullable = false, length = 100)
    private String name;

    @Column(nullable = false, length = 100)
    private Integer price;

    @Column(nullable = false, length = 100)
    private Integer weight;

    //cascade type persist means that if a product is deleted, all the orders that contain that product will be deleted as well
    //different between cascade type all and persist is that all will delete all the orders that contain that product, but persist will only delete the orders that contain that product

}