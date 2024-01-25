package cat.institutmarianao.shipmentsws.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.Formula;

import java.io.Serializable;
import java.util.List;

@Data
@NoArgsConstructor
@SuperBuilder
@Entity
@Table(name = "shipments")
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@JsonIgnoreProperties({"hibernateLazyInitializer"})
public class Shipment implements Serializable {
    public static final int MAX_DESCRIPTION = 500;
    public static final String PENDING = "PENDING";
    public static final String IN_PROCESS = "IN_PROCESS";
    public static final String DELIVERED = "DELIVERED";
    private static final long serialVersionUID = 1L;
    /* Lombok */
    @Id
    @EqualsAndHashCode.Include
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "category", nullable = false)
    @Enumerated(EnumType.STRING)
    private Category category;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sender_id")
    private Address sender;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "recipient_id")
    private Address recipient;
    @Column(name = "weight")
    private Float weight;
    @Column(name = "height")
    private Float height;
    @Column(name = "width")
    private Float width;
    @Column(name = "length")
    private Float length;
    @Column(name = "express")
    private Boolean express;
    @Column(name = "fragile")
    private Boolean fragile;
    @Column(name = "note")
    private String note;
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "actions", joinColumns = @JoinColumn(name = "id"), inverseJoinColumns = @JoinColumn(name = "shipment_id"))
    private List<Action> tracking;
    /* JPA */
    @Enumerated(EnumType.STRING) // Stored as string
    /* Hibernate */
    @Formula("(SELECT CASE a.type WHEN '" + Action.RECEPTION + "' THEN '" + PENDING + "' " + " WHEN '"
            + Action.ASSIGNMENT + "' THEN '" + IN_PROCESS + "' " + " WHEN '" + Action.DELIVERY + "' THEN '" + DELIVERED
            + "' ELSE NULL END FROM actions a "
            + " WHERE a.date=( SELECT MAX(last_action.date) FROM actions last_action "
            + " WHERE last_action.shipment_id=a.shipment_id AND last_action.shipment_id=id ))")
    // Lombok
    @Setter(AccessLevel.NONE)
    private Status status;

    public void addTracking(Action action) {
        this.tracking.add(action);
    }

    public enum Category {
        PARTICULAR, COMPANY, GOVERNMENT
    }

    public enum Status {
        PENDING, IN_PROCESS, DELIVERED
    }
}
