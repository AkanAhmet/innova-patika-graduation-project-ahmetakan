for /F %%p in ('dir /ad /b .') do (
  echo.%%p | findstr /I "ProjectImages" 1>nul
  if errorlevel 1 ( 
    echo Running Service : %%p
    cd %%p
    start cmd.exe /k "mvn spring-boot:run" 
    cd ..
  )
)