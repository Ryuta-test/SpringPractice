echo off

echo SQLすべてを実行します

psql -f EXECUTE_ALL_SQL.sql -U <ユーザー名> -d <DB名>

pause >nul
exit
