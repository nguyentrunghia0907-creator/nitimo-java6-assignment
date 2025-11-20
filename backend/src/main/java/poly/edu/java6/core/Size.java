package poly.edu.java6.core;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "sizes")
public class Size {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "sizeId")
    private Integer sizeId;

    @Column(name = "name", length = 50, nullable = false, unique = true)
    private String name;
}
