package data;

import entity.AppUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.Collection;

public class AppUserDAORepository implements AppUserDAO{


    private EntityManager em;


    @Autowired
    public AppUserDAORepository(EntityManager em) {
        this.em = em;
    }

    @Override
    @Transactional(readOnly = true)
    public AppUser findById(Integer appUserId) {
        return em.find(AppUser.class, appUserId);

    }

    @Override
    @Transactional(readOnly = true)
    public Collection<AppUser> findAll() {
        return em
                .createQuery("SELECT appUser FROM AppUser appUser", AppUser.class)
                .getResultList();
    }

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public AppUser create(AppUser appUser) {
        em.persist(appUser);
        return appUser;
    }

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public AppUser update(AppUser appUser) {
        return em.merge(appUser);

    }

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public boolean delete(Integer appUserId) {
        AppUser toDelete = findById(appUserId);
        if(toDelete != null){
            em.remove(toDelete);
        }else{
            throw new IllegalArgumentException("App user could not be found");
        }
        toDelete = findById(appUserId);
        return toDelete == null;
    }
}
