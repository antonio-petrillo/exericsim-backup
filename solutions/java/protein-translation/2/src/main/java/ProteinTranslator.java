import java.util.List;
import java.util.ArrayList;

public class ProteinTranslator {

    public List<String> translate(String rnaSequence) {
        var proteins = new ArrayList<String>();
        for(int i = 0; i < rnaSequence.length(); i+=3) {
            var codon = rnaSequence.substring(i, Math.min(i + 3, rnaSequence.length()));
            var protein = translateCodon(codon);
            if ("STOP".equals(protein)) {
                break;
            }
            proteins.add(protein);
        }
        return proteins;
    }
    
    private static String translateCodon(String codon) {
        return switch (codon) {
            case "AUG":
                yield "Methionine";
            case "UUC", "UUU":
                yield "Phenylalanine";
            case "UUA", "UUG": 
                yield "Leucine";
            case "UCU", "UCC", "UCA", "UCG": 
                yield "Serine";
            case "UAU", "UAC":
                yield "Tyrosine";
            case "UGU", "UGC": 
                yield "Cysteine";
            case "UGG":
                yield "Tryptophan";
            case "UAA", "UAG", "UGA":
                yield "STOP";
            default:
                throw new IllegalArgumentException("Invalid codon");
        };
    }
}