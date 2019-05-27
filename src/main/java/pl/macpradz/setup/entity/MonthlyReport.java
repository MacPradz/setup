package pl.macpradz.setup.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedNativeQuery;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Entity
@NamedQueries({
        @NamedQuery(name = "get_all",
                    query = "select mr from MonthlyReport mr"),
        @NamedQuery(name = "get_where_expenses_gt_1000",
                  query = "select mr from MonthlyReport mr where total_expenses > 1000"),
              })
public class MonthlyReport {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String description;
    private Date reportDate;
    private BigDecimal totalExpenses;

    @UpdateTimestamp
    private LocalDateTime lastUpdateDate;

    @CreationTimestamp
    private LocalDateTime createdDate;

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

    public LocalDateTime getLastUpdateDate() {
        return lastUpdateDate;
    }

    public MonthlyReport setLastUpdateDate(LocalDateTime lastUpdateDate) {
        this.lastUpdateDate = lastUpdateDate;
        return this;
    }

    public LocalDateTime getCreatedDate() {
        return createdDate;
    }

    public MonthlyReport setCreatedDate(LocalDateTime createdDate) {
        this.createdDate = createdDate;
        return this;
    }

    @Override
    public String toString() {
        return "MonthlyReport{" + "id=" + id + ", description='" + description + '\'' + ", reportDate=" + reportDate
                + ", totalExpenses=" + totalExpenses + '}';
    }
}
