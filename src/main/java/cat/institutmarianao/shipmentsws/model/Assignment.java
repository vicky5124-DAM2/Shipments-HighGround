package cat.institutmarianao.shipmentsws.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;

@Data
@SuperBuilder
@NoArgsConstructor
@Entity
@Table(name = "actions")
@EqualsAndHashCode(callSuper = true)
public class Assignment extends Action implements Serializable {
    public static final int MIN_PRIORITAT = 1;
    public static final int MAX_PRIORITAT = 3;
    private static final long serialVersionUID = 1L;
    @JoinColumn(name = "courier_username")
    @OneToOne(fetch = FetchType.LAZY)
    private Courier courier;

    @Column(name = "priority")
    private Integer priority;
}
