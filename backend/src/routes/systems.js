const express = require('express')
const router = express.Router();

router.get('/systems', (req, res) => {
    res.status(200).json([{systems: 'to be implemented....'}])
})
module.exports = router