package cat.institutmarianao.shipmentsws.model;

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
@EqualsAndHashCode(callSuper = true)
@DiscriminatorValue("COURIER")
public class Courier extends User implements Serializable {
    private static final long serialVersionUID = 1L;

    @JoinColumn(name = "company_id")
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Company company;
}
