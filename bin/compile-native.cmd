@pushd %~dp0
@cd..
call mvn -Pnative native:compile
@popd