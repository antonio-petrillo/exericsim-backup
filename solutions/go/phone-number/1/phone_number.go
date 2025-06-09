package phonenumber

import (
    "errors"
    
)

func Number(phoneNumber string) (string, error) {
	digits := []rune{}
    for _, ch := range phoneNumber {
        if ch >= '0' && ch <= '9' {
            digits = append(digits, ch)
        }
    }
    if len(digits) == 11 {
    	if digits[0] != '1' {
            return "", errors.New("Invalid number")
        }
        digits = digits[1:]
    }
    if len(digits) != 10 {
        return "", errors.New("Invalid number of digits")
    }
    if digits[0] == '0' || digits[0] == '1' {
        return "", errors.New("Invalid code")
    }
    if digits[3] == '0' || digits[3] == '1' {
        return "", errors.New("Invalid exchange")
    }
    return string(digits), nil
}

func AreaCode(phoneNumber string) (string, error) {
	num, err := Number(phoneNumber)
    if err != nil {
        return "", err
    }
    return num[0:3], err
}

func Format(phoneNumber string) (string, error) {
	num, err := Number(phoneNumber)
    if err != nil {
        return "", err
    }
    fmt := []rune{}
    for i, ch := range num {
        if(i == 0) {
            fmt = append(fmt, '(')
        }
        fmt = append(fmt, ch)
        if (i == 2) {
            fmt = append(fmt, ')', ' ')
        }
        if (i == 5) {
            fmt = append(fmt, '-')
        }
    }
    return string(fmt), nil
}
