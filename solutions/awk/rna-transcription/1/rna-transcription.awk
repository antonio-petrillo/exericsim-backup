BEGIN {
    FS=""
    result = ""
    mapping["G"] = "C"
    mapping["C"] = "G"
    mapping["T"] = "A"
    mapping["A"] = "U"
}

$0 ~ /[^GCTA]/ {
    print "Invalid nucleotide detected."
}

{
    for (i = 1; i <= NF; i++){
        result = (result mapping[$i])
    }
}
END {
    print result
}
