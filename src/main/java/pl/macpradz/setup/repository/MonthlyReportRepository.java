package pl.macpradz.setup.repository;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import pl.macpradz.setup.entity.MonthlyReport;

@Repository
@Transactional
public class MonthlyReportRepository {

    @Autowired
    private EntityManager em;

    public MonthlyReport findById(Long id) {
        return em.find(MonthlyReport.class, id);
    };

    public void deleteById(Long id) {
        MonthlyReport report = findById(id);
        em.remove(report);
    }

    public void save(MonthlyReport monthlyReport) {
        if (monthlyReport.getId() == null) {
            em.persist(monthlyReport);
        } else {
            em.merge(monthlyReport);
        }
    }

    public void transactionWorksBehindTheHood() {
        MonthlyReport monthlyReport =
                new MonthlyReport().setReportDate(new Date()).setDescription("nowy").setTotalExpenses(BigDecimal.TEN);
        em.persist(monthlyReport);
        monthlyReport.setDescription("a jednak sie zudejtuje");
    }

    public void useFlushDetachAndClear() {
        MonthlyReport monthlyReport =
                new MonthlyReport().setReportDate(new Date()).setDescription("nowszy").setTotalExpenses(BigDecimal.ONE);
        em.persist(monthlyReport);
        em.flush();

        monthlyReport.setDescription("zupdejtuje sie");
        em.flush();

        MonthlyReport monthlyReport2 =
                new MonthlyReport().setReportDate(new Date()).setDescription("bedzie odczepiony").setTotalExpenses(BigDecimal.ONE);
        em.persist(monthlyReport2);
        em.flush();

        em.detach(monthlyReport2);
        monthlyReport2.setDescription("this will not have effect");
        em.flush();

//        em.clear(); == em.detach(monthlyReport); && em.detach(monthlyReport2);
    }

    public void useFlushRefresh() {
        MonthlyReport monthlyReport =
                new MonthlyReport().setReportDate(new Date()).setDescription("useFlushRefresh").setTotalExpenses(BigDecimal.ONE);
        em.persist(monthlyReport);
        em.flush();

        monthlyReport.setDescription("to zostanie olane bo przywracam stan z bazy - sflushowany");
        em.refresh(monthlyReport);
    }

    public List<MonthlyReport> findAll() {
        return em.createNamedQuery("get_all", MonthlyReport.class).getResultList();
    }

    public List<MonthlyReport> findAllWhereExpansesGT1000() {
        return em.createNamedQuery("get_where_expenses_gt_1000", MonthlyReport.class).getResultList();
    }

    public List<MonthlyReport> findAllWhereExpansesBetween(int botLimit, int topLimit) {
        Query query = em.createNativeQuery("select * from monthly_report mr where total_expenses < ? and "
                        + "total_expenses > ?",
                MonthlyReport.class);
        query.setParameter(1, topLimit);
        query.setParameter(2, botLimit);
        return query.getResultList();
    }

    public List<MonthlyReport> findAllWhereExpansesBetween2(int botLimit, int topLimit) {
        Query query = em.createNativeQuery("select * from monthly_report mr where total_expenses < :topLimit and total_expenses > :botLimit",
                MonthlyReport.class);
        query.setParameter("topLimit", topLimit);
        query.setParameter("botLimit", botLimit);
        return query.getResultList();
    }
}
