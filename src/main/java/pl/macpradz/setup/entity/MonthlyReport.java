package pl.macpradz.setup.entity;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class MonthlyReport {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String description;
    private Date reportDate;
    private BigDecimal totalExpenses;

    public MonthlyReport() {
    }

    public Long getId() {
        return id;
    }

    public MonthlyReport setId(Long id) {
        this.id = id;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public MonthlyReport setDescription(String description) {
        this.description = description;
        return this;
    }

    public Date getReportDate() {
        return reportDate;
    }

    public MonthlyReport setReportDate(Date reportDate) {
        this.reportDate = reportDate;
        return this;
    }

    public BigDecimal getTotalExpenses() {
        return totalExpenses;
    }

    public MonthlyReport setTotalExpenses(BigDecimal totalExpenses) {
        this.totalExpenses = totalExpenses;
        return this;
    }

    @Override
    public String toString() {
        return "MonthlyReport{" + "id=" + id + ", description='" + description + '\'' + ", reportDate=" + reportDate
                + ", totalExpenses=" + totalExpenses + '}';
    }
}
