CREATE TABLE countries (
   id UUID DEFAULT uuid_generate_v4() NOT NULL,
   is_active BOOLEAN DEFAULT TRUE NOT NULL,
   created_at TIMESTAMP WITHOUT TIME ZONE DEFAULT NOW() NOT NULL,
   updated_at TIMESTAMP WITHOUT TIME ZONE DEFAULT NOW() NOT NULL,
   name VARCHAR(255) NOT NULL,
   iso_code VARCHAR(255) NOT NULL,
   CONSTRAINT pk_countries PRIMARY KEY (id)
);
ALTER TABLE countries ADD CONSTRAINT uc_countries_isocode UNIQUE (iso_code);

CREATE TABLE states (
  id UUID DEFAULT uuid_generate_v4() NOT NULL,
   is_active BOOLEAN DEFAULT TRUE NOT NULL,
   created_at TIMESTAMP WITHOUT TIME ZONE DEFAULT NOW() NOT NULL,
   updated_at TIMESTAMP WITHOUT TIME ZONE DEFAULT NOW() NOT NULL,
   name VARCHAR(255) NOT NULL,
   country_id UUID NOT NULL,
   CONSTRAINT pk_states PRIMARY KEY (id)
);
ALTER TABLE states ADD CONSTRAINT uc_001ccadf1dce53cc6b739b27f UNIQUE (name, country_id);
ALTER TABLE states ADD CONSTRAINT FK_STATES_ON_COUNTRY FOREIGN KEY (country_id) REFERENCES countries (id);

CREATE TABLE cities (
   id UUID DEFAULT uuid_generate_v4() NOT NULL,
   is_active BOOLEAN DEFAULT TRUE NOT NULL,
   created_at TIMESTAMP WITHOUT TIME ZONE DEFAULT NOW() NOT NULL,
   updated_at TIMESTAMP WITHOUT TIME ZONE DEFAULT NOW() NOT NULL,
   name VARCHAR(255) NOT NULL,
   state_id UUID NOT NULL,
   CONSTRAINT pk_cities PRIMARY KEY (id)
);
ALTER TABLE cities ADD CONSTRAINT uc_921bd305f716e7decc11cf378 UNIQUE (name, state_id);
ALTER TABLE cities ADD CONSTRAINT FK_CITIES_ON_STATE FOREIGN KEY (state_id) REFERENCES states (id);

CREATE TABLE addresses (
   id UUID DEFAULT uuid_generate_v4() NOT NULL,
   is_active BOOLEAN DEFAULT TRUE NOT NULL,
   created_at TIMESTAMP WITHOUT TIME ZONE DEFAULT NOW() NOT NULL,
   updated_at TIMESTAMP WITHOUT TIME ZONE DEFAULT NOW() NOT NULL,
   line1 VARCHAR(255) NOT NULL,
   line2 VARCHAR(255),
   land_mark VARCHAR(255),
   zip_code VARCHAR(255),
   city_id UUID NOT NULL,
   user_id UUID NOT NULL,
   latitude DOUBLE PRECISION,
   longitude DOUBLE PRECISION,
   CONSTRAINT pk_addresses PRIMARY KEY (id)
);
ALTER TABLE addresses ADD CONSTRAINT FK_ADDRESSES_ON_CITY FOREIGN KEY (city_id) REFERENCES cities (id);

-- only populate data for India tier1, tier2 cities, in future we can expand our services
-- populate countries
INSERT INTO countries (name, iso_code, created_at, updated_at)
VALUES
    ('INDIA', 'IN', now(), now());

-- populate states
INSERT INTO states (name, country_id, created_at, updated_at)
VALUES
    -- india
    ('HARYANA', (SELECT id FROM countries WHERE iso_code = 'IN'), now(), now()),
    ('DELHI', (SELECT id FROM countries WHERE iso_code = 'IN'), now(), now()),
    ('MAHARASHTRA', (SELECT id FROM countries WHERE iso_code = 'IN'), now(), now()),
    ('KARNATAKA', (SELECT id FROM countries WHERE iso_code = 'IN'), now(), now());

-- populate cities
INSERT INTO cities (name, state_id, created_at, updated_at)
VALUES
    -- HARYANA
    ('GURGAON', (SELECT id FROM states WHERE name = 'HARYANA'), now(), now()),

    -- Delhi
    ('NEW DELHI', (SELECT id FROM states WHERE name = 'DELHI'), now(), now()),
    ('SOUTH DELHI', (SELECT id FROM states WHERE name = 'DELHI'), now(), now()),

    -- Maharashtra
    ('MUMBAI', (SELECT id FROM states WHERE name = 'MAHARASHTRA'), now(), now()),
    ('PUNE', (SELECT id FROM states WHERE name = 'MAHARASHTRA'), now(), now()),

    -- Karnataka
    ('BANGALORE', (SELECT id FROM states WHERE name = 'KARNATAKA'), now(), now()),
    ('MYSORE', (SELECT id FROM states WHERE name = 'KARNATAKA'), now(), now());