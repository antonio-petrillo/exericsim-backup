package raindrops

import "strconv"

func Convert(number int) string {
    sounds := make (map[int]string)
    sounds[3] = "Pling"
    sounds[5] = "Plang"
    sounds[7] = "Plong"
	return ConvertWithSounds(number, sounds)
}

func ConvertWithSounds(number int, sounds map[int]string) string {
    res := ""
    for n, sound := range sounds{
        if (number % n == 0) {
            res += sound;
        }
    }
    if (res == "") {
        return strconv.Itoa(number)
    } else {
    	return res;
    }
}