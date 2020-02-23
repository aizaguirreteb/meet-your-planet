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
    },
    getPlanetById: (req, res)=> {
        planetsModel.findById({_id: req.params.id}, (err, result) =>{
            if(err) res.status(500).send(err)
            res.json(result)
        })
    },
    getPlanetsBySystemId: (req, res)=> {
        obtainedId = req.params.id
        planetsModel.find({systemId: `${obtainedId}`}, (err, result) =>{
            if(err) res.status(500).send(err)
            res.json(result)
        })
    },
    updatePlanetById:  (req, res) => {
        planetsModel.updateOne({ _id: req.params.id }, req.body, { new: true }, (err, result) => {
            if (err) res.status(404).json(err)
            res.status(204).json(result)
        })
    },
    deletePlanetById: (req, res) => {
        planetsModel.findOneAndDelete({ _id: req.params.id }, (err, result) => {
            if (err) res.status(404).json(err)
            res.send(result)
        })
    },

    findAndDeletePlanetsBySystemId: (systemId) => {
        planetsModel.find({systemId: systemId}, (err, result) => {
            if(err) res.status(500).send(err)
            else {
                for(let planet of result){
                    planetsModel.findOneAndDelete({_id: planet._id}, (err, result) => {
                        if (err) res.status(404).json(err)
                        //res.send(result)
                    })
                }
            }
        })     

    }
}