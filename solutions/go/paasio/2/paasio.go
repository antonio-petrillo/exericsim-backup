package paasio

import (
	"io"
	"sync"
)

type counter struct {
	mutex      *sync.RWMutex
	countBytes int64
	countOps   int
}

type readCounter struct {
	counter
	reader io.Reader
}

type writeCounter struct {
	counter
	writer io.Writer
}

type readWriteCounter struct {
	WriteCounter
	ReadCounter
}

func NewWriteCounter(writer io.Writer) WriteCounter {
	return &writeCounter{counter{mutex: new(sync.RWMutex)}, writer}
}

// I don't have a clear opinion on which one is easier to read 'new(sync.RWMutes)' or '&sync.RWMutex{}', the latter is shorter for sure
func NewReadCounter(reader io.Reader) ReadCounter {
	return &readCounter{counter{mutex: &sync.RWMutex{}}, reader}
}

func NewReadWriteCounter(readwriter io.ReadWriter) ReadWriteCounter {
	return &readWriteCounter{NewWriteCounter(readwriter), NewReadCounter(readwriter)}
}

func (rc *readCounter) Read(p []byte) (int, error) {
	rc.mutex.Lock()
	defer rc.mutex.Unlock()

	n, err := rc.reader.Read(p)
	rc.countOps++
	rc.countBytes += int64(n)

	return n, err
}

func (rc *readCounter) ReadCount() (int64, int) {
	rc.mutex.RLock()
	defer rc.mutex.RUnlock()

	return rc.countBytes, rc.countOps
}

func (wc *writeCounter) Write(p []byte) (int, error) {
	wc.mutex.Lock()
	defer wc.mutex.Unlock()

	n, err := wc.writer.Write(p)
	wc.countOps++
	wc.countBytes += int64(n)

	return n, err
}

func (wc *writeCounter) WriteCount() (int64, int) {
	wc.mutex.RLock()
	defer wc.mutex.RUnlock()

	return wc.countBytes, wc.countOps
}
