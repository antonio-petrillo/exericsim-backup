class CollatzCalculator {

    int computeStepCount(int start) {
        // throw new UnsupportedOperationException("Delete this statement and write your own implementation.");
        int step = 0;
        if(start < 1){
           throw new IllegalArgumentException();
        }
        while (start != 1){
            start = (start % 2 == 0) ? start / 2 : start * 3 + 1;
            step++;
        }
        return step;
    }

}
