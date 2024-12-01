const mongoose = require('mongoose');

// Définir le schéma utilisateur
const userSchema = new mongoose.Schema({
    name: { type: String, required: true },
    email: { type: String, required: true, unique: true },
});

// Exporter le modèle
module.exports = mongoose.model('User', userSchema);
