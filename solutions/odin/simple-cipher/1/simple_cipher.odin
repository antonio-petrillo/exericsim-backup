package simple_cipher

import "core:math/rand"


decode :: proc(ciphertext, key: string) -> string {
    plaintext := make([]u8, len(ciphertext))

    for ch, i in transmute([]u8)ciphertext {
        decoded := ch - key[i % len(key)] + 'a'
        if decoded < 'a' { decoded += 26 }
        plaintext[i] = decoded
    }

    return transmute(string)plaintext
}

encode :: proc(plaintext, key: string) -> string {
    ciphertext := make([]u8, len(plaintext))

    for ch, i in transmute([]u8)plaintext {
        encoded := ch + key[i % len(key)] - 'a'
        if encoded > 'z' { encoded -= 26 }
        ciphertext[i] = encoded
    }

    return transmute(string)ciphertext
}

DEFAULT_KEY_LEN :: 100

key :: proc() -> string {
    chars := make([]u8, DEFAULT_KEY_LEN)

    for i in 0..<DEFAULT_KEY_LEN {
        chars[i] = u8(rand.int_max(26)) + 'a'
    }

    return transmute(string)chars
}
