package robot_simulator

Heading :: enum {
	North,
	East,
	South,
	West,
}

Position :: struct {
	x: int,
	y: int,
}

Robot :: struct {
	pos: Position,
	hd:  Heading,
}

create_robot :: proc(x, y: int, dir: Heading) -> Robot {
    return Robot{ pos = Position{ x, y }, hd = dir}
}

dirs := [Heading][2]int{
        .North = { 0, 1 },
        .East  = { 1, 0 },
        .South = { 0, -1 },
        .West  = { -1, 0 },
}

Rotation :: enum {
    Clockwise,
    Counter_Clockwise,
}

rotate :: proc(r: ^Robot, rot: Rotation) {
    n := int(r.hd)
    n += rot == .Clockwise ? 1 : -1
    n &= 3
    r.hd = Heading(n)
}

follow_commands :: proc(r: ^Robot, cmds: string) {
    pos_v := [2]int{r.pos.x, r.pos.y}
    for instr in cmds {
        switch instr {
        case 'L':
            rotate(r, .Counter_Clockwise)
        case 'R':
            rotate(r, .Clockwise)
        case 'A':
            pos_v += dirs[r.hd]
        }
    }
    r.pos.x = pos_v[0]
    r.pos.y = pos_v[1]
}
