module CustomSet exposing (diff, disjoint, empty, equal, fromList, insert, intersect, isEmpty, member, subset, toList, union)

import List

type Set
    =  Set (List Int)


empty : Set
empty = Set []


insert : Int -> Set -> Set
insert element (Set lst) =
    if List.member element lst then 
        Set lst
    else 
        Set ( List.sort (element :: lst))


toList : Set -> List Int
toList (Set lst) = lst 
   


fromList : List Int -> Set
fromList elements =
     List.foldl insert empty elements


isEmpty : Set -> Bool
isEmpty (Set lst) = List.isEmpty lst


member : Int -> Set -> Bool
member el (Set lst) = List.member el lst


equal : Set -> Set -> Bool
equal (Set lst1) (Set lst2) =
    lst1 == lst2


union : Set -> Set -> Set
union set1 (Set lst) =
    List.foldl insert set1 lst

accumulate : List Int -> Int -> List Int -> List Int
accumulate check el acc =
    if List.member el check then
        el::acc
    else 
        acc

intersect : Set -> Set -> Set
intersect (Set lst1) (Set lst2) =
    List.foldl (accumulate lst2) [] lst1
    |> List.sort
    |> Set

accumulate2 : List Int -> Int -> List Int -> List Int
accumulate2 check el acc =
    if List.member el check then
        acc
    else 
        el::acc
        
diff : Set -> Set -> Set
diff (Set lst1) (Set lst2) =
    List.foldl (accumulate2 lst2) [] lst1
    |> List.sort
    |> Set


subset : Set -> Set -> Bool
subset (Set lst1) (Set lst2) =
    List.all (\el -> List.member el lst2) lst1


disjoint : Set -> Set -> Bool
disjoint set1 set2 =
    intersect set1 set2 
    |> isEmpty

-- reeeeaaaallllyyyyyyy bad implementation
