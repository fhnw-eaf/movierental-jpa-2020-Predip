package ch.fhnw.eaf.rental.model;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import ch.fhnw.eaf.rental.json.PriceCategoryDeserializer;
import ch.fhnw.eaf.rental.json.PriceCategorySerializer;

import javax.persistence.*;

@JsonDeserialize(using = PriceCategoryDeserializer.class)
@JsonSerialize(using = PriceCategorySerializer.class)
@Entity
@Table(name = "PRICECATEGORIES")
@DiscriminatorColumn(name = "PRICECATEGORY_TYPE")
public abstract class PriceCategory {
	@Id @GeneratedValue(strategy= GenerationType.IDENTITY)
	@Column(name="PRICECATEGORY_ID")
	private Long id;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public abstract double getCharge(int daysRented);

	public int getFrequentRenterPoints(int daysRented) {
		return 1;
	}
}
