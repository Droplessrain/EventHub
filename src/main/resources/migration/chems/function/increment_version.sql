create or replace function increment_version()
  returns trigger
as
$body$
begin
  new.version := new.version + 1;
  return new;
end;
$body$
language plpgsql;