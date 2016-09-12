const path = require('path')  
const exphbs = require('express-handlebars')
const express = require('express')
const app = express()
const port = 3000

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

app.get('/users', (request, response) => {
	const pg = require('pg')  
	const conString = 'postgres://postgres:Rb5!!!!!@localhost/postgres' // make sure to match your own database's credentials
	pg.connect(conString, function (err, client, done) {
		if (err) {
		  // pass the error to the express error handler
		  return next(err)
		}	
		
		var users = {Users:[]};
		client.query('SELECT name, age FROM users;', [], function (err, result) {
			done()
			if (err) {
			// pass the error to the express error handler
			return next(err)
			}
			users.Users  = result.rows;
			response.render('users',users);
		})
	}) 
});

app.listen(port, (err) => {  
  if (err) {
    return console.log('something bad happened', err)
  }

  console.log('server is listening on '+port)
})