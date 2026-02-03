package high_scores

import "core:slice"

High_Scores :: struct {
    scores: []int,
    length: int
}

new_scores :: proc(initial_values: []int) -> High_Scores {
    length := len(initial_values)
    scores := make([]int, length + 3)

    m1, m2, m3: int
    for val, i in initial_values {
        if val > m1 {
            m3, m2, m1 = m2, m1, val
        } else if val > m2 {
            m3, m2 = m2, val
        } else if val > m3 {
            m3 = val
        }
        scores[i] = val
    }

    scores[length] = m1
    scores[length + 1] = m2
    scores[length + 2] = m3

    return High_Scores{
        scores = scores,
        length = length,
    }
}

destroy_scores :: proc(s: ^High_Scores) {
    delete(s.scores)
    s.length = 0
}

scores :: proc(s: High_Scores) -> []int {
    return slice.clone(s.scores[:s.length])
}

latest :: proc(s: High_Scores) -> int {
    return s.scores[s.length - 1]
}

personal_best :: proc(s: High_Scores) -> int {
    return s.scores[s.length]
}

personal_top_three :: proc(s: High_Scores) -> []int {
    end := s.length + min(3, s.length)
    return slice.clone(s.scores[s.length:end])
}
