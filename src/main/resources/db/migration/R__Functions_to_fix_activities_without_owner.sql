CREATE OR REPLACE FUNCTION get_default_owner() RETURNS "user" AS $$
	DECLARE
		default_owner "user"%ROWTYPE;
	BEGIN
	   SELECT * INTO default_owner FROM "user" WHERE username = 'Default Owner';
	   IF NOT FOUND THEN
	    	INSERT INTO "user" (id, username) VALUES (nextval('id_generator'), 'Default Owner');
	    	SELECT * INTO default_owner FROM "user" WHERE username = 'Default Owner';
	   END IF;
	   RETURN default_owner;
	END
$$ LANGUAGE 'plpgsql';


CREATE OR REPLACE FUNCTION fix_activities_without_owner() RETURNS SETOF activity AS $$
	DECLARE
		id_default_owner bigint;
	BEGIN
		SELECT id INTO id_default_owner FROM "user" WHERE username = 'Default Owner';
		UPDATE activity SET owner_id = id_default_owner  WHERE owner_id IS NULL;
		RETURN QUERY SELECT * FROM activity WHERE owner_id = id_default_owner ;
	END
$$ LANGUAGE 'plpgsql';

		