package diamond

import (
	"bytes"
	"errors"
	"strings"
)

const (
	space byte = ' '
)

func genRow(char byte) []byte {
	if char == 'A' {
		return []byte{'A'}
	}
	numSpaces := (char-'A')*2 - 1
	buffer := bytes.Buffer{}
	buffer.WriteByte(char)
	buffer.Write(bytes.Repeat([]byte{32}, int(numSpaces)))
	buffer.WriteByte(char)
	return buffer.Bytes()
}

func padToLen(row []byte, size int) string {
	padLen := (size - len(row)) / 2
	if padLen == 0 {
		return string(row)
	}
	pad := bytes.Repeat([]byte{' '}, padLen)

	return string(bytes.Join([][]byte{pad, row, pad}, []byte{}))
}

func Gen(char byte) (string, error) {
	if char < 'A' || char > 'Z' {
		return "", errors.New("Invalid character, choose in range [A-Z]")
	}
	first := genRow(char)
	size := len(first)
	halfDiamond := []string{}

	for i := byte('A'); i < char; i++ {
		row := genRow(i)
		padded := padToLen(row, size)
		halfDiamond = append(halfDiamond, padded)
	}

	diamond := []string{}
	diamond = append(diamond, halfDiamond...)
	diamond = append(diamond, string(first))

	for i := len(halfDiamond) - 1; i >= 0; i-- {
		diamond = append(diamond, halfDiamond[i])
	}

	return strings.Join(diamond, "\n"), nil
}
