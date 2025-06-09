module TopScorers exposing (..)

import Dict exposing (Dict)
import TopScorersSupport exposing (PlayerName)


updateGoalCountForPlayer : PlayerName -> Dict PlayerName Int -> Dict PlayerName Int
updateGoalCountForPlayer playerName playerGoalCounts =
    Dict.update playerName (\val -> case val of
                                        Nothing -> Just 1
                                        Just count -> Just (count + 1)) playerGoalCounts


aggregateScorers : List PlayerName -> Dict PlayerName Int
aggregateScorers playerNames =
    List.foldl updateGoalCountForPlayer Dict.empty playerNames


removeInsignificantPlayers : Int -> Dict PlayerName Int -> Dict PlayerName Int
removeInsignificantPlayers goalThreshold =
    Dict.filter (\_ val -> val >= goalThreshold)


resetPlayerGoalCount : PlayerName -> Dict PlayerName Int -> Dict PlayerName Int
resetPlayerGoalCount playerName =
    Dict.update playerName (\_ -> Just 0)


formatPlayer : PlayerName -> Dict PlayerName Int -> String
formatPlayer playerName playerGoalCounts =
    case Dict.get playerName playerGoalCounts of
        Nothing -> playerName ++ ": 0"
        Just count -> playerName ++ ": " ++ String.fromInt count


formatPlayer2 : Dict PlayerName Int -> PlayerName -> String
formatPlayer2 dict name = formatPlayer name dict


formatPlayers : Dict PlayerName Int -> String
formatPlayers players =
   Dict.keys players
       |> List.map (formatPlayer2 players)
       |> String.join ", "

combineGames : Dict PlayerName Int -> Dict PlayerName Int -> Dict PlayerName Int
combineGames game1 game2 =
    Dict.merge
        Dict.insert
        (\player count1 count2 -> Dict.insert player (count1 + count2))
        Dict.insert
        game1
        game2
        Dict.empty
