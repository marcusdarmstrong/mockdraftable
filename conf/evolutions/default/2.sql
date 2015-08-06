# --- !Ups

create index i_measurement_player_id on t_measurement(player_id);
create index i_measurement_measurable_id on t_measurement(measurable_id);
create index i_player_canonical_name on t_player(canonical_name);

# --- !Downs

drop index i_measurement_player_id;
drop index i_measurement_measurable_id;
drop index i_player_canonical_name;
