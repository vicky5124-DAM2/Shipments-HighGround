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
public class Receptionist extends User implements Serializable {
    public static final int MAX_PLACE = 100;
    private static final long serialVersionUID = 1L;
    @JoinColumn(name = "office_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Office office;

    @Column(name = "place")
    private String place;
}
