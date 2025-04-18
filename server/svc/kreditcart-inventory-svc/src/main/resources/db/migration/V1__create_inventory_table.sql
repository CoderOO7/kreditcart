CREATE TABLE inventory (
  id BIGINT NOT NULL,
   created_at datetime NOT NULL,
   updated_at datetime NOT NULL,
   product_id BIGINT NOT NULL,
   quantity INT NOT NULL,
   CONSTRAINT pk_inventory PRIMARY KEY (id)
);

ALTER TABLE inventory ADD CONSTRAINT uc_inventory_productid UNIQUE (product_id);