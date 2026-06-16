docker run -d \
  --name postgres \
  -p 5432:5432 \
  -e POSTGRES_USER=postgres \
  -e POSTGRES_PASSWORD=postgres \
  postgres:17

  docker exec -it postgres psql _U postgres
  CREATE DATABASE account_db;
  CREATE DATABASE user_db;
  CREATE DATABASE payment_db;
  CREATE DATABASE fraud_db;
  CREATE DATABASE audit_db;
  CREATE DATABASE ledger_db;
  CREATE DATABASE customer_db;
  CREATE DATABASE notification_db;
  CREATE DATABASE reconciliation_db;
  CREATE DATABASE payment_db;