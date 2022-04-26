package com.project.rpgstoreback.repository;

import com.project.rpgstoreback.models.Tag;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
public interface TagRepository extends CrudRepository<Tag, Long> {

    @Modifying
    @Query("delete from Tag t where t.id = :id")
    void delete(@Param("id") Long entityId);

    Boolean existsByName(String name);
}
