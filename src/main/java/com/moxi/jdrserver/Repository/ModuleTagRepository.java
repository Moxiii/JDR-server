package com.moxi.jdrserver.Repository;

import com.moxi.jdrserver.Models.ModuleTag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ModuleTagRepository extends JpaRepository<ModuleTag, Long> {

    List<ModuleTag> findModuleTagbyModuleId(Long moduleId);

}
