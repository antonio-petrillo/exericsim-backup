package diffiehellman

import (
	"crypto/rand"
	"math/big"
)

// Diffie-Hellman-Merkle key exchange
// Private keys should be generated randomly.

var two = big.NewInt(2)
var one = big.NewInt(1)
var zero = big.NewInt(0)

func PrivateKey(p *big.Int) *big.Int {
	n, _ := rand.Int(rand.Reader, p)
	if n.Cmp(zero) == 0 {
		n = n.Add(n, two)
	}
	if n.Cmp(one) == 0 {
		n = n.Add(n, one)
	}
	return n
}

func PublicKey(private, p *big.Int, g int64) *big.Int {
	base := big.NewInt(g)
	return base.Exp(base, private, p)
}

func NewPair(p *big.Int, g int64) (*big.Int, *big.Int) {
	private := PrivateKey(p)
	public := PublicKey(private, p, g)

	return private, public
}

func SecretKey(private1, public2, p *big.Int) *big.Int {
	shared := big.NewInt(0)
	shared = shared.Exp(public2, private1, p)
	return shared
}
