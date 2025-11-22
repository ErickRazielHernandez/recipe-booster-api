SELECT pg_terminate_backend(pid) FROM pg_stat_activity WHERE datname='recipe-booster';
DROP DATABASE IF EXISTS "recipe-booster";
CREATE DATABASE "recipe-booster";
\c recipe-booster

BEGIN;
\i create.sql
\i catalogos.sql
COMMIT;