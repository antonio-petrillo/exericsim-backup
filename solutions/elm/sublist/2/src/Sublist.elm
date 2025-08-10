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
                        findWithinLarger Sublist alist blist alen blen
                    GT ->
                        findWithinLarger Superlist alist blist alen blen


findWithinLarger : ListComparison ->  List a -> List a -> Int -> Int -> ListComparison
findWithinLarger lookingFor alist blist alen blen =
    case compare alen blen of
        EQ ->
            if alist == blist then
                lookingFor
            else
                Unequal

        LT ->
            let
                btrim = List.take alen blist
                tail = case blist of
                           [] -> []
                           _::tl -> tl     
            in
                if alist == btrim then
                    Sublist
                else
                    findWithinLarger lookingFor alist tail alen (blen - 1)

        GT ->
            let
                atrim = List.take blen alist
                tail = case alist of
                           [] -> []
                           _::tl -> tl     
            in
                if atrim == blist then
                    Superlist
                else
                    findWithinLarger lookingFor tail blist (alen - 1) blen
