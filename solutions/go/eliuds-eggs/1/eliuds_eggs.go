package eliudseggs

func EggCount(displayValue int) (count int) {
	for displayValue != 0 {
        count++
        displayValue &= displayValue - 1
    }
    return
}
