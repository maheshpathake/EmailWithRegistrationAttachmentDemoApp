package com.maildemoattach.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.maildemoattach.model.Employee;


@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

}
