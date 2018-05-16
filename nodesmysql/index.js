const path = require('path');
const exphbs = require('express-handlebars');
const express = require('express');
const app = express();
const port = 3000

var mysql = require('mysql');
/*var con = mysql.createConnection({
  host: "localhost",
  user: "root",
  password: "root",
  database: "simpos" 
});*/

var con = mysql.createPool({
	host: "localhost",
	user: "root",
	password: "root",
	database: "simpos" ,
	connectionLimit : 10000,
	connectTimeout  : 60 * 60 * 100,
    aquireTimeout   : 60 * 60 * 100,
    timeout         : 60 * 60 * 100,
});


app.use('/assets', express.static(__dirname + '/assets'));

app.engine('.hbs', exphbs({  
  defaultLayout: 'main',
  extname: '.hbs',
  layoutsDir: path.join(__dirname, 'views/layouts')
}))

app.set('view engine', '.hbs')
app.set('views', path.join(__dirname, 'views'))

app.get('/', function(request, response) {
  response.render('home', {
    name: 'John'
  })
})



app.get('/personrest', function(request, response) {
	
	getPerson(function (result){
		response.writeHead(200, {'Content-Type' : 'x-application/json'});
		response.end(result);
	});
		  
/*
	try { 
		con.getConnection(function(err2,connection){
			if (err2) { throw err2;} 
			var sql = "SELECT id_person, name, id_city FROM person where id_person < 500000;"
			connection.query(sql, function (err, result) {
				connection.release();
				if (err) { throw err;} 
				else { 
					response.writeHead(200, {'Content-Type' : 'x-application/json'});
					response.end(JSON.stringify(result));
				}
			});
		});
	} catch(e){ 
		console.log(e);
	}
	*/
});


function getPerson(callback){
 	con.getConnection(function(err,connection){
 		if (err) { 
			console.log(err);
			return callback(null);
		}
		var sql = "SELECT id_person, name, id_city FROM person ;"
		connection.query(sql, function (err, result) {
			connection.release();
			//console.log(con._freeConnections.indexOf(connection));
			if (err) { 
				console.log(err);
				return callback(null);
			} else { 
				callback(JSON.stringify(result));
			}
		});

	});
}


	/*
	con.query(sqlCity, function (err2, result) {
		if (err2) console.log(err2);
		citys = result;*/

						/*personArray  = result;

				personArray.forEach(function (entry) {
					var person = JSON.parse(JSON.stringify(entry));

					citys.forEach(function (city) {
						if (city && city.id_city === person.id_city)
							person.city = JSON.parse(JSON.stringify(city));
					});
					documents.push(person);
				});*/

			//response.end(JSON.stringify(documents));
		//});

/*
app.get('/users', (request, response) => {

	var users = {Users:[]};
	con.connect(function(err) {
	  if (err) throw err;
	  console.log("Connected!");
	  var sql = "SELECT name, age FROM person";
	  con.query(sql, function (err, result) {
		if (err) throw err;
		console.log(result);
		users.Users  = result;
		response.render('users',users);
	  });
	});
});
*/

app.listen(port, function(err) {  
  if (err) {
    return console.log('something bad happened', err)
  }

  console.log('server is listening on '+port)
})
