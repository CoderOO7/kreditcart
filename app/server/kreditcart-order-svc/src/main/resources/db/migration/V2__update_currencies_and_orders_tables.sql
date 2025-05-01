-- currencies
-- populate currencies
INSERT INTO currencies (name, code, symbol, created_at, updated_at)
VALUES
  ('INDIAN RUPEE', 'INR', '₹', NOW(), NOW()),
  ('US DOLLAR', 'USD', '$', NOW(), NOW()),
  ('ARAB EMIRATES DIRHAM', 'AED', 'د.إ', NOW(), NOW());

-- orders
-- Add new columns, shipping_address_id, billing_address_id
ALTER TABLE orders
ADD COLUMN shipping_address_id UUID NOT NULL,
ADD COLUMN billing_address_id UUID NOT NULL;