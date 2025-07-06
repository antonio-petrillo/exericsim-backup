package listops

// IntList is an abstraction of a list of integers which we can define methods on
type IntList []int

func (s IntList) Foldl(fn func(int, int) int, initial int) int {
	for _, n := range s {
		initial = fn(initial, n)
	}
	return initial
}

func (s IntList) Foldr(fn func(int, int) int, initial int) int {
	for i := len(s) - 1; i >= 0; i-- {
		initial = fn(s[i], initial)
	}
	return initial
}

func (s IntList) Filter(fn func(int) bool) IntList {
	filtered := []int{}
	for _, n := range s {
		if fn(n) {
			filtered = append(filtered, n)
		}
	}
	return filtered
}

func (s IntList) Length() int {
	return len(s)
}

func (s IntList) Map(fn func(int) int) IntList {
	mapped := []int{}
	for _, n := range s {
		mapped = append(mapped, fn(n))
	}
	return mapped
}

func (s IntList) Reverse() IntList {
	reversed := []int{}
	for i := len(s) - 1; i >= 0; i-- {
		reversed = append(reversed, s[i])
	}
	return reversed
}

func (s IntList) Append(lst IntList) IntList {
	return append(s, lst...)
}

func (s IntList) Concat(lists []IntList) IntList {
	l := append([]int{}, s...)
	for _, xs := range lists {
		l = append(l, xs...)
	}
	return l
}
