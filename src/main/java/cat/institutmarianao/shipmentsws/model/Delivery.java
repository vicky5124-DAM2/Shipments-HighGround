package cat.institutmarianao.shipmentsws.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;

@Data
@NoArgsConstructor
@SuperBuilder
@Entity
@Table(name = "actions")
@EqualsAndHashCode(callSuper = true)
public class Delivery extends Action implements Serializable {
    private static final long serialVersionUID = 1L;
}
