package cat.institutmarianao.shipmentsws.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;

@Data
@NoArgsConstructor
@SuperBuilder
@Entity
@Table(name = "users")
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "role", discriminatorType = DiscriminatorType.STRING)
@JsonIgnoreProperties({"hibernateLazyInitializer"})
public class User implements Serializable { // TODO: abstract?
    // Values for role - Must be final
    public static final String RECEPTIONIST = "RECEPTIONIST";
    public static final String LOGISTICS_MANAGER = "LOGISTICS_MANAGER";
    public static final String COURIER = "COURIER";
    public static final int MIN_USERNAME = 2;
    public static final int MAX_USERNAME = 25;
    public static final int MIN_PASSWORD = 4;
    public static final int MIN_FULL_NAME = 3;
    public static final int MAX_FULL_NAME = 100;
    public static final int MAX_EXTENSION = 9999;
    private static final long serialVersionUID = 1L;
    @Id
    @EqualsAndHashCode.Include
    @Column(name = "username", nullable = false)
    protected String username;
    @Enumerated(EnumType.STRING)
    @Column(name = "role", nullable = false, insertable = false, updatable = false)
    protected Role role;
    @Column(name = "password", nullable = false)
    protected String password;
    @Column(name = "full_name", nullable = false)
    protected String fullName;
    @Column(name = "extension")
    protected Integer extension;

    public enum Role {
        RECEPTIONIST, LOGISTICS_MANAGER, COURIER
    }
}
