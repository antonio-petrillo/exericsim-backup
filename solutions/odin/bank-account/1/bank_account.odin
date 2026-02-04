package bank_account

import "core:sync"

TransactionResult :: enum {
	Success,
	Account_Not_Open,
	Account_Already_Open,
	Invalid_Amount,
	Not_Enough_Balance,
	Unimplemented,
}

Status :: enum {
    New,
    Open,
    Closed,
}

Account :: struct {
    balance: u32,
    mutex: sync.RW_Mutex,
    status: Status,
}

open :: proc(self: ^Account) -> TransactionResult {
    sync.rw_mutex_lock(&self.mutex)
    defer sync.rw_mutex_unlock(&self.mutex)
    switch self.status {
    case .New: self.status = .Open
    case .Closed: return .Account_Not_Open
    case .Open: return .Account_Already_Open
    }
    self.balance = 0
    return .Success
}

close :: proc(self: ^Account) -> TransactionResult {
    sync.rw_mutex_lock(&self.mutex)
    defer sync.rw_mutex_unlock(&self.mutex)
    switch self.status {
    case .New: return .Account_Not_Open
    case .Closed: return .Account_Not_Open
    case .Open: self.status = .Closed
    }
    self.balance = 0
    return .Success
}

read_balance :: proc(self: ^Account) -> (u32, TransactionResult) {
    sync.rw_mutex_lock(&self.mutex)
    defer sync.rw_mutex_unlock(&self.mutex)
    if self.status != .Open { return 0, .Account_Not_Open }
    return self.balance, .Success
}

deposit :: proc(self: ^Account, amount: u32) -> TransactionResult {
    if amount == 0 {
        return .Invalid_Amount
    }
    sync.rw_mutex_lock(&self.mutex)
    defer sync.rw_mutex_unlock(&self.mutex)
    if self.status != .Open {
        return .Account_Not_Open
    }
    self.balance += amount
    return .Success
}

withdraw :: proc(self: ^Account, amount: u32) -> TransactionResult {
    if amount == 0 {
        return .Invalid_Amount
    }
    sync.rw_mutex_lock(&self.mutex)
    defer sync.rw_mutex_unlock(&self.mutex)
    if self.status != .Open {
        return .Account_Not_Open
    }
    if self.balance < amount {
        return .Not_Enough_Balance
    }
    self.balance -= amount
    return .Success
}
