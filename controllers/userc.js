const User = require('../models/user');

// Ajouter un utilisateur
exports.addUser = async (req, res) => {
    try {
        const user = new User(req.body);
        await user.save();
        res.status(201).json({ message: 'Utilisateur ajouté', user });
    } catch (err) {
        res.status(400).json({ error: 'Erreur lors de l’ajout', details: err });
    }
};

// Récupérer tous les utilisateurs
exports.getAllUsers = async (req, res) => {
    try {
        const users = await User.find();
        res.status(200).json(users);
    } catch (err) {
        res.status(500).json({ error: 'Erreur lors de la récupération', details: err });
    }
};

// Supprimer un utilisateur par ID
exports.deleteUser = async (req, res) => {
    try {
        await User.findByIdAndDelete(req.params.id);
        res.status(200).json({ message: 'Utilisateur supprimé' });
    } catch (err) {
        res.status(500).json({ error: 'Erreur lors de la suppression', details: err });
    }
};
