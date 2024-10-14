package exam.badui.services;

import exam.badui.dto.StatsResponse;
import exam.badui.repositories.DnaRepository;
import org.springframework.stereotype.Service;

@Service
public class StatsService {
    private final DnaRepository dnaRepository;

    public StatsService(DnaRepository dnaRepository){
        this.dnaRepository = dnaRepository;
    }

    public StatsResponse getStats(){
        long countMutantDna = dnaRepository.countByIsMutant(true);
        long countHumanDna = dnaRepository.countByIsMutant(false);
        double ratio = countHumanDna != 0 ? (double) countMutantDna / countHumanDna : 0;
        return new StatsResponse(countMutantDna, countHumanDna, ratio);
    }
}
