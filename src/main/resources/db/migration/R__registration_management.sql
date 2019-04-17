CREATE OR REPLACE FUNCTION register_user_on_activity(id_user bigint, id_activity bigint) RETURNS registration AS $$
	DECLARE
		new_id bigint;
		user_added registration%ROWTYPE;
	BEGIN
		new_id := nextval('id_generator');
		SELECT * INTO user_added FROM registration WHERE user_id = id_user AND activity_id = id_activity;
		IF NOT FOUND THEN
			INSERT INTO registration (id, user_id, activity_id)
			VALUES (new_id, id_user, id_activity);
			SELECT * INTO user_added FROM registration WHERE registration.id = new_id;
			RETURN user_added;
		ELSE
			RAISE EXCEPTION 'registration_already_exists';
			RETURN NULL;
		END IF;
	END;
$$ LANGUAGE 'plpgsql';

	
CREATE OR REPLACE FUNCTION unregister_user_on_activity(in_id_user bigint, in_id_activity bigint) RETURNS SETOF registration AS $$
	BEGIN
		DELETE FROM registration WHERE user_id = in_id_user AND activity_id = in_id_activity;
	END;
$$ LANGUAGE 'plpgsql';

CREATE OR REPLACE FUNCTION check_registration() RETURNS trigger AS $$
	BEGIN
		IF (TG_OP = 'DELETE') THEN
			INSERT INTO action_log
			VALUES (nextval('id_generator'),lower(TG_OP),TG_RELNAME,current_user,OLD.id,NOW());
			RETURN NULL;
		ELSIF (TG_OP = 'INSERT') THEN
			INSERT INTO action_log
			VALUES (nextval('id_generator'),lower(TG_OP),TG_RELNAME,current_user,NEW.id,NOW());
			RETURN NULL;
		ELSE
			RETURN NULL;
		END IF;
	END;
$$ LANGUAGE 'plpgsql';

/*CREATE TRIGGER registration_audit
	AFTER INSERT OR DELETE ON registration
	FOR EACH ROW EXECUTE PROCEDURE check_registration();*/