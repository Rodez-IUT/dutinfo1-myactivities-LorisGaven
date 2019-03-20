CREATE OR REPLACE FUNCTION check_activity_delete() RETURNS trigger AS $$
	DECLARE
		john_id bigint;
	BEGIN
		john_id := OLD.id; 
		
    	INSERT INTO action_log (id, action_name, entity_name, entity_id, author)
    	VALUES (nextval('id_generator'), lower(TG_OP), TG_RELNAME, john_id, current_user);

    	RETURN NULL;
    	
    END;        	
$$ LANGUAGE 'plpgsql';

/*CREATE TRIGGER delete_john
	AFTER DELETE ON activity
	FOR EACH ROW EXECUTE PROCEDURE check_activity_delete();*/