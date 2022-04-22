package com.project.rpgstoreback.repository;

import com.project.rpgstoreback.models.Tag;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
public interface TagRepository extends CrudRepository<Tag, Long> {
}
