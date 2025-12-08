create trigger update_event_trigger
   before update on event
   for each row execute function update_column();