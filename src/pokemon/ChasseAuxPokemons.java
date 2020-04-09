import java.util.Scanner;

public class ChasseAuxPokemons {

    public static void main(String[] args) {

        final Pokemon piplup = new Pokemon("Piplup", "EAU", 5, true, 51, 53, 61, 56, new Attaque[]{new AttaqueTackle(), new AttaqueMorsure(), new AttaquePistoleEau(), new AttaqueEnfer()});
        final Pokemon rowlet = new Pokemon("Rowlet", "PLANTE", 10, false, 55, 55, 50, 55, new Attaque[]{new AttaqueMorsure(), new AttaqueFeinte(), new AttaqueTornadeFeuilles()});
        final Pokemon totodile = new Pokemon("Totodile", "EAU", 8, true, 65, 64, 44, 48, new Attaque[]{new AttaqueBulle(), new AttaqueCoupDeTete()});


        final Joueur moi = new Joueur("Onete", "Cristina", 20, new Pokemon[]{piplup, rowlet, totodile, null, null});

        final Nourriture tartiflette = new Nourriture(35, "tartiflette", new String[]{"DRAGON", "FEU", "COMBAT", "NORMAL", "EAU", "ELECTRIQUE"}, 20);
        final Nourriture ratatouille = new Nourriture(10, "ratatouille", new String[]{"PLANTE", "EAU", "VOL", "FEU", "NORMAL", "ELECTRIQUE", "COMBAT"}, 50);

        final Gourmandise barreChocolatee = new Gourmandise(20, "Barre Chocolatee", new String[]{"DRAGON", "FEU", "COMBAT", "EAU", "ELECTRIQUE"}, 10, 7);
        final PotionMagique mojito = new PotionMagique("mojito", 2);

        final Nourriture[] tableauNourriture = new Nourriture[]{tartiflette, ratatouille, barreChocolatee, mojito};


        Scanner lecteur = new Scanner(System.in);
        System.out.println();

        final Pokemon p1 = piplup;
        final Pokemon p2 = rowlet;

        boolean batailleFinie = false;

        while (!batailleFinie) {
            if (p1.sEstEvanoui() || p2.sEstEvanoui()) {
                batailleFinie = true;
            }
            if (!batailleFinie) {
                p1.afficherEtatAttaques();
                System.out.println("Pour pokemon " + p1.getNom() + ", choisissez l'index de votre attaque contre le pokemon " + p2.getNom());
                int reponseIndex = lecteur.nextInt();
                while (reponseIndex < 0 || reponseIndex > p1.getAttaques().length) {
                    System.out.println("Refaites votre choix.");
                    reponseIndex = lecteur.nextInt();
                }
                p1.utiliserAttaque(reponseIndex, p2);
                if (p2.sEstEvanoui()) {
                    batailleFinie = true;
                }
            }

            if (!batailleFinie && !p2.sEstEvanoui()) {
                p2.afficherEtatAttaques();
                System.out.println("Pour pokemon" + p2.getNom() + ", choisissez l'index votre attaque contre le pokemon " + p1.getNom());
                int reponseIndex = lecteur.nextInt();
                while (reponseIndex < 0 || reponseIndex > p2.getAttaques().length) {
                    System.out.println("Refaites votre choix.");
                    reponseIndex = lecteur.nextInt();
                }
                p2.utiliserAttaque(reponseIndex, p1);
                if (p1.sEstEvanoui()) {
                    batailleFinie = true;
                }
            }
        }

        if (p1.sEstEvanoui()) {
            System.out.println("Le vainqueur est " + p2.getNom());
        } else {
            System.out.println("Le vainqueur est " + p1.getNom());
        }

        p1.rechargerAttaques();
        p2.rechargerAttaques();

        lecteur.close();
		/*
		final Scanner scanner = new Scanner(System.in);

		double alea;
		boolean generer = false;
		Nourriture[] nourritureGeneree = new Nourriture[tableauNourriture.length];
		System.out.println("Continuer ? Pour oui, appuyez sur n'importe quelle touche. Pour non, tapez stop.");
		String reponse = scanner.next();
		int reponseEntiere;
		while(!reponse.equals("stop")) {
			alea = Math.random()*100;
			for (int j = 0; j<tableauNourriture.length; j++) {
				if (alea < tableauNourriture[j].getFrequence()) {
					generer = true;
				}
				else {
					generer = false;
				}
				nourritureGeneree[j] = tableauNourriture[j].genererMemeNourriture(generer);
				if (generer) {
					System.out.println("Vous avez trouv� un.e/du/de la " + tableauNourriture[j].getNom() + ". Voulez-vous la prendre ? Ecrivez oui si c'est le cas.");
					reponse = scanner.next();
					if (reponse.equals("oui")) {
						moi.ajouterProvision(nourritureGeneree[j]);
					}
				}

			}
			System.out.println("Tapez 1,2,3,4 ou stop pour : \n 1. regarder vos pokemons; \n 2. caresser vos pokemons; "
						+ "\n 3. regarder vos provisions; \n 4. nourrir vos pokemons; \n stop : s'arreter.");
			reponse = scanner.next();
			if (reponse.equals("1")) {
				for (int i = 0; i < moi.getPokemons().length; i++) {
					if (null != moi.getPokemons()[i]) {
						System.out.println("Index " + i + " : " +moi.getPokemons()[i]);
					}
				}
			}
			else {
				if (reponse.equals("2")) {
					System.out.println("Quel pokemon voulez-vous caresser ? Donnez son index entre 0 et " + (moi.getPokemons().length-1));
					reponseEntiere = scanner.nextInt();
					if(reponseEntiere >= 0 && reponseEntiere < moi.getPokemons().length) {
						moi.caresserPokemon(moi.getPokemons()[reponseEntiere]);
					}
					else {
						System.out.println("Mauvaise valeur. Vous perdez votre tour.");
					}
				}
				else {
					if (reponse.equals("3")) {
						moi.afficherProvisions();
					}
					else {
						if (reponse.equals("4")) {
							System.out.println("Quel pokemon voulez-vous nourrir ? Donnez son index entre 0 et 4.");
							reponseEntiere = scanner.nextInt();
							if(reponseEntiere >= 0 && reponseEntiere < moi.getPokemons().length) {
								System.out.println("Quelle provision voulez-vous utiliser ? Donnez son index entre 0 et " + (moi.getProvisions().length-1));
								int deuxiemeReponse = scanner.nextInt();
								if(deuxiemeReponse >= 0 && deuxiemeReponse < moi.getProvisions().length) {
									moi.nourrirPokemon(moi.getPokemons()[reponseEntiere], moi.getProvisions()[deuxiemeReponse]);
								}
								else {
									System.out.println("Mauvaise valeur. Vous perdez votre tour.");
								}
							}
							else {
								System.out.println("Mauvaise valeur. Vous perdez votre tour.");
							}
						}
					}
				}
			}
		}

		scanner.close();
		*/

    }


}