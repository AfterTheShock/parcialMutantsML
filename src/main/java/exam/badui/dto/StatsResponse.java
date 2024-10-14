package exam.badui.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Setter
@Getter
public class StatsResponse {
    private long countMutantDna;
    private long countHumanDna;
    private double ratio;
}
