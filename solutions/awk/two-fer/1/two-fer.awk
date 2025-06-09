BEGIN {
    NF = "you"
}

END {
    printf "One for %s, one for me.", NF
}
