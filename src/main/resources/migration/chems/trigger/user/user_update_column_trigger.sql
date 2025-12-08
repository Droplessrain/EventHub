create trigger update_user_trigger
   before update on users
   for each row execute function update_column();