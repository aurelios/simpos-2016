const path = require('path')  
const exphbs = require('express-handlebars')
const express = require('express')
const app = express()
var assert = require('assert');
const port = 3000

var elasticsearch=require('elasticsearch');
var elastic = new elasticsearch.Client({  
    host: 'localhost:9200',
    log: 'info'
}); 

app.use('/assets', express.static(__dirname + '/assets'));

app.engine('.hbs', exphbs({  
  defaultLayout: 'main',
  extname: '.hbs',
  layoutsDir: path.join(__dirname, 'views/layouts')
}))

app.set('view engine', '.hbs')
app.set('views', path.join(__dirname, 'views'))

app.get('/', (request, response) => {
  response.render('home', {
    name: 'John'
  })
})

app.get('/usersi', (request, response) => {

	elastic.create({
        index: 'myapp',
        type: 'person',
        id: '1',
        body: {
            name: "aurelio",
            age: 26
        }
    }, function (error, response) {
        console.log(response);
    });
	
	elastic.create({
        index: 'myapp',
        type: 'person',
        id: '2',
        body: {
            name: "maria",
            age: 15
        }
    }, function (error, response) {
        console.log(response);
    });

	 response.end();
});

app.get('/usersrest', (request, response) => {
	var users = {Users:[]};
	elastic.search({
        index: 'myapp',
        type: 'person'
    }).then(function (resp) {
		 for(i = 0; i < resp.hits.hits.length; i++) {
		   users.Users[i] = resp.hits.hits[i]._source;
		 }
		 response.end(JSON.stringify(users));
		 
    }, function (err) {
        console.log(err.message);
    });
});

app.get('/users', (request, response) => {
	var users = {Users:[]};
	
	elastic.search({
        index: 'myapp',
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

app.listen(port, (err) => {  
  if (err) {
    return console.log('something bad happened', err)
  }
  console.log('server is listening on '+port)
})