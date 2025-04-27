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
-- set currency_id to NOT NULL
ALTER TABLE orders
ALTER COLUMN currency_id SET NOT NULL;
-- set customer_id type to UUID
ALTER TABLE orders
DROP COLUMN IF EXISTS customer_id;
--
ALTER TABLE orders
ADD COLUMN customer_id UUID NOT NULL;
-- Add foreign key constraints
ALTER TABLE orders
ADD CONSTRAINT fk_orders_shipping_address
FOREIGN KEY (shipping_address_id) REFERENCES addresses(id);
--
ALTER TABLE orders
ADD CONSTRAINT fk_orders_billing_address
FOREIGN KEY (billing_address_id) REFERENCES addresses(id);

-- orderItems
-- set productId type to UUID
ALTER TABLE order_items
DROP COLUMN IF EXISTS product_id;
--
ALTER TABLE order_items
ADD COLUMN product_id UUID NOT NULL;