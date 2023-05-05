/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

/**
 *
 * @author MSI
 */
public class ChatBot {
     public String processInput(String input) {
        if(null == input)return "Malheuresement j'ai pas une reponse à ce genre de Message , merci d'attendez nos mise à jour systeme !";
        else switch (input) {
            case "Salut":
                return "Bonjour, comment puis-je vous aider ?";
            case "Bonjour":
                return "Bonjour, comment puis-je vous aider ?";
            case "Pouvez vous m'expliquer le concept de cette plateforme":
                return "Gogym permet aux clients de participer a des evenements \ninscrit à l’Ordre des médecins, via vidéo depuis  leur \ntablette ou smartphone  a pour objectif de rendre la \nsanté plus équitable et accessible " ;
            case "Les services":
                return "-Faire un abonnement à une ou plisieurs salles de sport\n" +
                        "-Cours collectifsl\n" +
                        "-Événements communautaires \n" +
                        "-Coaching personnel \n" +
                        "-Acheter des produits\n" +
                        "-Services nutritionnels\n" +
                        "-Programmes pour enfants " ;
            case "Merci":
                return "A tout moment , je suis là pour vous aidez !";
            case "Le concept":
                return "fournir un espace dédié à la pratique d'activités physiques et de remise en forme.  ";
            default:
                return "Malheuresement j'ai pas une reponse à ce genre de Message , merci d'attendez nos mise à jour systeme !";
        }
    }
}
