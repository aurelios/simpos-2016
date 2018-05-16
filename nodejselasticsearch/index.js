/*
 http://localhost:9200/simpos/person/_search?pretty=true&q=*:*

 Executar PUT Em http://localhost:9200/simpos
 {
 "settings" : {
 "number_of_shards" : 1,
 "max_result_window" : 1000000
 }
 }


 */
const path = require('path')
const exphbs = require('express-handlebars')
const express = require('express')
const app = express()
var assert = require('assert');
const port = 3000

var elasticsearch = require('elasticsearch');
var elastic = new elasticsearch.Client({
    host: 'localhost:9200',
    log: 'info',
    requestTimeout: 190000
});

app.use('/assets', express.static(__dirname + '/assets'));

app.engine('.hbs', exphbs({
    defaultLayout: 'main',
    extname: '.hbs',
    layoutsDir: path.join(__dirname, 'views/layouts')
}))

app.set('view engine', '.hbs')
app.set('views', path.join(__dirname, 'views'))

app.get('/', function(request, response)  {
    response.render('home', {
    name: 'John'
})
})

app.get('/mysqltoelastic', function(request, response)  {
    var mysql = require('mysql');
var con = mysql.createConnection({
    host: "localhost",
    user: "root",
    password: "root",
    database: "simpos"
});


var personArray = [];
var documents = [];
con.connect(function (err) {
    if (err) throw err;
    console.log("Connected!");

    var citys = [];
    var sqlCity = "SELECT id_city, name FROM city ";
    con.query(sqlCity, function (err, result) {
        if (err) throw err2;
        citys = result;

        var sqlPerson = "SELECT id_person, name, id_city FROM person order by id_person";

        con.query(sqlPerson, function (err, result) {
            if (err) throw err;
            personArray = result;
            personArray.forEach(function (entry) {
                var person = JSON.parse(JSON.stringify(entry));

                citys.forEach(function (city) {
                    if (city && city.id_city === person.id_city)
                        person.city = JSON.parse(JSON.stringify(city));
                });

                documents.push({index: {_index: 'simpos', _type: 'person', _id: person.id_person}});
                documents.push(person);

            });
            elastic.bulk({
                body: documents
            }, function (error, response) {
                console.log('Inserting... ');
                console.log('---- Done -----');
            });

        });
    });
});
})
;

app.get('/personrest', function(request, response)  {
    var users = {Users: []};
elastic.search({
    index: 'simpos',
    type: 'person',
    size: 1000000
}).then(function (resp) {
    for (i = 0; i < resp.hits.hits.length; i++) {
        users.Users[i] = resp.hits.hits[i]._source;
    }
    response.end(JSON.stringify(users));


}, function (err) {
    console.log(err.message);
});
})
;
/*
 app.get('/users', (request, response) => {
 var users = {Users:[]};

 elastic.search({
 index: 'simpos',
 type: 'person'
 }).then(function (resp) {
 for(i = 0; i < resp.hits.hits.length; i++) {
 users.Users[i] = resp.hits.hits[i]._source;
 }
 response.render('users', users);

 }, function (err) {
 console.log(err.message);
 });
 });
 */
app.listen(port, function(err)  {
    if (err) {
        return console.log('something bad happened', err)
    }
    console.log('server is listening on ' + port)
})