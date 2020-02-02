const systemsModel = require('../model/systems')

module.exports = {
    getSystems: (req, res) => {
        systemsModel.find({}, (err, docs) => {
            if (err) res.status(500).send(err)
            res.json(docs)
        })
    },
    addSystem: (req, res) => {
        const newSystem = {
            star: req.body.star,
            constellation: req.body.constellation,
            distanceFromEarth: req.body.distanceFromEarth,
            imageURL: req.body.imageURL
        }
        systemsModel.create(newSystem, (err, result) => {
            if (err) res.status(500).json(err)
            res.status(201).json(result)
        })
    },
    getSystemById: (req, res) => {
        systemsModel.findById({ _id: req.params.id }, (err, docs) => {
            if (err) res.status(500).send(err)
            res.json(docs)
        })
    },
    updateSystemById: (req, res) => {
        systemsModel.updateOne({ _id: req.params.id }, req.body, { new: true }, (err, result) => {
            if (err) res.status(404).json(err)
            res.status(204).json(result)
        })
    },
    deleteSystemById: (req, res) => {
        systemsModel.findOneAndDelete({ _id: req.params.id }, (err, result) => {
            if (err) res.status(404).json(err)
            res.send(result)
        })
    }
}