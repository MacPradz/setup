package pl.macpradz.setup.entity;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;

import pl.macpradz.setup.model.SpendingCategory;

@Entity
@NamedQuery(name="find_all_spendings", query="select s from Spending s")
public class Spending {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String description;
    private Date eventDate;
    private BigDecimal value;
    @Enumerated(EnumType.STRING)
    private SpendingCategory category;

    public Spending() {
    }

    public Long getId() {
        return id;
    }

    public Spending setId(Long id) {
        this.id = id;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public Spending setDescription(String description) {
        this.description = description;
        return this;
    }

    public Date getEventDate() {
        return eventDate;
    }

    public Spending setEventDate(Date eventDate) {
        this.eventDate = eventDate;
        return this;
    }

    public BigDecimal getValue() {
        return value;
    }

    public Spending setValue(BigDecimal value) {
        this.value = value;
        return this;
    }

    public SpendingCategory getCategory() {
        return category;
    }

    public Spending setCategory(SpendingCategory category) {
        this.category = category;
        return this;
    }

    @Override
    public String toString() {
        return "Spending{" + "id=" + id + ", description='" + description + '\'' + ", eventDate=" + eventDate
                + ", value=" + value + ", category=" + category + '}';
    }
}
