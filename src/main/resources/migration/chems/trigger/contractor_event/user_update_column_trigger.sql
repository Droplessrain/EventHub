create trigger update_contractor_event_trigger
   before update on contractor_event
   for each row execute function update_column();