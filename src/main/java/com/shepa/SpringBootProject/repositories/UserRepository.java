package com.shepa.SpringBootProject.repositories;

import com.shepa.SpringBootProject.model.Users;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<Users, Long> {
    Users findOneByUserName(String userName);
}
