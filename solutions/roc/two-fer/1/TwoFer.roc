module [twoFer]

twoFer : [Name Str, Anonymous] -> Str
twoFer = \name ->
    when name is
        Name s -> "One for $(s), one for me."
        Anonymous -> twoFer (Name "you")
