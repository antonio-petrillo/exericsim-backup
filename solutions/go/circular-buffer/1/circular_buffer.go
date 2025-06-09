package circular

import "errors"

// Implement a circular buffer of bytes supporting both overflow-checked writes
// and unconditional, possibly overwriting, writes.
//
// We chose the provided API so that Buffer implements io.ByteReader
// and io.ByteWriter and can be used (size permitting) as a drop in
// replacement for anything using that interface.

// Define the Buffer type here.

type Buffer struct {
    buffer []byte
    size int 
    cap  int
    popIndex int
    pushIndex int
}

func NewBuffer(size int) *Buffer {
	return &Buffer{
        buffer: make([]byte, size),
        size: size,
        cap: 0,
        popIndex: 0,
        pushIndex: 0,
    }
}

func (b *Buffer) ReadByte() (byte, error) {
	if b.cap == 0 {
        return 0, errors.New("cannot read from empty buffer")
    }
	c := b.buffer[b.popIndex]
    b.popIndex = (b.popIndex + 1) % b.size
    b.cap--
    return c, nil
}

func (b *Buffer) WriteByte(c byte) error {
	if b.cap == b.size {
        return errors.New("buffer full")
    }
	b.buffer[b.pushIndex] = c
    b.pushIndex = (b.pushIndex + 1) % b.size
    b.cap++
    return nil
}

func (b *Buffer) Overwrite(c byte) {
	b.buffer[b.pushIndex] = c 
    if b.cap == b.size {
        b.popIndex = (b.popIndex + 1) % b.size
    } else {
    	b.cap++
    }
	b.pushIndex = (b.pushIndex + 1) % b.size
}

func (b *Buffer) Reset() {
	b.pushIndex = 0
    b.popIndex = 0
    b.cap = 0
}
