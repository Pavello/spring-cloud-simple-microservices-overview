1. Uruchom stos monitoringu `docker-compose up -d `
2. Otwórz grafanę http://localhost:3000/

Login: admin
Hasło: admin

3. Dodaj Prometherus'a jako źródło danych:

* Configuration -> Data Sources -> Add Data Source
* Url: http://host.docker.internal:9090
