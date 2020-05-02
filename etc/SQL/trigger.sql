-- 更新日時を自動更新する関数のトリガー

create trigger update_trigger_login before insert or update on develop.login for each row execute procedure develop.set_update_time();
create trigger update_trigger_memo before insert or update on develop.memo for each row execute procedure develop.set_update_time();
