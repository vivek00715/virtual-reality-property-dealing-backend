package com.hashedin.virtualproperty.application.repository;

import com.hashedin.virtualproperty.application.entities.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User, String> {

    @Query(value = "select count(*) from user_table where email = ?1 or mobile = ?2", nativeQuery = true)
    int countOfUsersByEmailOrMobile(String email, String mobile);
}
