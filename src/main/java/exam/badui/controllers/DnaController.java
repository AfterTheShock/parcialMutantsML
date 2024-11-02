package exam.badui.controllers;

import exam.badui.dto.DnaRequest;
import exam.badui.dto.DnaResponse;
import exam.badui.services.DnaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/mutant")
public class DnaController {
    private DnaService dnaService;

    public DnaController(DnaService dnaService){
        this.dnaService = dnaService;
    }

    @PostMapping
    public ResponseEntity<DnaResponse> checkMutant(@RequestBody DnaRequest dnaRequest) {
        boolean isMutant = dnaService.analyzeDna(dnaRequest.getDna());
        DnaResponse dnaResponse = new DnaResponse(isMutant);

        try{
            if(isMutant){
                return ResponseEntity.status(HttpStatus.OK).body(dnaResponse);
            }else{
                return ResponseEntity.status(HttpStatus.FORBIDDEN).body(dnaResponse);
            }
        }catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(dnaResponse);
        }
    }
}