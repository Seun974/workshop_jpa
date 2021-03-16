package se.lexicon.samuel.workshop_jpa.data;

import se.lexicon.samuel.workshop_jpa.entity.AppUser;

import java.util.Collection;

public interface AppUserDAO {
    AppUser findById(Integer appUserId);
    Collection<AppUser> findAll();
    AppUser create(AppUser appUser);
    AppUser update(AppUser appUser);
    void delete(Integer appUserId);//take not that this is void and it returns nothing
}
