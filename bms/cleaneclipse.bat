@echo On
@Rem Delete Eclipse Maven Configure Files
@PROMPT [Com]

@for /r . %%a in (.) do @if exist "%%a/.settings" rd /s /q "%%a/.settings"
@Rem for /r . %%a in (.) do @if exist "%%a/.settings" @echo "%%a/.settings"

@for /r . %%a in (.) do @if exist "%%a/target" rd /s /q "%%a/target"
@Rem for /r . %%a in (.) do @if exist "%%a/target" @echo "%%a/target"

@for /r . %%a in (.) do @if exist "%%a/.idea" rd /s /q "%%a/.idea"
@Rem for /r . %%a in (.) do @if exist "%%a/.idea" @echo "%%a/.idea"

@del /a /f /s /q  ".project" ".classpath" ".factorypath"   "*.iml"
@del /a /f /s /q  ".project" ".classpath"  ".factorypath"  "*.iml"

@echo Mission Completed.
@pause