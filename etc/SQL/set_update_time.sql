-- 更新日時を自動更新する関数

create function set_update_time() returns opaque as '
  begin
    new.update_date := ''now'';
    return new;
  end;
' language 'plpgsql';
