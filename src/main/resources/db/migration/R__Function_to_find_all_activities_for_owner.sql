CREATE OR REPLACE FUNCTION find_all_activities_for_owner(owner_name varchar) RETURNS SETOF activity AS $$
	SELECT activity.*
	FROM "user"
	JOIN activity
	ON "user".id = activity.owner_id
	WHERE username = owner_name
$$ LANGUAGE SQL;