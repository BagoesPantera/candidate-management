package com.tera.candidatemanagement.vacancy.service;

import com.tera.candidatemanagement.candidate.model.Candidate;
import com.tera.candidatemanagement.candidate.repository.CandidateRepository;
import com.tera.candidatemanagement.vacancy.dto.CandidateRankingResponse;
import com.tera.candidatemanagement.vacancy.dto.VacancyRequest;
import com.tera.candidatemanagement.vacancy.dto.VacancyResponse;
import com.tera.candidatemanagement.vacancy.enums.CriterionType;
import com.tera.candidatemanagement.vacancy.evaluator.AgeCriterionEvaluator;
import com.tera.candidatemanagement.vacancy.evaluator.CriterionEvaluator;
import com.tera.candidatemanagement.vacancy.evaluator.GenderCriterionEvaluator;
import com.tera.candidatemanagement.vacancy.evaluator.SalaryRangeCriterionEvaluator;
import com.tera.candidatemanagement.vacancy.exception.VacancyNotFoundException;
import com.tera.candidatemanagement.vacancy.model.Criterion;
import com.tera.candidatemanagement.vacancy.model.Vacancy;
import com.tera.candidatemanagement.vacancy.repository.VacancyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class VacancyServiceImpl implements VacancyService {

    private final VacancyRepository vacancyRepository;
    private final CandidateRepository candidateRepository;

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
                .orElseThrow(() -> new VacancyNotFoundException(id));
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

    private final Map<CriterionType, CriterionEvaluator> evaluators = Map.of(
            CriterionType.AGE, new AgeCriterionEvaluator(),
            CriterionType.GENDER, new GenderCriterionEvaluator(),
            CriterionType.SALARY_RANGE, new SalaryRangeCriterionEvaluator()
    );

    @Override
    public List<CandidateRankingResponse> rankCandidates(String vacancyId) {
        Vacancy vacancy = getById(vacancyId);
        List<Criterion> criteriaList = vacancy.getCriteriaList();

        List<CandidateRankingResponse> result = new ArrayList<>();
        int page = 0;
        int pageSize = 100;
        Page<Candidate> candidatesPage;

        do {
            Pageable pageable = PageRequest.of(page, pageSize);
            candidatesPage = candidateRepository.findAll(pageable);

            List<CandidateRankingResponse> tempList = candidatesPage.getContent().parallelStream()
                    .map(candidate -> {
                        int score = 0;
                        for (Criterion criterion : criteriaList) {
                            CriterionEvaluator evaluator = evaluators.get(criterion.getType());
                            if (evaluator != null) {
                                score += evaluator.evaluate(criterion, candidate);
                            }
                        }

                        return new CandidateRankingResponse(
                                candidate.getId(),
                                candidate.getName(),
                                candidate.getEmail(),
                                score
                        );
                    })
                    .toList();

            result.addAll(tempList);

            page++;
        } while (candidatesPage.hasNext());

        result.sort((a, b) -> Integer.compare(b.getScore(), a.getScore()));
        return result;
    }

    private VacancyResponse mapToResponse(Vacancy vacancy) {
        return new VacancyResponse(
                vacancy.getId(),
                vacancy.getName(),
                vacancy.getCriteriaList()
        );
    }
}

