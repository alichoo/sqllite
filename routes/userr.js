const express = require('express');
const router = express.Router();
const userController = require('../controllers/userc');

// Routes pour les utilisateurs
router.post('/', userController.addUser);
router.get('/', userController.getAllUsers);
router.delete('/:id', userController.deleteUser);

module.exports = router;
