const planetsModel = require('../model/planets')

module.exports = {
    getPlanets: (req, res) => {
        planetsModel.find({}, (err,docs)=>{
            if(err) res.status(500).send(err)
            res.json(docs)
        })
    },
    addPlanet: (req, res) => {
        const newPlanet = {
            name: req.body.name,
            category: req.body.category,
            orbitalPeriod: req.body.orbitalPeriod,
            mass: req.body.mass,
            equilibriumTemperature: req.body.equilibriumTemperature,
            numberStaellites: req.body.numberStaellites,
            gravity: req.body.gravity,
            surface: req.body.surface,
            planetImage: req.body.planetImage,
            systemId: req.body.systemId
        }
        planetsModel.create(newPlanet, (err, result)=>{
            if(err) res.status(500).json(err)
            res.status(201).json(result)
        })
    }
}