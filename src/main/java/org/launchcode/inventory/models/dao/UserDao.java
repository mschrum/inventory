package org.launchcode.inventory.models.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.launchcode.inventory.models.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Transactional
@Repository
public interface UserDao extends CrudRepository<User, Integer> {

    User findByUsername(String username);

    User findByUid(int uid);
    List<User> findAll();

}
