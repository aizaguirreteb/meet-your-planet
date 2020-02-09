const user = require('../model/users')
const bcrypt = require('bcrypt')
const jwt = require('jsonwebtoken')

module.exports = {

    create: (req, res, next) => {
        const newUser = {
            name: req.body.name,
            email: req.body.email,
            password: req.body.password
        }

        user.create(newUser, (err, result) =>{
            if(err) res.status(500).json({error: 'Server Failure'})
            res.status(201).json(result)
        })
    },

    validate: (req, res, next) => {
        user.findOne({email: req.body.email}, (err, userInfo) => {
            if(err) res.status(500).json({error: 'Server Failure'})
            if(userInfo) {
                if(bcrypt.compareSync(req.body.password, userInfo.password)){
                    const token = jwt.sign({id: userInfo._id},
                        req.app.get('secretKey'), {expiresIn: '1h'})
                    res.json({
                        status: 'succes', 
                        message: 'User was found',
                        data: {
                            user: userInfo,
                            token: token
                        }
                    })
                } else {
                    res.status(404).json({error: 'Incorrect password'})
                }
            } else {
                res.status(404).json({error: 'Email does not exist'})
            }
        })
    }
}