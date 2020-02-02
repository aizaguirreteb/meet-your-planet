const mongoose = require('mongoose')
const Schema = mongoose.Schema

const planetsSchema = new Schema({
    name: {
        type: String,
        minlength: 1,
        maxlength: 50,
        required: true,
        trim: true
    },
    category: {
        type: String,
        minlength: 1,
        maxlength: 50,
        required: true,
        trim: true
    },
    orbitalPeriod: {
        type: Number,
        min: 0,
        default: 0,
        required: true
    },
    mass: {
        type: String,
        minlength: 1,
        maxlength: 50,
        required: false,
        trim: true
    },
    equilibriumTemperature: {
        type: Number,
        required: false
    },
    numberSatellites: {
        type: Number,
        default: 0,
        min: 0
    },
    gravity: {
        type: String,
        default: 'Unknown',
        required: true
    },
    surface: {
        type: String,
        enum: ['Gas', 'Liquid', 'Solid', 'Mixed', 'Unknown'],
        required: true
    },
    planetImage: {
        type: String,
        minlength: 0,
        required: false
    },
    systemId: {
        type: String,
        required: true,
        trim: true
    }

}, {
    versionKey : false
})

module.exports=mongoose.model('Planet', planetsSchema)