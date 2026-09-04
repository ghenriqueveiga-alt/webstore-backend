$ErrorActionPreference = "Stop"
$wd = "C:\Users\Henrique\Documents\Dev\webstore\webstore-backend"
$c = (Get-Content -LiteralPath "$wd\main-cmd.txt" -Raw).Trim()
$exe = "C:\Program Files\Java\jdk-25.0.3\bin\java.exe"
$args = $c.Substring($c.IndexOf('"', 1) + 1).Trim()
Stop-Process -Id 10268 -Force -ErrorAction SilentlyContinue
Start-Sleep -Seconds 3
Start-Process -FilePath $exe -ArgumentList $args -WorkingDirectory $wd -WindowStyle Hidden -RedirectStandardOutput "$wd\backend-restart.log" -RedirectStandardError "$wd\backend-restart-err.log"
"reiniciado"
