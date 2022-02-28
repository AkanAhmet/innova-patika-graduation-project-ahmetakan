for /F %%p in ('dir /ad /b .') do (
  echo.%%p | findstr /I "ProjectImages"  1>nul
  if errorlevel 1 (
    echo Creating .jar files on target folder : %%p
    cd %%p
    mvn clean package -Dmaven.test.skip=true
    cd ..
  )
)