package pl.macpradz.setup.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import pl.macpradz.setup.entity.Spending;

@Repository
@Transactional
public class SpendingJpaRepository {

    @PersistenceContext
    EntityManager em;

    public List<Spending> findAll() {
        TypedQuery<Spending> namedQuery = em.createNamedQuery("find_all_spendings", Spending.class);
        return namedQuery.getResultList();
    }

    public Spending findById(Long id) {
        return em.find(Spending.class, id);
    }

    public void deleteById(Long id) {
        Spending spending = findById(id);
        em.remove(spending);
    }

    public Spending update(Spending spending) {
        return em.merge(spending);
    }

    public Spending insert(Spending spending) {
        return em.merge(spending);
    }
}
