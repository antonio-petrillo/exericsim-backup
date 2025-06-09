package flatten

func Flatten(nested interface{}) []interface{} {
	flat := []any{}

	switch ns := nested.(type) {
	case []interface{}:
		for _, elem := range ns {
			flat = append(flat, Flatten(elem)...)
		}
	case any:
		flat = append(flat, ns)
	}

	return flat
}
