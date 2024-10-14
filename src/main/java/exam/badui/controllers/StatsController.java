package exam.badui.controllers;

import exam.badui.dto.StatsResponse;
import exam.badui.services.StatsService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/stats")
public class StatsController {
    private StatsService statsService;

    public StatsController(StatsService statsService){
        this.statsService = statsService;
    }

    @GetMapping
    public StatsResponse getStats(){
        return statsService.getStats();
    }
}
