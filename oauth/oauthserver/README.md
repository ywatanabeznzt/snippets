# README

https://docs.spring.io/spring-security-oauth2-boot/docs/2.0.0.RELEASE/reference/html5/


1. 認可コードの取得
http://localhost:8080/oauth/authorize?response_type=code&client_id=child&redirect_uri=http://oauth-callback.com&scope=all

2. 認可コードからアクセストークン取得
curl "http://child:childpassword@localhost:8080/oauth/token" -d grant_type=authorization_code -d code=${code} -d redirect_uri=http://oauth-callback.com
> {"access_token":"xxxxxxxx-xxxx-xxxx-xxxx-xxxxxxxxxxxx","token_type":"bearer","expires_in":42866,"scope":"all"}

