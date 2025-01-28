package com.ecolink.spring.entity;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Mission {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
    private MissionType type;
    private Integer points;
    @ManyToMany
    @JoinTable(
        name = "mision_client", // Nombre de la tabla de unión
        joinColumns = @JoinColumn(name = "mision_id"), // Columna que referencia a Mision
        inverseJoinColumns = @JoinColumn(name = "client_id") // Columna que referencia a Client
    )
    private List<Client> clients;

    public Mission(String name, String description, MissionType type, Integer points){
        this.name = name;
        this.description = description;
        this.type = type;
        this.points = points;
    }
}
