package exam.badui.controllers;

import exam.badui.dto.DnaRequest;
import exam.badui.services.DnaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("api/v1/mutant")
public class DnaController {
    private DnaService dnaService;

    public DnaController(DnaService dnaService){
        this.dnaService = dnaService;
    }

    /*@GetMapping("")
    public ResponseEntity<?> getAll(){
        try {
            return ResponseEntity.status(HttpStatus.OK).body(dnaService.findall());
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"error\":\"Error. Por favor intente mas tarde.\"}");
        }
    }*/


    @PostMapping("")
    public ResponseEntity<String> checkMutant(@RequestBody DnaRequest dnaRequest) {
        boolean isMutant = dnaService.analyzeDna(dnaRequest.getDna());

        try{
            if(isMutant){
                return ResponseEntity.status(HttpStatus.OK).body("Mutante detectado");
            }else{
                return ResponseEntity.status(HttpStatus.FORBIDDEN).body("No es mutante");
            }
        }catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
}
