package com.geekglassess.trustnetwork.repository;

import com.geekglassess.trustnetwork.entities.Topic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TopicRepository extends JpaRepository<Topic, Long> {
    @Override
    <S extends Topic> List<S> saveAll(Iterable<S> entities);
}
