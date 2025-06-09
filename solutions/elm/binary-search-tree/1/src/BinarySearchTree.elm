module BinarySearchTree exposing (BinaryTree(..), makeTree, sort)


type BinaryTree
    = Empty
    | Tree BinaryTree Int BinaryTree


makeTree : List Int -> BinaryTree
makeTree data =
    List.foldl insert Empty data


insert : Int -> BinaryTree -> BinaryTree
insert n t =
    case t of
        Empty -> 
            Tree Empty n Empty
        Tree left root right ->
            case compare root n of
                LT -> Tree left root (insert n right)
                GT -> Tree (insert n left) root right
                EQ -> Tree (insert n left) root right


sort : List Int -> List Int
sort = makeTree >> flatten


flatten : BinaryTree -> List Int
flatten t =
    case t of
        Empty -> 
            []
        Tree left root right ->
            flatten left ++ [root] ++ flatten right