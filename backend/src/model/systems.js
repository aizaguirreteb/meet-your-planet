const mongoose = require('mongoose')
const Schema = mongoose.Schema

const systemsSchema = new Schema({
    star: {
        type: String,
        minlength: 1,
        maxlength: 50,
        required: true,
        trim: true
    },
    constellation: {
        type: String,
        minlength: 1,
        maxlength: 50,
        required: true,
        trim: true
    },
    distanceFromEarth: {
        type: Number,
        default: 0,
        min: 0
    },
    imageURL: {
        type: String,
        minlength: 0,
        required: false
    }
}, {
    versionKey : false
})

module.exports=mongoose.model('System', systemsSchema)