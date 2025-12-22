SELECT 
    c.name,
    c.population,
    cl.language,
    cl.countrycode ,
	cty.name as "City Name"
FROM public.country c
JOIN public.countrylanguage cl
    ON c.code = cl.countrycode
	JOIN public.city cty
    ON c.code = cty.countrycode
WHERE c.population > 0
ORDER BY c.population ASC
 