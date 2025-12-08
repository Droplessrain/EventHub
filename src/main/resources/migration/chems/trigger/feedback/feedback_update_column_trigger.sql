create trigger update_feedback_trigger
   before update on feedback
   for each row execute function update_column();