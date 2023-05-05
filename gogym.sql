-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Hôte : 127.0.0.1
-- Généré le : ven. 05 mai 2023 à 18:09
-- Version du serveur : 10.4.24-MariaDB
-- Version de PHP : 8.1.6

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données : `gogym`
--

-- --------------------------------------------------------

--
-- Structure de la table `abonnement`
--

CREATE TABLE `abonnement` (
  `id` int(11) NOT NULL,
  `nom_a` varchar(255) NOT NULL,
  `type_a` varchar(255) NOT NULL,
  `prix_a` int(11) NOT NULL,
  `description_a` varchar(255) NOT NULL,
  `debut_a` date NOT NULL,
  `fin_a` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `abonnement`
--

INSERT INTO `abonnement` (`id`, `nom_a`, `type_a`, `prix_a`, `description_a`, `debut_a`, `fin_a`) VALUES
(57, 'Yellow', 'annuel', 1200, 'eeee', '2023-04-01', '2024-04-01'),
(59, 'Yellow', 'mensuel', 150, 'aaaa', '2023-04-01', '2023-05-01'),
(60, 'Blue', 'annuel', 1300, 'adfghdf', '2023-04-22', '2024-04-22'),
(61, 'Blue', 'mensuel', 170, 'zdsg', '2023-04-15', '2023-05-15'),
(62, 'Kids', 'mensuel', 200, 'for kids only', '2023-04-29', '2023-05-29'),
(76, 'Kidzyovbc', 'mensuel', 200, 'for kids only', '2023-04-29', '2023-05-29');

-- --------------------------------------------------------

--
-- Structure de la table `abonnementsalle`
--

CREATE TABLE `abonnementsalle` (
  `id` int(11) NOT NULL,
  `idAbonnement` int(11) NOT NULL,
  `idSalle` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `abonnementsalle`
--

INSERT INTO `abonnementsalle` (`id`, `idAbonnement`, `idSalle`) VALUES
(9, 49, 39),
(11, 50, 40),
(12, 49, 38),
(14, 49, 23),
(15, 49, 13),
(16, 50, 13),
(17, 51, 13),
(21, 50, 41),
(22, 51, 41),
(23, 49, 41),
(24, 51, 42),
(25, 49, 43),
(26, 51, 43),
(27, 49, 44),
(28, 52, 44),
(29, 50, 44),
(30, 54, 44),
(31, 55, 44),
(32, 49, 45),
(33, 54, 45),
(34, 50, 45),
(35, 49, 46),
(36, 56, 45),
(37, 57, 48),
(39, 57, 50),
(40, 57, 51),
(41, 57, 52),
(42, 57, 53),
(43, 57, 54),
(44, 57, 55),
(45, 57, 56),
(46, 57, 57),
(48, 58, 48),
(49, 58, 50),
(50, 58, 51),
(51, 58, 52),
(52, 59, 48),
(54, 59, 50),
(55, 59, 51),
(56, 59, 52),
(57, 59, 53),
(58, 59, 54),
(59, 59, 56),
(60, 59, 57),
(61, 60, 48),
(62, 60, 51),
(63, 60, 53),
(64, 60, 56),
(65, 60, 57),
(66, 60, 58),
(67, 61, 48),
(68, 61, 50),
(69, 61, 51),
(70, 61, 52),
(71, 61, 53),
(72, 61, 54),
(73, 61, 55),
(74, 61, 56),
(75, 61, 57),
(76, 61, 58),
(77, 62, 48),
(78, 62, 51),
(79, 62, 53),
(80, 57, 49),
(81, 58, 49),
(82, 59, 49),
(83, 60, 49),
(84, 61, 49),
(85, 62, 49),
(86, 57, 59),
(87, 58, 59),
(88, 59, 59),
(89, 60, 60),
(90, 61, 60),
(91, 62, 61),
(92, 62, 62),
(93, 63, 48),
(94, 63, 49),
(95, 63, 50),
(96, 63, 51),
(97, 63, 52),
(98, 63, 53),
(99, 63, 54),
(100, 63, 56),
(101, 63, 57),
(102, 64, 48),
(103, 64, 49),
(104, 57, 63),
(105, 58, 63),
(106, 59, 63),
(107, 57, 64),
(108, 58, 64),
(109, 59, 64),
(110, 65, 49),
(111, 65, 50),
(112, 65, 48),
(113, 65, 52),
(114, 57, 65),
(115, 59, 65),
(116, 66, 49),
(117, 67, 52),
(118, 68, 50),
(119, 69, 50),
(120, 70, 50),
(121, 71, 50),
(122, 72, 50),
(123, 73, 50),
(124, 60, 66),
(125, 61, 66),
(126, 57, 67),
(127, 59, 67),
(128, 67, 48),
(129, 67, 48),
(130, 62, 50),
(131, 74, 48),
(136, 75, 48),
(137, 75, 49),
(138, 75, 50),
(139, 75, 51),
(140, 75, 53),
(143, 76, 50),
(145, 76, 53),
(146, 77, 48),
(147, 77, 51),
(148, 57, 68),
(149, 59, 68);

-- --------------------------------------------------------

--
-- Structure de la table `categorie`
--

CREATE TABLE `categorie` (
  `id` int(11) NOT NULL,
  `nom` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Déchargement des données de la table `categorie`
--

INSERT INTO `categorie` (`id`, `nom`) VALUES
(1, 'fffff');

-- --------------------------------------------------------

--
-- Structure de la table `categorie_evenement`
--

CREATE TABLE `categorie_evenement` (
  `id` int(11) NOT NULL,
  `nom_cat_e` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Déchargement des données de la table `categorie_evenement`
--

INSERT INTO `categorie_evenement` (`id`, `nom_cat_e`) VALUES
(3, 'ibrahim'),
(4, 'categorie3');

-- --------------------------------------------------------

--
-- Structure de la table `commande`
--

CREATE TABLE `commande` (
  `id_com` int(11) NOT NULL,
  `etat_com` tinyint(1) NOT NULL,
  `date_com` date NOT NULL,
  `prixtotal` double NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- --------------------------------------------------------

--
-- Structure de la table `cours`
--

CREATE TABLE `cours` (
  `id` int(11) NOT NULL,
  `nom` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `duree` int(11) NOT NULL,
  `intensite` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `bienfaits` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `image` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Déchargement des données de la table `cours`
--

INSERT INTO `cours` (`id`, `nom`, `duree`, `intensite`, `bienfaits`, `image`) VALUES
(2, 'box', 20, 'haute', 'amelioration', 'C:Usersdon7aDocumentsGitHubgogym_javafxsrcimages603.jpg'),
(4, 'RPM', 30, 'huate', 'amelioration', ''),
(6, 'RPM', 20, '', '', ''),
(7, 'Box', 20, 'haute', 'amelioration', 'C:Usersdon7aDocumentsGitHubgogym_javafxsrcuploads	éléchargement (3).jpg');

-- --------------------------------------------------------

--
-- Structure de la table `evenement`
--

CREATE TABLE `evenement` (
  `id` int(11) NOT NULL,
  `categorie_evenement_id` int(11) DEFAULT NULL,
  `nom_e` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `description_e` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `date_e` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `lieu_e` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `nbr_participants` int(11) NOT NULL,
  `image` varchar(1000) COLLATE utf8mb4_unicode_ci NOT NULL,
  `etat` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Déchargement des données de la table `evenement`
--

INSERT INTO `evenement` (`id`, `categorie_evenement_id`, `nom_e`, `description_e`, `date_e`, `lieu_e`, `nbr_participants`, `image`, `etat`) VALUES
(3, 3, 'ibrahim', 'ibrahim', '23-04-2022', 'ibrahim', 154, 'téléchargement (3).jpg', ''),
(4, 3, 'hhhhhhhhhhhhh', 'kkkkkkkkkkkk', '28-04-2020', 'ghazela', 150, 'téléchargement.jpg', ''),
(5, 3, 'azerty', 'azerty', '23-04-2020', 'azerty', 0, 'téléchargement.jpg', ''),
(6, 4, 'jump', 'jump', '2023-04-02', 'jump', 125, 'téléchargement.jpg', ''),
(7, 4, 'integr', 'integr', '2023-02-04', 'integr', 121, 'téléchargement.jpg', ''),
(8, 4, 'International Sports', 'Here is a list of the major international sports events in which teams from different countries battle it out, both for individual sports and multi-sport events. In these events countries of the world are brought together in competition.', '2023-03-07', 'esprit', 149, 'téléchargement.jpg', ''),
(9, 4, 'val', 'val', '2023-04-06', 'val', 123, 'téléchargement.jpg', '');

-- --------------------------------------------------------

--
-- Structure de la table `ligne_commande`
--

CREATE TABLE `ligne_commande` (
  `id_l_p` int(11) NOT NULL,
  `qte_dem` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- --------------------------------------------------------

--
-- Structure de la table `livraison`
--

CREATE TABLE `livraison` (
  `id_livraison` int(11) NOT NULL,
  `description_livraison` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `etat_livraison` tinyint(1) NOT NULL,
  `adresse_livraison` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- --------------------------------------------------------

--
-- Structure de la table `livreur`
--

CREATE TABLE `livreur` (
  `id_livreur` int(11) NOT NULL,
  `nom_liv` varchar(30) COLLATE utf8mb4_unicode_ci NOT NULL,
  `prenom_liv` varchar(30) COLLATE utf8mb4_unicode_ci NOT NULL,
  `num_tel_liv` int(11) NOT NULL,
  `disponibilite_liv` tinyint(1) NOT NULL,
  `region` varchar(50) COLLATE utf8mb4_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- --------------------------------------------------------

--
-- Structure de la table `messenger_messages`
--

CREATE TABLE `messenger_messages` (
  `id` bigint(20) NOT NULL,
  `body` longtext COLLATE utf8mb4_unicode_ci NOT NULL,
  `headers` longtext COLLATE utf8mb4_unicode_ci NOT NULL,
  `queue_name` varchar(190) COLLATE utf8mb4_unicode_ci NOT NULL,
  `created_at` datetime NOT NULL,
  `available_at` datetime NOT NULL,
  `delivered_at` datetime DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- --------------------------------------------------------

--
-- Structure de la table `notification`
--

CREATE TABLE `notification` (
  `id` int(11) NOT NULL,
  `user_id` int(11) DEFAULT NULL,
  `message` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `is_read` tinyint(1) NOT NULL,
  `created_at` datetime NOT NULL COMMENT '(DC2Type:datetime_immutable)'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- --------------------------------------------------------

--
-- Structure de la table `panier`
--

CREATE TABLE `panier` (
  `id_l_p` int(11) NOT NULL,
  `quantite` int(11) NOT NULL,
  `total` double NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- --------------------------------------------------------

--
-- Structure de la table `participate`
--

CREATE TABLE `participate` (
  `id` int(11) NOT NULL,
  `id_user_id` int(11) DEFAULT NULL,
  `id_event_id` int(11) DEFAULT NULL,
  `verification_code` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Déchargement des données de la table `participate`
--

INSERT INTO `participate` (`id`, `id_user_id`, `id_event_id`, `verification_code`) VALUES
(13, 3, 3, 2156),
(14, 3, 4, 1198),
(15, 3, 7, 7408),
(16, 3, 8, 1133);

-- --------------------------------------------------------

--
-- Structure de la table `participation`
--

CREATE TABLE `participation` (
  `id_part` int(11) NOT NULL,
  `verif_code` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- --------------------------------------------------------

--
-- Structure de la table `produit`
--

CREATE TABLE `produit` (
  `id` int(11) NOT NULL,
  `categorie_id` int(11) DEFAULT NULL,
  `nom_prod` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `description` longtext COLLATE utf8mb4_unicode_ci NOT NULL,
  `image` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `prix` double NOT NULL,
  `nbr_prods` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Déchargement des données de la table `produit`
--

INSERT INTO `produit` (`id`, `categorie_id`, `nom_prod`, `description`, `image`, `prix`, `nbr_prods`) VALUES
(1, 1, 'test', 'ffff', 'hh.jpg', 33, 33),
(2, 1, 'fffff', 'ffff', 'hh.jpg', 33, 33),
(3, 1, 'fffff', 'ffff', 'hh.jpg', 33, 33),
(4, 1, 'adem', 'adem', 'hh.jpg', 159, 159),
(5, 1, 'dfff', 'ffff', 'hh.jpg', 24, 23);

-- --------------------------------------------------------

--
-- Structure de la table `produitrating`
--

CREATE TABLE `produitrating` (
  `id` int(11) NOT NULL,
  `id_prod` int(11) NOT NULL,
  `id_user` int(11) NOT NULL,
  `rate` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `produitrating`
--

INSERT INTO `produitrating` (`id`, `id_prod`, `id_user`, `rate`) VALUES
(1, 3, 2, 4),
(2, 2, 2, 0),
(3, 4, 2, 5),
(9, 1, 3, 3),
(10, 2, 3, 3),
(11, 4, 3, 4);

-- --------------------------------------------------------

--
-- Structure de la table `reservation`
--

CREATE TABLE `reservation` (
  `id` int(11) NOT NULL,
  `cours_id` int(11) DEFAULT NULL,
  `date` date NOT NULL,
  `type` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Déchargement des données de la table `reservation`
--

INSERT INTO `reservation` (`id`, `cours_id`, `date`, `type`) VALUES
(5, 6, '2023-06-13', 'COURS'),
(6, 4, '2023-07-13', 'COURS'),
(7, 4, '2023-06-05', 'COURS');

-- --------------------------------------------------------

--
-- Structure de la table `salle`
--

CREATE TABLE `salle` (
  `id` int(11) NOT NULL,
  `nom_s` varchar(255) NOT NULL,
  `email_s` varchar(255) NOT NULL,
  `tel_s` int(11) NOT NULL,
  `adresse_s` varchar(255) NOT NULL,
  `ville_s` varchar(255) NOT NULL,
  `image_s` varchar(255) NOT NULL,
  `perimetre_s` float NOT NULL,
  `like_s` int(11) NOT NULL,
  `longitude_s` double NOT NULL,
  `latitude_s` double NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `salle`
--

INSERT INTO `salle` (`id`, `nom_s`, `email_s`, `tel_s`, `adresse_s`, `ville_s`, `image_s`, `perimetre_s`, `like_s`, `longitude_s`, `latitude_s`) VALUES
(48, 'GG SFAX', 'info@gogym.tn', 74100200, 'Sfax', 'SFAX', 'image85677.jpg', 123, 7, 10.710983276367186, 34.73709847578162),
(49, 'Boumhel', 'info@gogym.tn', 70000000, 'Boumhel', 'Tunis', 'no image.jpg', 234, 4, 10.694503784179686, 34.8047829195724),
(50, 'Soukra', 'info@gogym.tn', 71234567, 'Soukra Silver mall', 'Tunis', 'image12057.jpg', 176, 2, 10.83045959472656, 34.81267582141754),
(51, 'GG Marsa', 'info@gogym.tn', 71098765, 'Zéphyr', 'Tunis', 'image84936.jpg', 235, 2, 10.66566467285156, 34.71565349880315),
(52, 'Azerty', 'info@gogym.tn', 12345678, 'azeertty', 'Tunis', 'image76254.jpg', 567, 3, 10.837326049804688, 34.839731459420676),
(53, 'GG Bardo', 'info@gogym.tn', 12345678, 'Bardo', 'Tunis', 'image89049.jpg', 765, 1, 10.60523986816406, 34.80140001594981),
(54, 'GG Ennaser', 'info@gogym.tn', 71899000, 'Enaaser1', 'Ariana', 'image43049.jpg', 876, 1, 10.645065307617186, 34.823950084069025),
(55, 'Ariana', 'info@gogym.tn', 71234567, 'Ariana', 'Ariana', 'image43601.jpg', 243, 2, 10.720596313476562, 34.73822700466421),
(56, 'GG Lac', 'info@gogym.tn', 70678534, 'Lac 1', 'Tunis', 'image65644.jpg', 765, 1, 10.84144592285156, 34.84536693184103),
(57, 'GG Centre Ville', 'info@gogym.tn', 70345213, 'Centre ville Tunis', 'Tunis', 'image49858.jpg', 122, 1, 10.59425354003906, 34.64676624651912),
(64, 'hana', 'hana@s.cim', 12312343, 'dswxc', 'dcsx', 'image70769.jpg', 123, 0, 10.065536499023438, 36.86204269508728),
(68, 'esmha', 'esmo@gogym.tn', 12345678, 'fdsg', 'saafa', 'image47967.jpg', 124, 0, 10.04219055175781, 36.857647585644074);

-- --------------------------------------------------------

--
-- Structure de la table `user`
--

CREATE TABLE `user` (
  `id` int(11) NOT NULL,
  `username` varchar(180) COLLATE utf8mb4_unicode_ci NOT NULL,
  `roles` longtext COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '(DC2Type:json)',
  `password` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `nom` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `prenom` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `email` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `reset_token` varchar(100) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `status` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Déchargement des données de la table `user`
--

INSERT INTO `user` (`id`, `username`, `roles`, `password`, `nom`, `prenom`, `email`, `reset_token`, `status`) VALUES
(1, 'yess', '[\"ROLE_ADMIN\"]', '$2a$10$.tpyuFMHc9Rr2k5zfQdN8Ovch7qm8rvNnQ1A.mmqbi/osp2DFI7By', 'Yessine', 'Jarray', 'yessine.jarray@esprit.tn', NULL, 'enabled'),
(3, 'yes', '[\"ROLE_USER\"]', '$2a$10$N4eINlgU5Hl1tF8aJRtK7eGaSfOhZBtqcQA/BBSu.EPLOZSxdOwBi', 'yessinee', 'jarray', 'don7allouzi@gmail.com', NULL, 'enabled'),
(4, 'ibra', '[\"ROLE_USER\"]', '$2a$10$E68ylkbgN21cFq32fY9deOyM6ep6j5gEDUiRJ2pDlciQp1s8xbkj.', 'ibrahim', 'soussi', 'ibra@gmail.com', NULL, 'enabled'),
(5, 'adouma', '[\"ROLE_USER\"]', '$2a$10$.tpyuFMHc9Rr2k5zfQdN8Ovch7qm8rvNnQ1A.mmqbi/osp2DFI7By', 'adem', 'mzid', 'yessine.jarray@esprit.tn', NULL, 'enabled'),
(6, 'layenn', '[\"ROLE_USER\"]', '$2a$10$IFF3Mhdu4Qnnu516yjZTuuhyGT/50J07.FtR984SUH66KdyLLcGni', 'layen', 'jarray', 'ines.mahjoubi@esprit.tn', NULL, 'enabled');

--
-- Index pour les tables déchargées
--

--
-- Index pour la table `abonnement`
--
ALTER TABLE `abonnement`
  ADD PRIMARY KEY (`id`);

--
-- Index pour la table `abonnementsalle`
--
ALTER TABLE `abonnementsalle`
  ADD PRIMARY KEY (`id`);

--
-- Index pour la table `categorie`
--
ALTER TABLE `categorie`
  ADD PRIMARY KEY (`id`);

--
-- Index pour la table `categorie_evenement`
--
ALTER TABLE `categorie_evenement`
  ADD PRIMARY KEY (`id`);

--
-- Index pour la table `commande`
--
ALTER TABLE `commande`
  ADD PRIMARY KEY (`id_com`);

--
-- Index pour la table `cours`
--
ALTER TABLE `cours`
  ADD PRIMARY KEY (`id`);

--
-- Index pour la table `evenement`
--
ALTER TABLE `evenement`
  ADD PRIMARY KEY (`id`),
  ADD KEY `IDX_B26681E76D36991` (`categorie_evenement_id`);

--
-- Index pour la table `ligne_commande`
--
ALTER TABLE `ligne_commande`
  ADD PRIMARY KEY (`id_l_p`);

--
-- Index pour la table `livraison`
--
ALTER TABLE `livraison`
  ADD PRIMARY KEY (`id_livraison`);

--
-- Index pour la table `livreur`
--
ALTER TABLE `livreur`
  ADD PRIMARY KEY (`id_livreur`);

--
-- Index pour la table `messenger_messages`
--
ALTER TABLE `messenger_messages`
  ADD PRIMARY KEY (`id`),
  ADD KEY `IDX_75EA56E0FB7336F0` (`queue_name`),
  ADD KEY `IDX_75EA56E0E3BD61CE` (`available_at`),
  ADD KEY `IDX_75EA56E016BA31DB` (`delivered_at`);

--
-- Index pour la table `notification`
--
ALTER TABLE `notification`
  ADD PRIMARY KEY (`id`),
  ADD KEY `IDX_BF5476CAA76ED395` (`user_id`);

--
-- Index pour la table `panier`
--
ALTER TABLE `panier`
  ADD PRIMARY KEY (`id_l_p`);

--
-- Index pour la table `participate`
--
ALTER TABLE `participate`
  ADD PRIMARY KEY (`id`),
  ADD KEY `IDX_D02B13879F37AE5` (`id_user_id`),
  ADD KEY `IDX_D02B138212C041E` (`id_event_id`);

--
-- Index pour la table `participation`
--
ALTER TABLE `participation`
  ADD PRIMARY KEY (`id_part`);

--
-- Index pour la table `produit`
--
ALTER TABLE `produit`
  ADD PRIMARY KEY (`id`),
  ADD KEY `IDX_29A5EC27BCF5E72D` (`categorie_id`);

--
-- Index pour la table `produitrating`
--
ALTER TABLE `produitrating`
  ADD PRIMARY KEY (`id`),
  ADD KEY `id_prod` (`id_prod`);

--
-- Index pour la table `reservation`
--
ALTER TABLE `reservation`
  ADD PRIMARY KEY (`id`),
  ADD KEY `IDX_42C849557ECF78B0` (`cours_id`);

--
-- Index pour la table `salle`
--
ALTER TABLE `salle`
  ADD PRIMARY KEY (`id`);

--
-- Index pour la table `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `UNIQ_8D93D649F85E0677` (`username`);

--
-- AUTO_INCREMENT pour les tables déchargées
--

--
-- AUTO_INCREMENT pour la table `abonnement`
--
ALTER TABLE `abonnement`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=78;

--
-- AUTO_INCREMENT pour la table `abonnementsalle`
--
ALTER TABLE `abonnementsalle`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=150;

--
-- AUTO_INCREMENT pour la table `categorie`
--
ALTER TABLE `categorie`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT pour la table `categorie_evenement`
--
ALTER TABLE `categorie_evenement`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT pour la table `commande`
--
ALTER TABLE `commande`
  MODIFY `id_com` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT pour la table `cours`
--
ALTER TABLE `cours`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- AUTO_INCREMENT pour la table `evenement`
--
ALTER TABLE `evenement`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;

--
-- AUTO_INCREMENT pour la table `ligne_commande`
--
ALTER TABLE `ligne_commande`
  MODIFY `id_l_p` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT pour la table `livraison`
--
ALTER TABLE `livraison`
  MODIFY `id_livraison` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT pour la table `livreur`
--
ALTER TABLE `livreur`
  MODIFY `id_livreur` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT pour la table `messenger_messages`
--
ALTER TABLE `messenger_messages`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT pour la table `notification`
--
ALTER TABLE `notification`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT pour la table `panier`
--
ALTER TABLE `panier`
  MODIFY `id_l_p` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT pour la table `participate`
--
ALTER TABLE `participate`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=17;

--
-- AUTO_INCREMENT pour la table `participation`
--
ALTER TABLE `participation`
  MODIFY `id_part` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT pour la table `produit`
--
ALTER TABLE `produit`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT pour la table `produitrating`
--
ALTER TABLE `produitrating`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=12;

--
-- AUTO_INCREMENT pour la table `reservation`
--
ALTER TABLE `reservation`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- AUTO_INCREMENT pour la table `salle`
--
ALTER TABLE `salle`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=69;

--
-- AUTO_INCREMENT pour la table `user`
--
ALTER TABLE `user`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- Contraintes pour les tables déchargées
--

--
-- Contraintes pour la table `evenement`
--
ALTER TABLE `evenement`
  ADD CONSTRAINT `FK_B26681E76D36991` FOREIGN KEY (`categorie_evenement_id`) REFERENCES `categorie_evenement` (`id`);

--
-- Contraintes pour la table `notification`
--
ALTER TABLE `notification`
  ADD CONSTRAINT `FK_BF5476CAA76ED395` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`);

--
-- Contraintes pour la table `participate`
--
ALTER TABLE `participate`
  ADD CONSTRAINT `FK_D02B138212C041E` FOREIGN KEY (`id_event_id`) REFERENCES `evenement` (`id`),
  ADD CONSTRAINT `FK_D02B13879F37AE5` FOREIGN KEY (`id_user_id`) REFERENCES `user` (`id`);

--
-- Contraintes pour la table `produit`
--
ALTER TABLE `produit`
  ADD CONSTRAINT `FK_29A5EC27BCF5E72D` FOREIGN KEY (`categorie_id`) REFERENCES `categorie` (`id`);

--
-- Contraintes pour la table `reservation`
--
ALTER TABLE `reservation`
  ADD CONSTRAINT `FK_42C849557ECF78B0` FOREIGN KEY (`cours_id`) REFERENCES `cours` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
