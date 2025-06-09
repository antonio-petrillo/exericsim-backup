package anagram

import (
	"sort"
    "strings"
)

func Detect(subject string, candidates []string) (res []string) {
	subject = strings.ToLower(subject)
    sorted := sortString(subject)
    for _, candidate := range candidates {
        if isAnagram(sorted, subject, strings.ToLower(candidate)) {
            res = append(res, candidate)
        }
    }
	return 
}

func sortString(s string) string {
    runes := []rune(s)
    sort.Slice(runes, func(i, j int) bool {
        return runes[i] < runes[j]
    })
    return string(runes)
}

func isAnagram(sorted, s1, s2 string) bool {
    return s1 != s2 && sortString(s2) == sorted
}