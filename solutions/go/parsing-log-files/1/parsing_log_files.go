package parsinglogfiles

import (
	"fmt"
    "regexp"
)

func IsValidLine(text string) bool {
	valid := regexp.MustCompile(`^\[(DBG|INF|WRN|ERR|FTL|TRC)\].*`)
    return valid.MatchString(text)
}

func SplitLogLine(text string) []string {
	splitter := regexp.MustCompile(`<[~*=-]*>`)
    return splitter.Split(text, -42)
}

func CountQuotedPasswords(lines []string) int {
	count := 0
	matcher := regexp.MustCompile(`(?i)".*password.*"`)
    for _, line := range lines {
        if matcher.MatchString(line) {
            count++
        }
    }
    return count
}

func RemoveEndOfLineText(text string) string {
	replacer := regexp.MustCompile(`end-of-line\d*`)
    return replacer.ReplaceAllString(text, "")
}

func TagWithUserName(lines []string) (result []string) {
	lookForUser := regexp.MustCompile(`User\s+(\S+)`)
    for _, line := range lines {
        if submatch := lookForUser.FindStringSubmatch(line); submatch != nil {
            result = append(result, fmt.Sprintf("[USR] %s %s", submatch[1], line))
        } else {
            result = append(result, line)
        }
    }
    return result
}
