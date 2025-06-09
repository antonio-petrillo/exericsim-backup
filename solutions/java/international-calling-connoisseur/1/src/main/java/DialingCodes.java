import java.util.Map;
import java.util.HashMap;

public class DialingCodes {

    private Map<Integer, String> codes = new HashMap<>();
    private Map<String, Integer> reverse = new HashMap<>();

    public Map<Integer, String> getCodes() {
        return codes;
    }

    public void setDialingCode(Integer code, String country) {
        codes.put(code, country);
        reverse.put(country, code);
    }

    public String getCountry(Integer code) {
        return codes.get(code);
    }

    public void addNewDialingCode(Integer code, String country) {
        if (!codes.containsKey(code) && !reverse.containsKey(country)) {
            setDialingCode(code, country);
        }
    }

    public Integer findDialingCode(String country) {
        return reverse.get(country);
    }

    public void updateCountryDialingCode(Integer code, String country) {
        Integer prev = reverse.get(country);
        if (prev != null) {
            codes.put(code, country);
            codes.remove(prev);
            reverse.put(country, code);
        }
    }
}
