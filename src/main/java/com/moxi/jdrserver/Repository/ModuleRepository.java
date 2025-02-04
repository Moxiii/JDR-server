package com.moxi.jdrserver.Repository;

import com.moxi.jdrserver.Models.User;
import com.moxi.jdrserver.Models.Module;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ModuleRepository extends JpaRepository<Module, Integer> {

    List<Module> findByCreatedBy(User user);
}
