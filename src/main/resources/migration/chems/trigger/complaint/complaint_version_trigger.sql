create trigger version_complaint_trigger
   before update on complaint
   for each row execute function increment_version();