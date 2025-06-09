BEGIN {
    FS="\n"
    RS=""
}
END {
    printf("%d\n", hamming($1, $2))
}

function hamming(s1, s2) {
    difference = 0
    for (i = 1; i <= length(s1); i++){
        if(substr(s1, i, 1) != substr(s2, i, 1)){
            difference++
        }
    }
    return difference
}
