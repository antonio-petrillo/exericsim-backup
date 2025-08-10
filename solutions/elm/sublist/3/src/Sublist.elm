module Sublist exposing (ListComparison(..), sublist)


type ListComparison
    = Equal
    | Superlist
    | Sublist
    | Unequal


sublist : List a -> List a -> ListComparison
sublist alist blist =
    case ( alist, blist ) of
        ( [], [] ) ->
            Equal

        ( [], _ ) ->
            Sublist

        ( _, [] ) ->
            Superlist

        _ ->
            let
                alen =
                    List.length alist

                blen =
                    List.length blist
            in
            case compare alen blen of
                EQ ->
                    if alist == blist then
                        Equal

                    else
                        Unequal

                LT ->
                    findWithinLarger Sublist alist blist alen blen

                GT ->
                    findWithinLarger Superlist alist blist alen blen


findWithinLarger : ListComparison -> List a -> List a -> Int -> Int -> ListComparison
findWithinLarger lookingFor alist blist alen blen =
    case compare alen blen of
        EQ ->
            if alist == blist then
                lookingFor

            else
                Unequal

        LT ->
            let
                tail =
                    case List.tail blist of
                        Just tl ->
                            tl

                        Nothing ->
                            []
            in
            if startsWith alist blist then
                Sublist

            else
                findWithinLarger lookingFor alist tail alen (blen - 1)

        GT ->
            let
                tail =
                    case List.tail alist of
                        Just tl ->
                            tl

                        Nothing ->
                            []
            in
            if startsWith blist alist then
                Superlist

            else
                findWithinLarger lookingFor tail blist (alen - 1) blen


startsWith : List a -> List a -> Bool
startsWith needle haystack =
    case ( needle, haystack ) of
        ( [], _ ) ->
            True

        ( _, [] ) ->
            False

        ( a :: needleTail, b :: haystackTail ) ->
            if a == b then
                startsWith needleTail haystackTail

            else
                False
