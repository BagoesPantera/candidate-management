package com.tera.candidatemanagement.vacancy.controller;

import com.tera.candidatemanagement.common.payload.ApiResponse;
import com.tera.candidatemanagement.vacancy.dto.CandidateRankingResponse;
import com.tera.candidatemanagement.vacancy.dto.VacancyRequest;
import com.tera.candidatemanagement.vacancy.dto.VacancyResponse;
import com.tera.candidatemanagement.vacancy.model.Vacancy;
import com.tera.candidatemanagement.vacancy.service.VacancyService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/vacancies")
@RequiredArgsConstructor
public class VacancyController {

    private final VacancyService vacancyService;

    @PostMapping
    public ResponseEntity<VacancyResponse> create(@RequestBody @Valid VacancyRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(vacancyService.create(request));
    }

    @GetMapping
    public ResponseEntity<List<VacancyResponse>> getAll() {
        return ResponseEntity.ok(vacancyService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Vacancy> getById(@PathVariable String id) {
        return ResponseEntity.ok(vacancyService.getById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<VacancyResponse> update(
            @PathVariable String id,
            @RequestBody @Valid VacancyRequest request) {
        return ResponseEntity.ok(vacancyService.update(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> delete(@PathVariable String id) {
        vacancyService.delete(id);
        return ResponseEntity.ok(ApiResponse.success(null, "Vacancy deleted successfully"));
    }

    @GetMapping("/{vacancyId}/rank-candidates")
    public List<CandidateRankingResponse> rankCandidates(@PathVariable String vacancyId) {
        return vacancyService.rankCandidates(vacancyId);
    }
}
