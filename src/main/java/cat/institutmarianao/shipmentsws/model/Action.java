package cat.institutmarianao.shipmentsws.model;

import java.io.Serializable;
import java.util.Date;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@NoArgsConstructor
@SuperBuilder
@Entity
@DiscriminatorColumn(name = "type", discriminatorType = DiscriminatorType.STRING)
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Table(name = "actions")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public abstract class Action implements Serializable {

	private static final long serialVersionUID = 1L;

	/* Values for type - MUST be constants */
	public static final String RECEPTION = "RECEPTION";
	public static final String ASSIGNMENT = "ASSIGNMENT";
	public static final String DELIVERY = "DELIVERY";

	public enum Type {
		RECEPTION, ASSIGNMENT, DELIVERY
	}

	/* Lombok */
	@EqualsAndHashCode.Include

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	protected Long id;

	@Column(name = "type", nullable = false, insertable=false, updatable=false)
	@Enumerated(EnumType.STRING)
	protected Type type;

	//@Column(name = "performer_username", nullable = false)
	@OneToOne(fetch = FetchType.LAZY)
	@PrimaryKeyJoinColumn(name = "performer_username")
	protected User performer;

	@Column(name = "date", nullable = false)
	protected Date date = new Date();

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "shipment_id", nullable = false)
	protected Shipment shipment;

	//@Column(name = "tracking_number")
	//private Integer trackingNumber;

	//@ManyToOne(fetch = FetchType.LAZY)
	//@JoinColumn(name = "courier_username")
	//private User courier;

	//@Column(name = "priority")
	//private Integer priority;
}
