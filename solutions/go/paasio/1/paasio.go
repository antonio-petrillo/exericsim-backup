package paasio

import (
	"io"
	"sync"
)

type readCounter struct {
	mutex      *sync.RWMutex
	r          io.Reader
	countBytes int64
	countOps   int
}

type writeCounter struct {
	mutex      *sync.RWMutex
	w          io.Writer
	countBytes int64
	countOps   int
}

type readWriteCounter struct {
	wc WriteCounter
	rc ReadCounter
}

func NewWriteCounter(writer io.Writer) WriteCounter {
	return &writeCounter{w: writer, mutex: &sync.RWMutex{}}
}

func NewReadCounter(reader io.Reader) ReadCounter {
	return &readCounter{r: reader, mutex: &sync.RWMutex{}}
}

func NewReadWriteCounter(readwriter io.ReadWriter) ReadWriteCounter {
	return &readWriteCounter{wc: NewWriteCounter(readwriter), rc: NewReadCounter(readwriter)}
}

func (rc *readCounter) Read(p []byte) (int, error) {
	rc.mutex.Lock()
	defer rc.mutex.Unlock()

	n, err := rc.r.Read(p)
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

	n, err := wc.w.Write(p)
	wc.countOps++
	wc.countBytes += int64(n)

	return n, err
}

func (wc *writeCounter) WriteCount() (int64, int) {
	wc.mutex.RLock()
	defer wc.mutex.RUnlock()

	return wc.countBytes, wc.countOps
}

func (rwc *readWriteCounter) Read(p []byte) (int, error) {
	return rwc.rc.Read(p)
}

func (rwc *readWriteCounter) ReadCount() (int64, int) {
	return rwc.rc.ReadCount()
}

func (rwc *readWriteCounter) Write(p []byte) (int, error) {
	return rwc.wc.Write(p)
}

func (rwc *readWriteCounter) WriteCount() (int64, int) {
	return rwc.wc.WriteCount()
}
