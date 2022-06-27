package com.codegym.test.repository;

import com.codegym.test.model.entity.Branch;
import jdk.nashorn.internal.ir.annotations.Reference;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IBrachRepository extends JpaRepository<Branch, Long> {
}
