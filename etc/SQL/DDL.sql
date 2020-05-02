-- \encoding utf8;

-- developスキーマをカスケード削除
DROP schema develop cascade;

-- developスキーマを作成（デフォルトはpublic）
create schema develop;

-- カレントスキーマをdevelopに変更
SET search_path=develop;

-- カレントスキーマを確認
select current_schema();

-- テーブルを削除
DROP TABLE IF EXISTS develop.login;
DROP TABLE IF EXISTS develop.memo;

-- ログインテーブル
CREATE TABLE develop.login (
id char (8) NOT NULL, -- ログインID
pw varchar (8) NOT NULL, -- パスワード
update_user varchar(16),
update_date timestamp without time zone not null default now(),
PRIMARY KEY (id)
);

-- メモテーブル
CREATE TABLE develop.memo (
id char (8) NOT NULL, -- ログインID
title char (100) NOT NULL, --連番
memo varchar (1000), -- めも
update_user varchar(16),
update_date timestamp without time zone not null default now(),
PRIMARY KEY (id,title)
);

