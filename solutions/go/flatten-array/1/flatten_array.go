package flatten

import (
	"reflect"
)

type User struct {
	Name string `json:"username"`
}

func Flatten(nested interface{}) []interface{} {
	v := reflect.ValueOf(nested)
	result := []any{}
	switch v.Kind() {
	case reflect.Slice, reflect.Array:
		for i := 0; i < v.Len(); i++ {
			val := v.Index(i)
			if val.IsNil() {
				continue
			}
			result = append(result, Flatten(val.Interface())...)
		}
	default:
		result = append(result, v.Interface())
	}

	return result
}
