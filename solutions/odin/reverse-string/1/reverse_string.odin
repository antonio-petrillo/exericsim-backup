package reverse_string

import "core:strings"
import "core:unicode/utf8"

reverse :: proc(str: string) -> string {
	graphemes, _, _, _ := utf8.decode_grapheme_clusters(str)
	defer delete(graphemes)
	sb := strings.builder_make()
	size := len(graphemes)
	if size > 0 {
		strings.write_string(&sb, str[graphemes[size - 1].byte_index:])
		if size > 1 {
			#reverse for grapheme, i in graphemes[:size - 1] {
				strings.write_string(&sb, str[grapheme.byte_index:graphemes[i + 1].byte_index])
			}
		}
	}
	return strings.to_string(sb)
}
