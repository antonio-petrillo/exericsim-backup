package logs

// who need imports?

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
    var res []rune 
	for _, ch := range log {
        if ch == oldRune {
            res = append(res, newRune)
        } else {
            res = append(res, ch)
        }
    }
    return string(res)
}

func WithinLimit(log string, limit int) bool {
	for i, _ := range log {
        if i >= limit {
            return false
        }
    }
    return true
}
