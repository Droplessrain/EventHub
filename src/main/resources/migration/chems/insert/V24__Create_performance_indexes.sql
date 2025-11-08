CREATE INDEX IF NOT EXISTS idx_users_email ON users (email);
CREATE INDEX IF NOT EXISTS idx_event_date ON event (date);
CREATE INDEX IF NOT EXISTS idx_requestBooking_status ON requestBooking (status);
CREATE INDEX IF NOT EXISTS idx_pricelist_contractor ON pricelist (contractorId);
CREATE INDEX IF NOT EXISTS idx_feedback_contractor ON feedback (contractorId);
CREATE INDEX IF NOT EXISTS idx_complaint_status ON complaint (status);