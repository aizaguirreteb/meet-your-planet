const express = require('express')
const router = express.Router()
const systemsSchema = require('../controller/systems')

router.get('/systems', systemsSchema.getSystems)

module.exports = router