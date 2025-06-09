package stringset

import (
	"bytes"
	"fmt"
	"sort"
	"strings"
)

type token struct{}

type Set map[string]token

func New() Set {
	return make(map[string]token)
}

func NewFromSlice(l []string) Set {
	newSet := New()
	for _, s := range l {
		newSet[s] = token{}
	}
	return newSet
}

func (s Set) String() string {
	var buffer bytes.Buffer

	buffer.WriteString("{")

	elems := []string{}
	for k := range s {
		elems = append(elems, fmt.Sprintf("\"%s\"", k))
	}

	sort.Slice(elems, func(i, j int) bool {
		return elems[i] < elems[j]
	})

	buffer.WriteString(strings.Join(elems, ", "))

	buffer.WriteString("}")

	return buffer.String()
}

func (s Set) IsEmpty() bool {
	return len(s) == 0
}

func (s Set) Has(elem string) bool {
	_, ok := s[elem]
	return ok
}

func (s Set) Add(elem string) {
	s[elem] = token{}
}

func Subset(s1, s2 Set) bool {
	for k := range s1 {
		if _, ok := s2[k]; !ok {
			return false
		}
	}
	return true
}

func Disjoint(s1, s2 Set) bool {
	for k := range s1 {
		if _, ok := s2[k]; ok {
			return false
		}
	}
	return true
}

func Equal(s1, s2 Set) bool {
	if len(s1) != len(s2) {
		return false
	}
	for k := range s1 {
		if _, ok := s2[k]; !ok {
			return false
		}
	}
	return true
}

func Intersection(s1, s2 Set) Set {
	intersection := New()
	for k, v := range s1 {
		if _, ok := s2[k]; ok {
			intersection[k] = v
		}
	}
	return intersection
}

func Difference(s1, s2 Set) Set {
	difference := New()
	for k, v := range s1 {
		if _, ok := s2[k]; !ok {
			difference[k] = v
		}
	}

	return difference
}

func Union(s1, s2 Set) Set {
	union := New()
	for k, v := range s1 {
		union[k] = v
	}

	for k, v := range s2 {
		union[k] = v
	}

	return union
}
