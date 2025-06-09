public class IsbnVerifier {

    boolean isValid(String stringToVerify) {
        int sum = 0;
        int index = 11;
        stringToVerify = stringToVerify
                .replaceAll(" ", "")
                .replaceAll("-", "");
        if (stringToVerify.length() != 10) {
            return false;
        }
        for(Character c : stringToVerify.toCharArray()){
            index--;
            if(index == 1 && c == 'X'){
                sum += 10;
            } else if(Character.isDigit(c)){
                sum += index * Integer.parseInt(c.toString());
            } else {
                return false;
            }
        }
        return sum % 11 == 0;
    }

}
