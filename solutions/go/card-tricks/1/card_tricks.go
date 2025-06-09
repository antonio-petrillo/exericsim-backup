package cards

func FavoriteCards() []int {
	return []int{2, 6, 9}
}

func GetItem(slice []int, index int) int {
	if index < 0 || index >= len(slice) {
		return -1
	} else {
		return slice[index]
	}
}

func SetItem(slice []int, index, value int) []int {
	if index < 0 || index >= len(slice) {
		return append(slice, value)
	} else {
		slice[index] = value
		return slice
	}
}

func PrependItems(slice []int, values ...int) []int {
	return append(values, slice...)
}

func RemoveItem(slice []int, index int) []int {
	if index < 0 || index >= len(slice) {
		return slice
	} else {
		return append(slice[:index], slice[index+1:]...)
	}
}
