class RnaTranscription {

    String transcribe(String dnaStrand) {
        StringBuilder sb = new StringBuilder();
        for (char c : dnaStrand.toCharArray()) {
            switch (c) {
            case 'G':
                sb.append('C');
                break;
            case 'C':
                sb.append('G');
                break;
            case 'T':
                sb.append('A');
                break;
            case 'A':
                sb.append('U');
                break;
            default:
                break;
            }
        }
        return sb.toString();
    }

}
