package com.examSort.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.examSort.entity.Sort;

@Repository
public interface Repo extends JpaRepository<Sort, Long>{

}
