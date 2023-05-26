<h3>очерёдность запуска всего этого добра)</h3>

<pre>mvn clean package</pre>

<pre><i>(опционально)</i><br/>sudo chmod 755 -R .</pre>

<pre>docker build -f Dockerfile -t marvel_comics_app .</pre>

<pre>docker run -d -p 3306:3306 -v ~/Documents/java/marvel/mysql/etc/mysql/:/etc/mysql/conf.d -v ~/Documents/java/marvel/mysql/var/lib/mysql:/var/lib/mysql --name mysql-docker-container -e MYSQL_ROOT_PASSWORD=root -e MYSQL_DATABASE=sfg_dev -e MYSQL_USER=sfg_dev_user -e MYSQL_PASSWORD=123 mysql:latest</pre>

<pre>docker run -t --link mysql-docker-container:mysql -p 8080:8080 --name jar-app-marvel-container marvel_comics_app</pre>

<hr>

<h3>Само тестовое задание</h3>
<h4>Постановка задачи:</h4>
<p>Реализовать на языке программирования Java Rest API для картотеки супер героев Marvel.
Необходимо реализовать</p>    
    <ol>
        <li>
          Базовые API методы https://developer.marvel.com
            <ul>
                <li>GET /v1/public/characters</li>
                <li>GET /v1/public/characters/{characterId}</li>
                <li>GET /v1/public/characters/{characterId}/comics</li>
                <li>GET /v1/public/comics</li>
                <li>GET /v1/public/comics/{comicId}</li>
                <li>GET /v1/public/comics/{comicId}/characters</li>
            </ul>
          </li>
        <li>
          POST/PUT методы для наполнения базы/файлов с комиксами и героями
        </li>
    </ol>
      
        
<h4>Обязательные требования:</h4>
    <ol>
        <li>Постраничная загрузка, сортировка, фильтрация для запросов, возвращающих списки</li>
        <li>Работа с изображениями (загрузка, отображение)</li>
        <li>Адекватные ошибки в ответе. Например:
             <ul>
                 <li>a. 404 если не найден персонаж</li>
                 <li>b. 400 если запрос не прошёл валидацию</li>
             </ul>
        </li>
        <li>Документирование API (например, через Swagger)</li>
    </ol>
        
<h4>Допущения:</h4>
<ol>
  <li>Формат запросов и ответов может быть упрощён по сравнению с Marvel (оставлены только базовые поля)</li>
  <li>На первом этапе можно не реализовывать проверку токена безопасности</li>
</ol>
    
<h4>Требования к инструментам:</h4>
    <ol>
        <li>Язык программирования – Java</li>
        <li>Сборка – Gradle/Maven</li>
        <li>Исходный код должен быть опубликован на Github/Gitlab/Bitbucket</li>
        <li>(Крайне желательно) использование Spring/Spring Boot</li>
        <li>(Желательно) использование базы данных. Например Mongo</li>
        <li>(Совсем здорово) использование docker</li>
    </ol>
<h4>Срок выполнения: две недели</h4>
