package org.launchcode.inventory.models.dao;

import javax.transaction.Transactional;

import org.launchcode.inventory.models.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Transactional
@Repository
public interface UserDao extends CrudRepository<User, Integer> {

    User findByUsername(String userName);

    User findByUid(int uid);

}
