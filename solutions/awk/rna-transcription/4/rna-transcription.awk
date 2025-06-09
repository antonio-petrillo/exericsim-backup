BEGIN {
    FS=""
    result = ""
    mapping["G"] = "C"
    mapping["C"] = "G"
    mapping["T"] = "A"
    mapping["A"] = "U"
}
{
    if (length($0) > 0 && $0 ~ /[^GCTA]+/) {
        print "Invalid nucleotide detected."
    } else {
        for (i = 1; i <= NF; i++){
            result = (result mapping[$i])
        }
        print result
    }
}
