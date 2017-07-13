const path = require('path')  
const exphbs = require('express-handlebars')
const express = require('express')
const app = express()
var assert = require('assert');
const port = 3000

var MongoClient = require('mongodb').MongoClient;

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
	
var insertDocument = function(db, callback) {
   db.collection('users').insertMany([ 
	   {
         "name" : "aurelio",
         "age" : 26         
      },{
         "name" : "maria",
         "age" : 15         
      }
	], function(err, result) {
    assert.equal(err, null);
    console.log("Inserted some documents into the users collection.");
    callback();
  });
};

app.get('/usersi', (request, response) => {
	var users = {Users:[]};
	var url = 'mongodb://localhost:27017/test';
	MongoClient.connect(url, function(err, db) {
	  assert.equal(null, err);
	  console.log("Connected correctly to server.");
		insertDocument(db, function() {
			  db.close();
		});
	})	  
});

app.get('/personrest', (request, response) => {
	var users = {Users:[]};
	var url = 'mongodb://localhost:27017/admin';
	MongoClient.connect(url, function(err, db) {
		assert.equal(null, err);
		console.log("Connected correctly to server.");
	  	  
		db.collection('person').find().toArray(function(err, data) {
			users.Users = data;
			console.log(data);
			db.close();
			response.end(JSON.stringify(users));
		});
	  
	});	
});

app.get('/users', (request, response) => {
	var users = {Users:[]};
	var url = 'mongodb://localhost:27017/test';
	MongoClient.connect(url, function(err, db) {
		assert.equal(null, err);
		console.log("Connected correctly to server.");
	  	  
		db.collection('users').find().toArray(function(err, drinks) {
			users.Users = drinks;
			console.log(drinks);
			db.close();
			response.render('users', users);
		});
	  
	});	
});

app.listen(port, (err) => {  
  if (err) {
    return console.log('something bad happened', err)
  }
  console.log('server is listening on '+port)
})