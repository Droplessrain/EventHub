create trigger version_event_trigger
   before update on event
   for each row execute function increment_version();