package com.examSort.service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.examSort.entity.Sort;
import com.examSort.repo.Repo;

@Service
public class SortService {

    @Autowired
    Repo repo;

    public Sort saveExam(Sort sort) {
        return repo.save(sort);
    }

    public List<Sort> getAllExams() {
        return repo.findAll();
    }

//    public List<Sort> getAllSortedExams() {
//        LocalDate today = LocalDate.now();
//
//        List<Sort> sortedExams = repo.findAll().stream()
//                .sorted(Comparator.comparing((Sort s) -> Math.abs(today.compareTo(s.getEndDate())))
//                        .thenComparing((Sort s) -> Math.abs(today.compareTo(s.getRetroDate())))
//                        .thenComparing(Sort::getStartDate)
//                        .thenComparing(s -> s.getExam().charAt(0)))
//                .collect(Collectors.toList());
//
//        return sortedExams;
//    } 

    public List<Sort> getAllSortedExams() {
        LocalDate today = LocalDate.now();

        List<Sort> sortedExams = repo.findAll().stream()
                .sorted((s1, s2) -> {
                    if (s1.getEndDate().isAfter(today) && s2.getEndDate().isAfter(today)) {
                        if (s1.getRetroDate().isAfter(today) && s2.getRetroDate().isAfter(today)) {
                            return s1.getStartDate().compareTo(s2.getStartDate());
                        } else {
                            return s1.getRetroDate().compareTo(s2.getRetroDate());
                        }
                    } else {
                        return s1.getEndDate().compareTo(s2.getEndDate());
                    }
                })
                .collect(Collectors.toList());

        return sortedExams;
    }

    public Sort updateExam(Long id, Sort sort) {
        if (repo.existsById(id)) {
            sort.setId(id);
            return repo.save(sort);
        } else {
            return null; // Handle not found scenario
        }
    }

    public Sort deleteExam(Long id) {
        Sort sort = repo.findById(id).orElse(null);
        if (sort != null) {
        	repo.delete(sort);
        }
        return sort;
    }
}

