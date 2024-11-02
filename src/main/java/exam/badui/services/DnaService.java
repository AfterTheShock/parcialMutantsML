package exam.badui.services;

import exam.badui.entities.Dna;
import exam.badui.exception.NoValidDnaException;
import exam.badui.repositories.DnaRepository;
import exam.badui.validators.DnaValidator;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DnaService {
    private final DnaRepository dnaRepository;

    @Autowired
    public DnaService(DnaRepository dnaRepository){
        this.dnaRepository = dnaRepository;
    }

    @Transactional
    public List<Dna> findall() throws Exception {
        try {
            return dnaRepository.findAll();
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    public static boolean isMutant(String[] dna){
        int totalSeq = 0;

        //Verifica las secuencias horizontales
        if((totalSeq += horizontalVer(convertStringToCharArray(dna), totalSeq)) > 1)                    return true;

        //Verifica las secuencias verticales
        if((totalSeq += horizontalVer(transposeArray(convertStringToCharArray(dna)), totalSeq)) > 1)    return true;

        //Verifica las secuencias diagonales
        return (totalSeq += diagonalVer(convertStringToCharArray(dna), totalSeq)) > 1;
    }

    public boolean validateDna(String[] dna){
        return DnaValidator.isValid(dna);
    }

    private static char[][] transposeArray(char[][] doubleCharArray){
        for (int i = 0; i < doubleCharArray.length; i++) {
            for (int j = 0; j < i; j++) {
                char aux = doubleCharArray[i][j];
                doubleCharArray[i][j] = doubleCharArray[j][i];
                doubleCharArray[j][i] = aux;
            }
        }

        return doubleCharArray;
    }

    private static char[][] convertStringToCharArray(String[] stringArray){
        char[][] charArray = new char[stringArray.length][];

        for(int i = 0; i < stringArray.length; i++){
            charArray[i] = stringArray[i].toCharArray();
        }

        return charArray;
    }

    private static int horizontalVer(char[][] doubleCharArray, int totalSeq){
        int hSeq = 0;

        for (int i = 0; i < doubleCharArray.length; i++) {
            int jInc = 1;

            //Contador de letras iguales sucesivas
            int hCount = 0;

            for (int j = 0; j < doubleCharArray[i].length - 1; j += jInc) {
                jInc = 1;

                //Compara si la letra es igual a la siguiente
                if(doubleCharArray[i][j] == doubleCharArray[i][j + 1]) {
                    hCount++;
                }else{
                    hCount = 0;
                }

                //Si encuentra una secuencia de 4 letras iguales
                if(hCount >= 3){
                    hSeq++;
                    jInc = 2;
                    hCount = 0;

                    //Si la suma de las secuencias totales y las secuencias de esta verificación
                    if(totalSeq + hSeq > 1){
                        return hSeq;
                    }
                }
            }
        }

        return hSeq;
    }

    private static int diagonalVer(char[][] doubleCharArray, int totalSeq){
        int dSeq = 0;
        int n = doubleCharArray.length;

        for (int t = n - 4; t >= 0 ; t--) {
            int iInc = 1;
            int dCount = 0;
            for (int i = 0; i < n - t - 1; i += iInc) {
                iInc = 1;
                if(doubleCharArray[i][t + i] == doubleCharArray[i + 1][t + i + 1]) {
                    dCount++;
                }else{
                    dCount = 0;
                }
                if(dCount >= 3){
                    dSeq++;
                    iInc = 2;
                    dCount = 0;
                }

                if(totalSeq + dSeq > 1){
                    return dSeq;
                }
            }
        }

        //Implementación para contradiagonales
        /*for (int t = n - 4; t > 0 ; t--) {
            int iInc = 1;
            int dCount = 0;
            for (int i = 0; i < n - t - 1; i += iInc) {
                iInc = 1;
                if(doubleCharArray[t + i][i] == doubleCharArray[t + i + 1][i + 1]) {
                    dCount++;
                }else{
                    dCount = 0;
                }
                if(dCount >= 3){
                    dSec++;
                    iInc = 2;
                    dCount = 0;
                }
            }
        }*/

        return dSeq;
    }

    public boolean analyzeDna(String[] dna){
        String dnaSequence = String.join(", ", dna);

        if(!validateDna(dna)){
            throw new NoValidDnaException("La secuencia de ADN no es válida.");
        }

        Optional<Dna> existingDna = dnaRepository.findByDna(dnaSequence);
        if(existingDna.isPresent()){
            return existingDna.get().isMutant();
        }

        boolean isMutant = isMutant(dna);
        Dna dnaEntity = Dna.builder()
                .dna(dnaSequence)
                .isMutant(isMutant)
                .build();

        dnaRepository.save(dnaEntity);

        return isMutant;
    }
}
