const express = require('express');
const mongoose = require('mongoose');
const bodyParser = require('body-parser');
const userRoutes = require('./routes/userr');
const app = express();

// Middleware pour analyser les requêtes JSON
app.use(bodyParser.json());

// Connexion à MongoDB
mongoose.connect('mongodb://127.0.0.1:27017/expressmongodb', {
    useNewUrlParser: true,
    useUnifiedTopology: true
}).then(() => console.log('Connecté à MongoDB'))
  .catch(err => console.error('Erreur de connexion à MongoDB:', err));
/*
// Définir un schéma pour MongoDB
const userSchema = new mongoose.Schema({
    name: { type: String, required: true },
    email: { type: String, required: true, unique: true },
});

// Créer un modèle basé sur le schéma
const User = mongoose.model('User', userSchema);

// Route pour ajouter un utilisateur
app.post('/users', async (req, res) => {
    try {
        const user = new User(req.body);
        await user.save();
        res.status(201).send({ message: 'Utilisateur ajouté', user });
    } catch (err) {
        res.status(400).send({ error: 'Erreur lors de l’ajout', details: err });
    }
});

// Route pour lister tous les utilisateurs
app.get('/users', async (req, res) => {
    try {
        const users = await User.find();
        res.status(200).send(users);
    } catch (err) {
        res.status(500).send({ error: 'Erreur lors de la récupération', details: err });
    }
});

// Route pour supprimer un utilisateur par ID
app.delete('/users/:id', async (req, res) => {
    try {
        await User.findByIdAndDelete(req.params.id);
        res.status(200).send({ message: 'Utilisateur supprimé' });
    } catch (err) {
        res.status(500).send({ error: 'Erreur lors de la suppression', details: err });
    }
});
*/
// Utiliser les routes
app.use('/users', userRoutes);
// Lancer le serveur
const PORT = 3000;
app.listen(PORT, () => {
    console.log(`Serveur en cours d'exécution sur http://localhost:${PORT}`);
});
