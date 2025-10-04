package com.tri.freelancemp.repository;

import com.tri.freelancemp.entity.Role;
import com.tri.freelancemp.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Integer> {

    Optional<User> findByIdAndRole(int id, Role role);


}
