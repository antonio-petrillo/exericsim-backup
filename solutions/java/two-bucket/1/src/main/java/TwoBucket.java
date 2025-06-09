public class TwoBucket {

    private int moves;
    private final String bucket;
    private final int other;

    public TwoBucket(int bucketOneCap, int bucketTwoCap, int desiredLiters, String startBucket) {
        int[] buckets = {0, 0};
        int[] caps = {bucketOneCap, bucketTwoCap};
        int id = "one".equals(startBucket) ? 0 : 1;
        int other = id == 0 ? 1 : 0;

        // fill start
        buckets[id] = caps[id];
        moves++;


        // edge case, secondary is the same as target, fill second and end
        if (caps[other] == desiredLiters) {
            buckets[other] = desiredLiters;
            moves++;
        } else {


            while (buckets[0] != desiredLiters && buckets[1] != desiredLiters) {
                // if secondary is full, empty it
                if (buckets[other] == caps[other]) {
                    buckets[other] = 0;
                    moves++;
                }

                // if primary bucket is empty, refill
                if (buckets[id] == 0) {
                    buckets[id] = caps[id];
                    moves++;
                }

                int transfer = Math.min(caps[other] - buckets[other], buckets[id]);
                buckets[id] -= transfer;
                buckets[other] += transfer;
                moves++;
            }
        }

        bucket = buckets[0] == desiredLiters ? "one" : "two";
        this.other = buckets[bucket.equals("one") ? 1 : 0];

    }

    public int getTotalMoves() {
        return moves;
    }

    public String getFinalBucket() {
        return bucket;
    }

    public int getOtherBucket() {
        return other;
    }
}
