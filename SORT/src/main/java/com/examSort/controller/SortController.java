package com.examSort.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.examSort.entity.Sort;
import com.examSort.service.SortService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;



@RestController
@RequestMapping("/sort")
public class SortController {

	@Autowired
	SortService sortService;
	
	@PostMapping("/save")
	public Sort saveExam(@RequestBody Sort sort) {
		return sortService.saveExam(sort);
	}
	
	@GetMapping("/getAll")
	public List<Sort> getAllExams() {
		return sortService.getAllExams();
	}
	
	
	@GetMapping("/sorted")
	public List<Sort> getAllSortedExams() {
		return sortService.getAllSortedExams();
	}
	
	@PutMapping("exam/{id}")
	public Sort updateExam(@PathVariable Long id, @RequestBody Sort sort) {
		return sortService.updateExam(id, sort);
	}
	
	@DeleteMapping
	public Sort deleteExam(@PathVariable Long id) {
		return sortService.deleteExam(id);
	}
}
