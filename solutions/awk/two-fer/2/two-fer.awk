BEGIN {
    name = "you"
}
NF {name = $0} # for each line
END {
    printf "One for %s, one for me.", name
}
