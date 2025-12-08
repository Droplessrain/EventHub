create trigger version_contractor_trigger
   before update on contractor
   for each row execute function increment_version();