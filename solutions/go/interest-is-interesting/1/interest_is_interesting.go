package interest

// InterestRate returns the interest rate for the provided balance.
func InterestRate(balance float64) float32 {
	if balance < 0.0 {
		return 3.213
	} else if balance < 1000.0 {
		return 0.5
	} else if balance < 5000.0 {
		return 1.621
	} else {
		return 2.475
	}
}

func Interest(balance float64) float64 {
	return balance * float64(InterestRate(balance)) * 0.01
}

func AnnualBalanceUpdate(balance float64) float64 {
	return balance + balance*(float64(InterestRate(balance))*0.01)
}

func YearsBeforeDesiredBalance(balance, targetBalance float64) int {
	i := 0
	for i = 0; true; i++ {
		if balance >= targetBalance {
			break
		} else {
			balance = AnnualBalanceUpdate(balance)
		}
	}
	return i
}
