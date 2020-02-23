const express = require('express')
const bodyParser = require('body-parser')
const mongoose = require('mongoose')
const config = require('./src/config')
const systemsRoutes = require('./src/routes/systems')
const planetsRoutes = require('./src/routes/planets')
const userRoutes = require('./src/routes/users')
const jwt = require('jsonwebtoken')

const app = express()
app.use(bodyParser.json())
app.set('secretKey', 'variableValue')

mongoose.Promise = global.Promise
mongoose.connect(config.mongoDBURL,
    { useNewUrlParser: true, useUnifiedTopology: true })

app.get('/', (req, res) => {
    res.json({ server: 'ok' })
})
app.use('/api', userRoutes)
app.use('/api', validateUser, systemsRoutes)
app.use('/api', validateUser, planetsRoutes)

function validateUser(req, res, next) {
    jwt.verify(req.headers['x-access-token'], 
    req.app.get('secretKey'),
    (err, decoded) => {
        if(err) {
            res.json({status: 'error', message: err.message, data: null})
        } else {
            req.body.userId = decoded.id
            next()
        }
    })
}


const port = config.port
const server = app.listen(port, () => {
    const address = server.address().address
    console.log(`Server opened in http://${address}:${port}`)
})
