package account

import "sync"

// Define the Account type here.
type Account struct {
	mu      sync.RWMutex
	open    bool
	balance int64
}

func Open(amount int64) *Account {
	if amount < 0 {
		return nil
	}
	return &Account{
		mu:      sync.RWMutex{},
		open:    true,
		balance: amount,
	}
}

func (a *Account) Balance() (int64, bool) {
	a.mu.Lock()
	defer a.mu.Unlock()
	if !a.open {
		return 0, false
	}
	return a.balance, true
}

func (a *Account) Deposit(amount int64) (int64, bool) {
	a.mu.Lock()
	defer a.mu.Unlock()
	if !a.open || amount <= 0 && a.balance < -amount {
		return 0, false
	}
	a.balance += amount
	return a.balance, true
}

func (a *Account) Close() (int64, bool) {
	a.mu.Lock()
	defer a.mu.Unlock()

	if !a.open {
		return 0, false
	}
	amount := a.balance
	a.balance = 0
	a.open = false
	return amount, true
}
