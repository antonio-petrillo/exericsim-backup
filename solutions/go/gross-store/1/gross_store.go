package gross

func Units() map[string]int {
	return map[string]int{
		"quarter_of_a_dozen": 3,
		"half_of_a_dozen":    6,
		"dozen":              12,
		"small_gross":        120,
		"gross":              144,
		"great_gross":        1728,
	}
}

func NewBill() map[string]int {
	return map[string]int{}
}

func AddItem(bill, units map[string]int, item, unit string) bool {
	amount, ok := units[unit]
	if ok {
		bill[item] += amount
		return true
	}
	return false
}

func RemoveItem(bill, units map[string]int, item, unit string) bool {
	quantity, ok := bill[item]
	if ok {
		amount, ok := units[unit]
		if ok {
			quantity -= amount
			if quantity == 0 {
				delete(bill, item)
				return true
			} else if quantity > 0 {
				bill[item] = quantity
				return true
			}
		}
	}
	return false
}

func GetItem(bill map[string]int, item string) (int, bool) {
	quantity, ok := bill[item]
	return quantity, ok
}
