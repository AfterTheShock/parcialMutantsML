package exam.badui.validators;

import java.util.HashSet;
import java.util.Set;

public class DnaValidator {
    final static char[] VALID_CHARS = {'A', 'T', 'C', 'G'};

    public static boolean isValid(String[] dna){
        return verifyArrayCharacters(dna) && verifyArrayLength(dna) && verifySeqLength(dna);
    }

    private static boolean verifySeqLength(String[] str){
        return str.length > 3;
    }

    private static boolean verifyArrayCharacters(String[] str){
        Set<Character> validCharsSet = new HashSet<>();

        for(char c : VALID_CHARS){
            validCharsSet.add(c);
        }

        String unifiedStringSeq = String.join("", str);

        for(int i = 0; i < unifiedStringSeq.length(); i++){
            if(!validCharsSet.contains(unifiedStringSeq.charAt(i))) return false;
        }

        return true;
    }

    private static boolean verifyArrayLength(String[] str){
        for(String seq : str){
            if(seq.length() != str.length) return false;
        }

        return true;
    }
}
