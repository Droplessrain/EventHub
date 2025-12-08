create trigger update_request_booking_trigger
   before update on request_booking
   for each row execute function update_column();