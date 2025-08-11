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
                    let
                        answer =
                            findWithinLarger alist blist alen blen
                    in
                    case answer of
                        Equal ->
                            Sublist

                        _ ->
                            answer

                GT ->
                    let
                        answer =
                            findWithinLarger alist blist alen blen
                    in
                    case answer of
                        Equal ->
                            Superlist

                        _ ->
                            answer


findWithinLarger : List a -> List a -> Int -> Int -> ListComparison
findWithinLarger alist blist alen blen =
    case compare alen blen of
        EQ ->
            if alist == blist then
                Equal

            else
                Unequal

        LT ->
            let
                tail =
                    tailWithDefault blist
            in
            if startsWith alist blist then
                Sublist

            else
                findWithinLarger alist tail alen (blen - 1)

        GT ->
            let
                tail =
                    tailWithDefault alist
            in
            if startsWith blist alist then
                Superlist

            else
                findWithinLarger tail blist (alen - 1) blen


tailWithDefault : List a -> List a
tailWithDefault =
    Maybe.withDefault [] << List.tail


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
