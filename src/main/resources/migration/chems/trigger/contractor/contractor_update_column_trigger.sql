create trigger update_contractor_trigger
   before update on contractor
   for each row execute function update_column();