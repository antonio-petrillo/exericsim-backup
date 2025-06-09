class CollatzCalculator {

    int computeStepCount(int start) {
        int step = 0;
        if(start < 1){
           throw new IllegalArgumentException("Only natural numbers are allowed");
        }
        while (start != 1){
            start = (start % 2 == 0) ? start >> 1 : start * 3 + 1;
            step++;
        }
        return step;
    }

}
