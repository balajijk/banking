package com.eltropy.bank.ebank.repository;

import com.eltropy.bank.ebank.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {

    Role findByName(String name);


}

