CREATE OR REPLACE FUNCTION add_activity_with_title(title varchar) RETURNS bigint AS $$
	INSERT INTO activity (id, title)
	VALUES (nextval('id_generator'), title)
	RETURNING id;
$$ LANGUAGE SQL;