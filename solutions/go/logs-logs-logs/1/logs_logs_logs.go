package logs

import "unicode/utf8"
import "strings"

func Application(log string) string {
	for _, ch := range log {
        switch ch {
        case '❗':
            return "recommendation"
        case '🔍':
            return "search"
        case '☀':    
            return "weather"
        default:
            continue
        }
    }
    return "default"
}


func Replace(log string, oldRune, newRune rune) string {
	return strings.ReplaceAll(log, string(oldRune), string(newRune))
}

func WithinLimit(log string, limit int) bool {
	return utf8.RuneCountInString(log) <= limit
}
