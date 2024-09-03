package com.springbackend.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springbackend.exception.ResourceNotFoundException;
import com.springbackend.model.Issue;
import com.springbackend.repository.IssueRepository;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/v1/")
public class IssueController {
	
	@Autowired
	private IssueRepository issueRepository;
	
	//get all issues
	@GetMapping("/issues")
	public List<Issue> getAllIssues(){
		
		return issueRepository.findAll();
	}
	
	//create issue
	@PostMapping("/issues")
	public Issue createIssue(@RequestBody Issue issue) {
		return issueRepository.save(issue);
	}
	
	
	//Get ISSUE By ID`
	@GetMapping("/issues/{id}")
	public ResponseEntity<Issue> getIssueById(@PathVariable Long id) {

		Issue issue = issueRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Issue Not Found !!" + id));

		return ResponseEntity.ok(issue);

	}
	

	//Update Issue
	@PutMapping("/issues/{id}")
	public ResponseEntity<Issue> upadateIssue(@PathVariable Long id,
		@RequestBody Issue issueDetails) {

		Issue issue = issueRepository.findById(id)
			.orElseThrow(() -> new ResourceNotFoundException("Issue Not Found !!" + id));

			issue.setTitle(issueDetails.getTitle());
			issue.setDescription(issueDetails.getDescription());
			issue.setStatus(issueDetails.getStatus());
			issue.setAssigned_to(issueDetails.getAssigned_to());
			issue.setPriority(issueDetails.getPriority());
			issue.setType(issueDetails.getType());
			issue.setCreated_by(issueDetails.getCreated_by());
			issue.setCreated_at(issueDetails.getCreated_at());
			issue.setDue_date(issueDetails.getDue_date());

			Issue updatedIssueRequest = issueRepository.save(issue);
			return ResponseEntity.ok(updatedIssueRequest);

	}
	
	
	//Delete issue
	@DeleteMapping("/issues/{id}")
	public ResponseEntity<Map<String, Boolean>> deleteIssue(@PathVariable Long id){
		Issue issue = issueRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Issue Not Found !!" + id));
		
		issueRepository.delete(issue);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return ResponseEntity.ok(response);
	}
	
	

}
