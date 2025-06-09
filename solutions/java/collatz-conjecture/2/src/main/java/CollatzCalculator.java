class CollatzCalculator {

    int computeStepCount(int start) {
        // throw new UnsupportedOperationException("Delete this statement and write your own implementation.");
        int step = 0;
        while (start != 1){
            // start = (start % 2 == 0) ? start / 2 : start * 3 + 1;
            if (start % 2 == 0){
                start /= 2;
            }else {
               start = start * 3 + 1;
            }
            step++;
        }
        return step;
    }

}
