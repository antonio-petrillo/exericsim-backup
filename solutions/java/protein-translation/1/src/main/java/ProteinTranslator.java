import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;

class ProteinTranslator {

    private static HashMap<String, String> proteinsMapping = new HashMap<>();

    static {
        proteinsMapping.put("AUG", "Methionine");
        proteinsMapping.put("UUU", "Phenylalanine");
        proteinsMapping.put("UUC", "Phenylalanine");
        proteinsMapping.put("UUA", "Leucine");
        proteinsMapping.put("UUG", "Leucine");
        proteinsMapping.put("UCU", "Serine");
        proteinsMapping.put("UCC", "Serine");
        proteinsMapping.put("UCA", "Serine");
        proteinsMapping.put("UCG", "Serine");
        proteinsMapping.put("UAU", "Tyrosine");
        proteinsMapping.put("UAC", "Tyrosine");
        proteinsMapping.put("UGU", "Cysteine");
        proteinsMapping.put("UGC", "Cysteine");
        proteinsMapping.put("UGG", "Tryptophan");
        proteinsMapping.put("UAA", "STOP");
        proteinsMapping.put("UAG", "STOP");
        proteinsMapping.put("UGA", "STOP");
    }

    List<String> translate(String rnaSequence) {
        ArrayList<String> result = new ArrayList<>();
        for (int i = 0; i < rnaSequence.length(); i += 3) {
            String codon = rnaSequence.substring(i, i + 3);
            String protein = proteinsMapping.get(codon);
            if (protein.equals("STOP")) {
                break;
            }
            if (!result.contains(protein)) {
                result.add(protein);
            }
        }
        return result;
    }
}
