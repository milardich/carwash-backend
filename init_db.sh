#!/bin/bash

# Create the database, connect to it
docker exec -it carwash_db-postgres-1 psql -U postgres -c "CREATE DATABASE carwash_db;"
docker exec -it carwash_db-postgres-1 psql -U postgres -c "\c carwash_db"

# Copy sql script to docker container and execute (Create tables and fill with dummy data)
docker cp dumped_data_carwash.sql carwash_db-postgres-1:/dumped_data_carwash.sql
docker exec -it carwash_db-postgres-1 psql -U postgres -d carwash_db -f /dumped_data_carwash.sql
