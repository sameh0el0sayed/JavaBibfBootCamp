--Clue #1
--Least populated country in Southern Europe
SELECT name, population
FROM country
WHERE region = 'Southern Europe'
ORDER BY population ASC
LIMIT 1;

--Clue #2
--Official language of this country

SELECT language
FROM countrylanguage
WHERE countrycode = (
    SELECT code
    FROM country
    WHERE name = 'Holy See (Vatican City State)'
)
AND isofficial = true;

--Result=Italian

--Clue #3
--Nearby country that speaks ONLY Italian
SELECT DISTINCT c.name
FROM country c
JOIN countrylanguage cl ON c.code = cl.countrycode
WHERE cl.language = 'Italian'
AND cl.isofficial = true;

SELECT c.name
FROM country c
WHERE EXISTS (
    SELECT 1
    FROM countrylanguage cl
    WHERE cl.countrycode = c.code
      AND cl.language = 'Italian'
)
AND NOT EXISTS (
    SELECT 1
    FROM countrylanguage cl
    WHERE cl.countrycode = c.code
      AND cl.language <> 'Italian'
);

--Clue #4
--Other city in that country (not same as country name)
SELECT ci.name
FROM city ci
JOIN country c ON ci.countrycode = c.code
WHERE c.name = 'San Marino'
AND ci.name <> c.name;

--result "Serravalle"
--Clue #5 Find a similar city name in South America, but not ending the same

SELECT ci.name AS city, c.name AS country
FROM city ci
JOIN country c ON ci.countrycode = c.code
WHERE c.continent = 'South America'
AND ci.name ILIKE 'Serr%';

--Result "Serra"	"Brazil"

--Clue #6 Find the capital of that South American country
SELECT ci.name
FROM country c
JOIN city ci ON c.capital = ci.id
WHERE c.name = 'Brazil';

--result="Brasï¿½lia"

--Clue #7 (Final Riddle) City with population = 91,085
SELECT ci.name AS city, c.name AS country ,ci.population
FROM city ci
JOIN country c ON ci.countrycode = c.code
WHERE ci.population = 91084;
--result is "Santa Monica"	"United States"	91084
--CARMEN in Santa Monica!