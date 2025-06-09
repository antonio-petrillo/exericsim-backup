module RobotSimulator exposing
    ( Bearing(..)
    , Robot
    , advance
    , defaultRobot
    , simulate
    , turnLeft
    , turnRight
    )


type Bearing
    = North
    | East
    | South
    | West


type alias Robot =
    { bearing : Bearing
    , coordinates : { x : Int, y : Int }
    }


defaultRobot : Robot
defaultRobot =
    { bearing = North
    , coordinates = { x = 0, y = 0 }
    }


turnRight : Robot -> Robot
turnRight robot =
    let 
        bearing = case robot.bearing of
            North -> East 
            East -> South
            South -> West
            West -> North
    in     
        { robot | bearing = bearing }


turnLeft : Robot -> Robot
turnLeft robot =
    let 
        bearing = case robot.bearing of
            North -> West
            West -> South
            South -> East
            East -> North
    in 
        { robot | bearing = bearing }


advance : Robot -> Robot
advance robot =
    let 
        (dx, dy) = case robot.bearing of 
            North -> (0, 1)
            South -> (0, -1)
            East  -> (1, 0)
            West  -> (-1, 0)
        {x, y} = robot.coordinates
    in 
        Robot robot.bearing { x = x + dx, y = y + dy }

dispatch : Char -> Robot -> Robot
dispatch move =
    case move of 
        'L' -> turnLeft
        'R' -> turnRight
        'A' -> advance
        _   -> identity

simulate : String -> Robot -> Robot
simulate directions robot =
    String.foldl dispatch robot directions  
