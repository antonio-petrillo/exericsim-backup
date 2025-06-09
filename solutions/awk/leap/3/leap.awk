BEGIN {
    year = $0 + 0
}
{
    if (0 == $year % 4 && (0 != $year % 100 || 0 == $year % 400)) {
        print "true"
    } else  {
        print "false"
    }
}
