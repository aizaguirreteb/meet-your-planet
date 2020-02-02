const express = require('express')
const router = express.Router()
const systemsSchema = require('../controller/systems')

router.route('/systems')
      .get(systemsSchema.getSystems)
      .post(systemsSchema.addSystem)

router.route('/systems/:id')
      .get(systemsSchema.getSystemById)
      .put(systemsSchema.updateSystemById)
      .delete(systemsSchema.deleteSystemById)

module.exports = router