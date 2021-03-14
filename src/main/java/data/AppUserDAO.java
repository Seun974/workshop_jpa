package data;

import entity.AppUser;

import java.util.Collection;

public interface AppUserDAO {
    AppUser findById(Integer appUserId);
    Collection<AppUser> findAll();
    AppUser create(AppUser appUser);
    AppUser update(AppUser appUser);
    boolean delete(Integer appUserId);//take not that this is void and it returns nothing
}
