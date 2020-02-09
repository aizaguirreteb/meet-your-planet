const express = require('express')
const router  = express.Router()
const userController = require ('../controller/users')


router.post('/login/new', userController.create)
router.post('/login', userController.validate)

module.exports=router