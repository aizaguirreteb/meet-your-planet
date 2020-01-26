const express = require('express')
const bodyParser = require('body-parser')
const config = require('./src/config')
const systemsRoutes = require('./src/routes/systems')

const app = express()
app.use(bodyParser.json())

app.get('/', (req, res) => {
    res.json({ server: 'ok' })
})
app.use(systemsRoutes)

const port = config.port
const server = app.listen(port, () => {
    const address = server.address().address
    console.log(`Server opened in http://${address}:${port}`)
})
