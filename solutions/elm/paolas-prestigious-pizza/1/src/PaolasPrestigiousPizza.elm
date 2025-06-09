module PaolasPrestigiousPizza exposing
    ( Pizza
    , ingredientsParser
    , menuParser
    , oneIngredientParser
    , pizzaParser
    , priceParser
    , vegetarianParser
    , wordParser
    )

import Parser exposing ((|.), (|=), Parser)


type alias Pizza =
    { name : String
    , vegetarian : Bool
    , ingredients : List String
    , price : Int
    }


priceParser : Parser Int
priceParser =
   Parser.succeed identity
       |= Parser.int
       |. Parser.keyword "€"


vegetarianParser : Parser Bool
vegetarianParser =
    Parser.oneOf
        [ Parser.succeed True
              |. Parser.keyword "(v)"
        , Parser.succeed False
        ]


wordParser : Parser String
wordParser =
    Parser.succeed String.toLower
        |= (Parser.getChompedString <| Parser.chompWhile Char.isAlpha)


ingredientPredicate : Char -> Bool
ingredientPredicate char =
    Char.isAlpha char || char == ' '


oneIngredientParser : Parser String
oneIngredientParser =
    Parser.chompWhile ingredientPredicate
        |> Parser.getChompedString
        |> Parser.map (String.trim >> String.toLower)
        |> Parser.andThen parseOnlyNotEmpty


ingredientsParser : Parser (List String)
ingredientsParser =
    Parser.sequence
        { start = ""
        , end = ""
        , spaces = Parser.spaces
        , separator = ","
        , trailing = Parser.Forbidden
        , item = oneIngredientParser
        }


pizzaParser : Parser Pizza
pizzaParser =
    Parser.succeed Pizza
        |= wordParser
        |. Parser.spaces
        |= vegetarianParser
        |. Parser.symbol ":"
        |. Parser.spaces
        |= ingredientsParser
        |. Parser.spaces
        |. Parser.symbol "-"
        |. Parser.spaces
        |= priceParser


isWhiteSpace : Char -> Bool
isWhiteSpace c = c == ' '

menuParser : Parser (List Pizza)
menuParser =
    Parser.sequence
        { start = ""
        , end = ""
        , spaces = Parser.chompWhile isWhiteSpace
        , separator = "\n"
        , trailing = Parser.Optional
        , item = pizzaParser
        }
        |. Parser.end


parseOnlyNotEmpty : String -> Parser String
parseOnlyNotEmpty parsed =
    if String.isEmpty parsed then
        Parser.problem "empty string"
    else
        Parser.succeed parsed
