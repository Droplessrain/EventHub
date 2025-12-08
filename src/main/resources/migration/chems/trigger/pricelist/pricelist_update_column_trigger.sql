create trigger update_pricelist_trigger
   before update on pricelist
   for each row execute function update_column();