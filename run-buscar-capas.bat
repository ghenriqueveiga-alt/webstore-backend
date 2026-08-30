@echo off
setlocal
if "%TMDB_API_KEY%"=="" (
    echo Defina a variavel de ambiente TMDB_API_KEY antes de executar.
    echo Obtenha gratis em: https://www.themoviedb.org/settings/api
    echo.
    echo Exemplo: set TMDB_API_KEY=sua_chave_aqui
    exit /b 1
)
cd /d "%~dp0"
call gradlew.bat buscarCapas
pause
