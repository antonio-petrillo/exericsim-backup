module Go exposing (..)

import GoSupport exposing (..)


applyRules : Game -> Rule -> NonValidatingRule -> Rule -> Rule -> Game
applyRules game oneStonePerPointRule captureRule libertyRule koRule =
    case oneStonePerPointRule game
        |> Result.andThen libertyRule
        |> Result.andThen koRule of
        Ok nextTurn ->
            captureRule nextTurn |> changePlayer
        Err msg ->
            { game | error = msg}
