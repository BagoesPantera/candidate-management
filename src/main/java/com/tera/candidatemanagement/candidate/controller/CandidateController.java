package com.tera.candidatemanagement.candidate.controller;

import com.tera.candidatemanagement.candidate.service.CandidateService;
import com.tera.candidatemanagement.candidate.dto.CandidateRequest;
import com.tera.candidatemanagement.candidate.dto.CandidateResponse;
import com.tera.candidatemanagement.common.payload.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/candidates")
@RequiredArgsConstructor
public class CandidateController {

    private final CandidateService candidateService;

    @GetMapping
    public ResponseEntity<ApiResponse<List<CandidateResponse>>> getAll() {
        List<CandidateResponse> responses = candidateService.getAll().stream()
                .map(CandidateResponse::fromEntity)
                .toList();
        return ResponseEntity.ok(new ApiResponse<>(responses, "List of candidates"));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<CandidateResponse>> getById(@PathVariable String id) {
        CandidateResponse response = CandidateResponse.fromEntity(candidateService.getById(id));
        return ResponseEntity.ok(new ApiResponse<>(response, "Candidate found"));
    }

    @PostMapping
    public ResponseEntity<ApiResponse<CandidateResponse>> create(@Valid @ModelAttribute CandidateRequest request) {
        CandidateResponse response = CandidateResponse.fromEntity(candidateService.create(request));
        return ResponseEntity.status(HttpStatus.CREATED).body(new ApiResponse<>(response, "Candidate created"));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<CandidateResponse>> update(
            @PathVariable String id,
            @Valid @ModelAttribute CandidateRequest request) {
        CandidateResponse response = CandidateResponse.fromEntity(candidateService.update(id, request));
        return ResponseEntity.ok(new ApiResponse<>(response, "Candidate updated"));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> delete(@PathVariable String id) {
        candidateService.delete(id);
        return ResponseEntity.ok(new ApiResponse<>(null, "Candidate deleted"));
    }
}
