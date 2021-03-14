package data;

import entity.Details;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.Collection;

@Repository
public class DetailsDAORepository implements DetailsDAO {

    private EntityManager em;

    @Autowired
    public DetailsDAORepository(EntityManager em) {
        this.em = em;
    }

    @Override
    @Transactional(readOnly = true)
    public Details findById(Integer detailsId) {
        return em.find(Details.class, detailsId);
    }

    @Override
    @Transactional(readOnly = true)
    public Collection<Details> findAll() {
        return em
                .createQuery("SELECT details FROM Details details", Details.class)
                .getResultList();
    }

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public Details create(Details details) {
        em.persist(details);
        return details;
    }

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public Details update(Details details) {
        return em.merge(details);
    }

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public boolean delete(Integer detailsId) {
        Details toDelete = findById(detailsId);
        if (toDelete != null) {
            em.remove(toDelete);
        } else {
            throw new IllegalArgumentException("Details was not found");
        }
        toDelete = findById(detailsId);
        return toDelete == null;
    }
}
