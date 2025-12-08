create or replace function update_column()
  returns trigger
as
$body$
begin
  new.updated_date := current_timestamp;
return new;
end;
$body$
language plpgsql;