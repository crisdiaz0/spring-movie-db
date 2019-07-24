CREATE TYPE liked_status AS ENUM ('LIKED', 'DISLIKED', 'NONE');

alter table name
drop liked_status;

alter table name
add column liked_status liked_status default 'NONE';