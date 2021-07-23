CREATE TABLE users
(
    id        BIGSERIAL PRIMARY KEY,
    user_name TEXT NOT NULL UNIQUE,
    password  TEXT NOT NULL
);
CREATE TABLE orders
(
    id           BIGSERIAL PRIMARY KEY,
    user_id      BIGINT REFERENCES users,
    order_number TEXT    NOT NULL UNIQUE,
    amount       INTEGER NOT NULL DEFAULT 0 CHECK (amount >= 0),
    currency     INTEGER,
    status       INTEGER NOT NULL DEFAULT 0,
    returnUrl    TEXT    NOT NULL,
    failUrl      TEXT
);