-- Enable UUID extension
CREATE EXTENSION IF NOT EXISTS "uuid-ossp" SCHEMA public;

CREATE TABLE inventories (
   id UUID DEFAULT uuid_generate_v4() NOT NULL,
   is_active BOOLEAN DEFAULT TRUE NOT NULL,
   created_at TIMESTAMP WITHOUT TIME ZONE DEFAULT NOW() NOT NULL,
   updated_at TIMESTAMP WITHOUT TIME ZONE DEFAULT NOW() NOT NULL,
   product_id UUID NOT NULL,
   quantity INTEGER DEFAULT 0 NOT NULL,
   CONSTRAINT pk_inventories PRIMARY KEY (id)
);
ALTER TABLE inventories ADD CONSTRAINT uc_inventories_productid UNIQUE (product_id);