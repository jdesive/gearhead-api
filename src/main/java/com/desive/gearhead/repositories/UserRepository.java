package com.desive.gearhead.repositories;

import com.desive.gearhead.entities.User;
import com.desive.gearhead.support.jpa.CustomJpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends CustomJpaRepository<User, Integer> {

	Optional<User> findByUsername(String username);

}
