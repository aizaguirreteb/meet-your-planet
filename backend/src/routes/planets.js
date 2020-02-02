const express = require('express')
const router = express.Router()
const planetsSchema = require('../controller/planets')

router.route('/planets')
      .get(planetsSchema.getPlanets)
      .post(planetsSchema.addPlanet)

module.exports = router