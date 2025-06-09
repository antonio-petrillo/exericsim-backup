BEGIN {
    FS="\n"
    RS=""
}
END {
    printf("%d\n", hamming($1, $2))
}

function hamming(s1, s2) {
    l1 = length(s1)
    l2 = length(s2)
    if(l1 != l2){
        print "strands must be of equal length"
        exit(1)
    }
    difference = 0
    for (i = 1; i <= l1; i++){
        if(substr(s1, i, 1) != substr(s2, i, 1)){
            difference++
        }
    }
    return difference
}
