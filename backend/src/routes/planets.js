const express = require('express')
const router = express.Router()
const planetsSchema = require('../controller/planets')

router.route('/planets')
      .get(planetsSchema.getPlanets)
      .post(planetsSchema.addPlanet)

router.route('/planets/:id')
      .get(planetsSchema.getPlanetById)
      .put(planetsSchema.updatePlanetById)
      .delete(planetsSchema.deletePlanetById)

module.exports = router