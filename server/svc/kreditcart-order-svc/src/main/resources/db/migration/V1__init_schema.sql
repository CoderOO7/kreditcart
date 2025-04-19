CREATE TABLE country (
  id BIGINT NOT NULL,
   is_active BIT(1) NOT NULL,
   created_at datetime NOT NULL,
   updated_at datetime NOT NULL,
   name VARCHAR(255) NOT NULL,
   iso_code VARCHAR(255) NOT NULL,
   CONSTRAINT pk_country PRIMARY KEY (id)
);
ALTER TABLE country ADD CONSTRAINT uc_country_isocode UNIQUE (iso_code);


CREATE TABLE state (
  id BIGINT NOT NULL,
   is_active BIT(1) NOT NULL,
   created_at datetime NOT NULL,
   updated_at datetime NOT NULL,
   name VARCHAR(255) NOT NULL,
   country_id BIGINT NULL,
   CONSTRAINT pk_state PRIMARY KEY (id)
);
ALTER TABLE state ADD CONSTRAINT uc_9f4bc6b7f716080defdf804d9 UNIQUE (name, country_id);
ALTER TABLE state ADD CONSTRAINT FK_STATE_ON_COUNTRY FOREIGN KEY (country_id) REFERENCES country (id);


CREATE TABLE city (
  id BIGINT NOT NULL,
   is_active BIT(1) NOT NULL,
   created_at datetime NOT NULL,
   updated_at datetime NOT NULL,
   name VARCHAR(255) NOT NULL,
   state_id BIGINT NULL,
   CONSTRAINT pk_city PRIMARY KEY (id)
);
ALTER TABLE city ADD CONSTRAINT uc_23fe726bd40ec85dd0809257a UNIQUE (name, state_id);
ALTER TABLE city ADD CONSTRAINT FK_CITY_ON_STATE FOREIGN KEY (state_id) REFERENCES state (id);


CREATE TABLE address (
  id BIGINT NOT NULL,
   is_active BIT(1) NOT NULL,
   created_at datetime NOT NULL,
   updated_at datetime NOT NULL,
   line1 VARCHAR(255) NOT NULL,
   line2 VARCHAR(255) NULL,
   land_mark VARCHAR(255) NULL,
   zip_code VARCHAR(255) NULL,
   city_id BIGINT NULL,
   latitude DOUBLE NULL,
   longitude DOUBLE NULL,
   CONSTRAINT pk_address PRIMARY KEY (id)
);
ALTER TABLE address ADD CONSTRAINT FK_ADDRESS_ON_CITY FOREIGN KEY (city_id) REFERENCES city (id);

CREATE TABLE currency (
   id BIGINT NOT NULL,
   is_active BIT(1) NOT NULL,
   created_at datetime NOT NULL,
   updated_at datetime NOT NULL,
   name VARCHAR(50) NOT NULL,
   code VARCHAR(10) NOT NULL,
   symbol VARCHAR(5) NOT NULL,
   CONSTRAINT pk_currency PRIMARY KEY (id)
);
ALTER TABLE currency ADD CONSTRAINT uc_currency_code UNIQUE (code);


CREATE TABLE orders (
  id BIGINT NOT NULL,
   is_active BIT(1) NOT NULL,
   created_at datetime NOT NULL,
   updated_at datetime NOT NULL,
   customer_id BIGINT NOT NULL,
   order_code VARCHAR(255) NOT NULL,
   items_total_amount DOUBLE NOT NULL,
   items_total_tax DOUBLE NOT NULL,
   service_charge DOUBLE NOT NULL,
   delivery_charge DOUBLE NOT NULL,
   discount_rate DOUBLE NOT NULL,
   discount_amount DOUBLE NOT NULL,
   grand_total DOUBLE NOT NULL,
   state VARCHAR(255) NOT NULL,
   currency_id BIGINT NULL,
   CONSTRAINT pk_orders PRIMARY KEY (id)
);
ALTER TABLE orders ADD CONSTRAINT FK_ORDERS_ON_CURRENCY FOREIGN KEY (currency_id) REFERENCES currency (id);

CREATE TABLE order_items (
  id BIGINT NOT NULL,
   is_active BIT(1) NOT NULL,
   created_at datetime NOT NULL,
   updated_at datetime NOT NULL,
   product_id BIGINT NOT NULL,
   quantity INT NOT NULL,
   unit_price DOUBLE NOT NULL,
   actual_amount DOUBLE NOT NULL,
   discount_rate DOUBLE NOT NULL,
   discount_amount DOUBLE NOT NULL,
   tax_rate DOUBLE NOT NULL,
   tax_amount DOUBLE NOT NULL,
   total_amount DOUBLE NOT NULL,
   tax_type VARCHAR(255) NOT NULL,
   state VARCHAR(255) NOT NULL,
   order_id BIGINT NULL,
   CONSTRAINT pk_order_items PRIMARY KEY (id)
);
ALTER TABLE order_items ADD CONSTRAINT FK_ORDER_ITEMS_ON_ORDER FOREIGN KEY (order_id) REFERENCES orders (id);