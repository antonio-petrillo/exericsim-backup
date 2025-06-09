module Sublist exposing (ListComparison(..), sublist)


type ListComparison
    = Equal
    | Superlist
    | Sublist
    | Unequal


sublist : List a -> List a -> ListComparison
sublist alist blist =
    case (alist, blist) of
        ([], []) ->
            Equal

        ([], _) ->
            Sublist

        (_, []) ->
            Superlist

        _ ->
            let
                alen = List.length alist
                blen = List.length blist
            in
                case compare alen blen of
                    EQ ->
                        if alist == blist then Equal else Unequal
                    LT ->
                        sublistWithDefault Sublist alist blist alen blen
                    GT ->
                        sublistWithDefault Superlist alist blist alen blen


sublistWithDefault : ListComparison ->  List a -> List a -> Int -> Int -> ListComparison
sublistWithDefault default alist blist alen blen =
    case compare alen blen of
        EQ ->
            if alist == blist then
                default
            else
                Unequal

        LT ->
            let
                btrim = List.take alen blist
            in
                if alist == btrim then
                    Sublist
                else
                    sublistWithDefault default alist (List.drop 1 blist) alen (blen - 1)

        GT ->
            let
                atrim = List.take blen alist
            in
                if atrim == blist then
                    Superlist
                else
                    sublistWithDefault default (List.drop 1 alist) blist (alen - 1) blen
