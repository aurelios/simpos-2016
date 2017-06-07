const path = require('path')  
const exphbs = require('express-handlebars')
const express = require('express')
const app = express()
const port = 3000

var mysql = require('mysql');
var con = mysql.createConnection({
  host: "localhost",
  user: "root",
  password: "root",
  database: "sakila"
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

app.get('/usersrest', (request, response) => {
	var users = {Users:[]};
	con.connect(function(err) {
	  if (err) throw err;
	  console.log("Connected!");
	  var sql = "SELECT name, age FROM users";
	  con.query(sql, function (err, result) {
		if (err) throw err;
		console.log(result);
		users.Users  = result;
		response.end(JSON.stringify(users));
	  });
	});	
});

app.get('/users', (request, response) => {

	var users = {Users:[]};
	con.connect(function(err) {
	  if (err) throw err;
	  console.log("Connected!");
	  var sql = "SELECT name, age FROM users";
	  con.query(sql, function (err, result) {
		if (err) throw err;
		console.log(result);
		users.Users  = result;
		response.render('users',users);
	  });
	});
});

app.listen(port, (err) => {  
  if (err) {
    return console.log('something bad happened', err)
  }

  console.log('server is listening on '+port)
})