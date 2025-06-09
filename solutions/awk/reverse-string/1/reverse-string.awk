BEGIN {
    FS = ""
    result = ""
}

{
    for (char = NF; char > 0; char--)
        result=result $char
}

END {
    print result
}
