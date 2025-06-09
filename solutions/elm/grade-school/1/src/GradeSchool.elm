module GradeSchool exposing (Grade, Result(..), School, Student, addStudent, allStudents, emptySchool, studentsInGrade)

import Dict exposing (..)


type alias Grade =
    Int


type alias Student =
    String


type alias School =
    Dict Int (List Student)


type Result
    = Added
    | Duplicate


emptySchool : School
emptySchool =
   Dict.empty 


addToGrade : Student -> Maybe (List Student) -> Maybe (List Student)
addToGrade student students  =
    case students of
        Nothing -> Just [student]
        Just studentsList -> student :: studentsList |> Just


addStudent : Grade -> Student -> School -> ( Result, School )
addStudent grade student school =
    let
        isDuplicate = Dict.values school
                    |> List.concat
                    |> List.member student
    in
        if isDuplicate then
            (Duplicate, school)
        else
            (Added, Dict.update grade (addToGrade student) school)


studentsInGrade : Grade -> School -> List Student
studentsInGrade grade school =
    Dict.get grade school
        |> Maybe.withDefault []
        |> List.sort


allStudents : School -> List Student
allStudents =
   Dict.values >> List.map List.sort >> List.concat
