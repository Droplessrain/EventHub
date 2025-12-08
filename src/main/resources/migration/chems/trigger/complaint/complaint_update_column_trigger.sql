create trigger update_complaint_trigger
   before update on complaint
   for each row execute function update_column();