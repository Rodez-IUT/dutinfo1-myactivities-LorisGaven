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

		