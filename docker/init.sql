CREATE DATABASE branch;
CREATE DATABASE member;

\c branch
GRANT ALL PRIVILEGES ON DATABASE branch TO username;

\c member
GRANT ALL PRIVILEGES ON DATABASE member TO username;
