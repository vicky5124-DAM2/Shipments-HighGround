package cat.institutmarianao.shipmentsws.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@Entity
@Table(name = "offices")
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Office implements Serializable {
    public static final int MAX_NAME = 100;
    private static final long serialVersionUID = 1L;
    /* Lombok */
    @Id
    @EqualsAndHashCode.Include
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false, unique = true)
    private String name;
}
