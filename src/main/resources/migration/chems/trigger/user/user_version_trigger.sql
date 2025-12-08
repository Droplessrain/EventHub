create trigger version_user_trigger
   before update on users
   for each row execute function increment_version();