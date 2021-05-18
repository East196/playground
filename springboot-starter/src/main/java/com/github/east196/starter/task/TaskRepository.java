package com.github.east196.starter.task;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

import com.github.east196.starter.Person;

@CrossOrigin
@RepositoryRestResource()
public interface TaskRepository extends JpaRepository<Person, Long>, JpaSpecificationExecutor<Person> {

}
