package sorting

import (
    "fmt"
    "strconv"
)

func DescribeNumber(f float64) string {
	return fmt.Sprintf("This is the number %.1f", f)
}

type NumberBox interface {
	Number() int
}

func DescribeNumberBox(nb NumberBox) string {
	return fmt.Sprintf("This is a box containing the number %.1f", float64(nb.Number()))
}

type FancyNumber struct {
	n string
}

func (i FancyNumber) Value() string {
	return i.n
}

type FancyNumberBox interface {
	Value() string
}

func ExtractFancyNumber(fnb FancyNumberBox) int {
    switch fancy := fnb.(type) {
    case FancyNumber:
        if num, err := strconv.Atoi(fancy.Value()); err == nil {
            return num
        }
    }
    return 0
}

func DescribeFancyNumberBox(fnb FancyNumberBox) string {
	return fmt.Sprintf("This is a fancy box containing the number %.1f", float64(ExtractFancyNumber(fnb)))
}

func DescribeAnything(i interface{}) string {
	switch value := i.(type) {
    case int:
    	return DescribeNumber(float64(value))
    case float64:
    	return DescribeNumber(value)
    case NumberBox:
        return DescribeNumberBox(value)
    case FancyNumberBox:
        return DescribeFancyNumberBox(value)
    default:
        return "Return to sender"
    }
}
