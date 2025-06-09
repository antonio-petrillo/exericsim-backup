module MazeMaker exposing (..)

import Random exposing (Generator)


type Maze
    = DeadEnd
    | Room Treasure
    | Branch (List Maze)


type Treasure
    = Gold
    | Diamond
    | Friendship


deadend : Generator Maze
deadend =
    Random.constant DeadEnd


treasure : Generator Treasure
treasure =
    Random.uniform Friendship [Gold, Diamond]


room : Generator Maze
room =
    Random.map Room treasure

branch : Generator Maze -> Generator Maze
branch mazeGenerator =
    Random.int 2 4
        |> Random.andThen (\num -> Random.list num mazeGenerator )
        |> Random.map Branch


-- Basically the peano example
maze : Generator Maze
maze =
    Random.weighted (60, deadend)
        [ ( 15, room )
        , ( 25, branch (Random.lazy (\_ -> maze)))
        ]
        |> Random.andThen identity


mazeOfDepth : Int -> Generator Maze
mazeOfDepth depth =
    if depth > 0 then
        depth - 1
            |> mazeOfDepth
            |> branch
     else -- A negative deepth produce a room (is treated as 0)
        Random.uniform deadend [room]
            |> Random.andThen identity
