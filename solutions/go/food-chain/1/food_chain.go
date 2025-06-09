package foodchain

import (
	"fmt"
	"strings"
)

var (
	animals = []string{"fly", "spider", "bird", "cat", "dog", "goat", "cow", "horse"}
	whys    = map[string]string{
		"spider": "It wriggled and jiggled and tickled inside her.\n",
		"bird":   "How absurd to swallow a bird!\n",
		"cat":    "Imagine that, to swallow a cat!\n",
		"dog":    "What a hog, to swallow a dog!\n",
		"goat":   "Just opened her throat and swallowed a goat!\n",
		"cow":    "I don't know how she swallowed a cow!\n",
		"horse":  "She's dead, of course!",
	}
)

func swallow(v int) string {
	if v == 3 {
		return "She swallowed the bird to catch the spider that wriggled and jiggled and tickled inside her.\n"
	}
	return fmt.Sprintf("She swallowed the %s to catch the %s.\n", animals[v-1], animals[v-2])
}

func Verse(v int) string {
	builder := strings.Builder{}
	animal := animals[v-1]
	fmt.Fprintf(&builder, "I know an old lady who swallowed a %s.\n", animal)
	if why, ok := whys[animal]; ok {
		builder.WriteString(why)
	}

	if v == 8 {
		return builder.String()
	}

	for ; v > 1; v-- {
		builder.WriteString(swallow(v))
	}

	builder.WriteString("I don't know why she swallowed the fly. Perhaps she'll die.")
	return builder.String()
}

func Verses(start, end int) string {
	verses := []string{}
	for ; start <= end; start++ {
		verses = append(verses, Verse(start))
	}
	return strings.Join(verses, "\n\n")
}

func Song() string {
	return Verses(1, 8)
}
