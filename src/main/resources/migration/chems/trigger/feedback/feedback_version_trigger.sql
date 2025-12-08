create trigger version_feedback_trigger
   before update on feedback
   for each row execute function increment_version();