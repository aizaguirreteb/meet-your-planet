const systemsModel = require('../model/systems')

module.exports = {
    getSystems : (req, res) => {
        systemsModel.find({}, (err, docs) => {
            if (err) res.status(500).send(err)
            res.json(docs)
        })
    }
}