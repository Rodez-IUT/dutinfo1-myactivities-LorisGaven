CREATE OR REPLACE FUNCTION find_all_activities_for_owner(owner_name varchar) RETURNS varchar AS $$
SELECT *
FROM activity 
JOIN user
ON owner_id = id
WHERE username = owner_name;
$$ LANGUAGE SQL;