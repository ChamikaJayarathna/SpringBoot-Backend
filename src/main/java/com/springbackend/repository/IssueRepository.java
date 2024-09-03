package com.springbackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.springbackend.model.Issue;

@Repository
public interface IssueRepository extends JpaRepository<Issue, Long>{

	
	
}
