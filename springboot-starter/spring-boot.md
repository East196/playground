

[spring](https://spring.io/projects/spring-framework)
[spring boot](https://docs.spring.io/spring-boot/docs/2.0.5.RELEASE/reference/htmlsingle/)
[spring data rest](https://docs.spring.io/spring-data/rest/docs/3.0.9.RELEASE/reference/html/)
> 以上可以考虑全部翻译

devtools  
data-rest @RepositoryRestResource
exposeId config
cors    config+ @CrossOrigin
valid 	使用JSR303 注解形式
jwt login获取jwttoken, 请求时 header authorization:'Bearer {jwttoken}'
```
http POST :8090/user/login email=e@qq.com password=123456
http GET :8090/users  authorization:'Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJlQHFxLmNvbSIsInJvbGVzIjoidXNlciIsImlhdCI6MTUzNjc1NDg5N30.LY8iTOcvBq-oRGbxPohl05LUTl8peTQCrm11qPvvY3k'
```
spring-boot-admin pom @EnableAdminServer spring.boot.admin.url=http://localhost:8094


