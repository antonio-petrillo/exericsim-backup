package rna_transcription

import "core:strings"

to_rna :: proc(dna: string) -> (rna: string, ok: bool) {
    sb := strings.builder_make()
    defer if !ok {
        strings.builder_destroy(&sb)
    }
    for ch in dna {
        switch ch {
        case 'G': strings.write_rune(&sb, 'C')
        case 'C': strings.write_rune(&sb, 'G')
        case 'T': strings.write_rune(&sb, 'A')
        case 'A': strings.write_rune(&sb, 'U')
        case:
            ok = false
            return
        }
    }
    return strings.to_string(sb), true
}
