package com.github.east196.starter;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.query.Param;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;


@CrossOrigin
@Validated
public interface PersonRepository extends JpaRepository<Person, Long>, JpaSpecificationExecutor<Person> {

	List<Person> findByLastName(@Param("name") String name);

}