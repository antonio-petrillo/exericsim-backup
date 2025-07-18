-- Schema: CREATE TABLE "leap" ( "year" INT, "is_leap" BOOL);
-- Task: update the leap table and set the is_leap based on the year field.

UPDATE "leap"
SET "is_leap" = true
WHERE year % 4 = 0 AND (year % 400 = 0 OR year % 100 <> 0)
