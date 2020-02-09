const express = require('express')
const router  = express.Router()
const userController = require ('../controller/users')


router.post('/register', userController.create)
router.post('/authentication', userController.validate)

module.exports=router