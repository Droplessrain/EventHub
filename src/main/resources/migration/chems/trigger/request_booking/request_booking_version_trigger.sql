create trigger version_request_booking_trigger
   before update on request_booking
   for each row execute function increment_version();