module MonsterAttack exposing (..)


type alias MonsterDamage =
    String


attackWithSword1 : MonsterDamage -> Int -> MonsterDamage
attackWithSword1 monsterDamage strength =
    monsterDamage ++ "Attacked with sword of strength " ++ String.fromInt strength ++ "."


attackWithClaw1 : MonsterDamage -> Int -> MonsterDamage
attackWithClaw1 monsterDamage strength =
    monsterDamage ++ "Attacked with claw of strength " ++ String.fromInt strength ++ "."


attack1 : MonsterDamage -> MonsterDamage
attack1 =
    let
        annalyn = \acc -> attackWithSword1 acc 5
        kazak = \acc -> attackWithClaw1 acc 1
    in
        annalyn >> kazak >> kazak >> annalyn


attackWithSword2 : Int -> MonsterDamage -> MonsterDamage
attackWithSword2 strength monsterDamage = attackWithSword1 monsterDamage strength


attackWithClaw2 : Int -> MonsterDamage -> MonsterDamage
attackWithClaw2 strength monsterDamage = attackWithClaw1 monsterDamage strength


attack2 : MonsterDamage -> MonsterDamage
attack2 =
    let
        annalyn = attackWithSword2 5
        kazak = attackWithClaw2 1
    in
        annalyn >> kazak >> kazak >> annalyn


attack3 : MonsterDamage -> MonsterDamage
attack3 = attack2
