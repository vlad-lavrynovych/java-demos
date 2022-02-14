package com.java_demos.taco_cloud.repository;

import com.java_demos.taco_cloud.domain.Taco;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TacoRepository extends CrudRepository<Taco, Long> {

    Taco save(Taco design);

}
