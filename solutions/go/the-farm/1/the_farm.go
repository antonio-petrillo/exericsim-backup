package thefarm

import (
	"fmt"
    "errors"
)

func DivideFood(fc FodderCalculator, cows int) (float64, error) {
    fodder, err := fc.FodderAmount(cows)
    if err != nil {
        return 0.0, err
    }
    factor, err := fc.FatteningFactor()
    if err != nil {
        return 0.0, err
    }

    return fodder * factor / float64(cows), nil
}

func ValidateInputAndDivideFood(fc FodderCalculator, cows int) (float64, error) {
    if cows > 0 {
        return DivideFood(fc, cows)
    }

    return 0.0, errors.New("invalid number of cows")
}

func ValidateNumberOfCows(cows int) error {
    if cows < 0 {
        return errors.New(fmt.Sprintf("%d cows are invalid: there are no negative cows", cows))
    }
    if cows == 0 {
        return errors.New("0 cows are invalid: no cows don't need food")
    }
    return nil
}