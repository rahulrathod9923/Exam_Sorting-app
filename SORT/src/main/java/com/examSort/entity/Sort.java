package com.examSort.entity;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Sort {

	@Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
	Long id;
	
	String exam;
	
	LocalDate startDate;
	
	LocalDate endDate;
	
	LocalDate retroDate;

	@Override
	public String toString() {
		return "Sort [id=" + id + ", exam=" + exam + ", startDate=" + startDate + ", endDate=" + endDate
				+ ", retroDate=" + retroDate + "]";
	}

	public Sort() {
		super();
	}

	public Sort(Long id, String exam, LocalDate startDate, LocalDate endDate, LocalDate retroDate) {
		super();
		this.id = id;
		this.exam = exam;
		this.startDate = startDate;
		this.endDate = endDate;
		this.retroDate = retroDate;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getExam() {
		return exam;
	}

	public void setExam(String exam) {
		this.exam = exam;
	}

	public LocalDate getStartDate() {
		return startDate;
	}

	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}

	public LocalDate getEndDate() {
		return endDate;
	}

	public void setEndDate(LocalDate endDate) {
		this.endDate = endDate;
	}

	public LocalDate getRetroDate() {
		return retroDate;
	}

	public void setRetroDate(LocalDate retroDate) {
		this.retroDate = retroDate;
	}
}
