create trigger version_contractor_event_trigger
   before update on contractor_event
   for each row execute function increment_version();