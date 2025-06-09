package flatten

func Flatten(nested interface{}) []any {
	flat := []any{}

	switch ns := nested.(type) {
	case []any:
		for _, elem := range ns {
			flat = append(flat, Flatten(elem)...)
		}
	case any:
		flat = append(flat, ns)
	}

	return flat
}
