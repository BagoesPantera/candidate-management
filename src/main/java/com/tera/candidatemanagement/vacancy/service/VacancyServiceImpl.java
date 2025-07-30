package com.tera.candidatemanagement.vacancy.service;

import com.tera.candidatemanagement.vacancy.dto.VacancyRequest;
import com.tera.candidatemanagement.vacancy.dto.VacancyResponse;
import com.tera.candidatemanagement.vacancy.exception.VacancyNotFoundException;
import com.tera.candidatemanagement.vacancy.model.Vacancy;
import com.tera.candidatemanagement.vacancy.repository.VacancyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class VacancyServiceImpl implements VacancyService {

    private final VacancyRepository vacancyRepository;

    @Override
    public VacancyResponse create(VacancyRequest request) {
        Vacancy vacancy = new Vacancy();
        vacancy.setName(request.getName());
        vacancy.setCriteriaList(request.getCriteriaList());
        Vacancy saved = vacancyRepository.save(vacancy);
        return mapToResponse(saved);
    }

    @Override
    public List<VacancyResponse> getAll() {
        return vacancyRepository.findAll()
                .stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    @Override
    public Vacancy getById(String id) {
        return vacancyRepository.findById(id)
                .orElseThrow(() -> new VacancyNotFoundException("Vacancy not found."));
    }

    @Override
    public VacancyResponse update(String id, VacancyRequest request) {
        Vacancy vacancy = vacancyRepository.findById(id)
                .orElseThrow(() -> new VacancyNotFoundException(id));

        vacancy.setName(request.getName());
        vacancy.setCriteriaList(request.getCriteriaList());
        Vacancy updated = vacancyRepository.save(vacancy);
        return mapToResponse(updated);
    }

    @Override
    public void delete(String id) {
        if (!vacancyRepository.existsById(id)) {
            throw new VacancyNotFoundException(id);
        }
        vacancyRepository.deleteById(id);
    }

    private VacancyResponse mapToResponse(Vacancy vacancy) {
        return new VacancyResponse(
                vacancy.getId(),
                vacancy.getName(),
                vacancy.getCriteriaList()
        );
    }
}

