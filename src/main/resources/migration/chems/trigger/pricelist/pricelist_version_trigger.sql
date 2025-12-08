create trigger version_pricelist_trigger
   before update on pricelist
   for each row execute function increment_version();