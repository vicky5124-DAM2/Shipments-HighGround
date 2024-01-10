package cat.institutmarianao.shipmentsws.model;

import java.io.Serializable;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

/* Lombok */
@Data
@NoArgsConstructor
@SuperBuilder
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity(name = "Address")
public class Address implements Serializable {

	private static final long serialVersionUID = 1L;

	/* Lombok */
	@EqualsAndHashCode.Include
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	protected Long id;

	@Column(name = "name", nullable = false)
	private String name;

	@Column(name = "street", nullable = false)
	private String street;

	@Column(name = "number")
	private String number;

	@Column(name = "floor")
	private String floor;

	@Column(name = "door")
	private String door;

	@Column(name = "city", nullable = false)
	private String city;

	@Column(name = "province")
	private String province;

	@Column(name = "postal_code")
	private String postalCode;

	@Column(name = "country", nullable = false)
	private String country;
}
