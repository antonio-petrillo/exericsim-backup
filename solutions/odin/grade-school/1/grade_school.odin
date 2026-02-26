package grade_school

import "core:slice"

State :: enum {
    Uninitialized,
    Unsorted,
    Sorted,
}

// I don't know how many grades there are outside of Italy, I think 10 is enough
MAX_GRADES :: 10

School :: struct{
    students: map[string]struct{},
    grades: [MAX_GRADES][dynamic]string,
    states: [MAX_GRADES]State,
}

Grade :: struct {
	id:       u8,
	students: []string,
}

add :: proc(self: ^School, student: string, grade: u8) -> bool {
    if student in self.students {
        return false
    }

    if self.states[grade] == .Uninitialized {
        self.grades[grade] = make([dynamic]string)
    }

    self.students[student] = struct{}{}
    append(&self.grades[grade], student)
    self.states[grade] = .Unsorted

    return true
}

grade :: proc(self: ^School, id: u8) -> []string {
    if self.states[id] == .Uninitialized {
        return nil
    }

    if self.states[id] == .Unsorted {
        slice.sort(self.grades[id][:])
        self.states[id] = .Sorted
    }
    return self.grades[id][:]
}

roster :: proc(self: ^School) -> []Grade {
    res := make([dynamic]Grade)
    for i in 0..<MAX_GRADES {
        switch self.states[i] {
        case .Uninitialized: continue
        case .Unsorted:
            slice.sort(self.grades[i][:])
            fallthrough
        case .Sorted:
            append(&res, Grade{u8(i), self.grades[i][:]})
        }
    }
    return res[:]
}

delete_school :: proc(self: ^School) {
    delete(self.students)
    for i in 0..<MAX_GRADES {
        if self.states[i] == .Uninitialized {
            continue
        }
        delete(self.grades[i])
    }
}
