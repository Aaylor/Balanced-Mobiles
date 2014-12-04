package algorithms;

import data.*;
import helpers.ExtMath;

import java.util.ArrayList;

public class OrderedGreedyAlgorithm extends AbstractAlgorithms {

    /**
     * The Weight list.
     */
    private Integer[] W;

    /**
     * Create the algorithm with the given entry.
     * @param entry weight list
     */
    public OrderedGreedyAlgorithm(ArrayList<Integer> entry) {
        W = entry.toArray(new Integer[0]);
    }

    @Override
    public Tree mainFunction() {
        return new Tree(constructTree(0, W.length - 1));
    }

    /**
     * Construit l'arbre en fonction de la liste des poids se trouvant
     * entre le début et la fin donnée.
     * @param beg beginning
     * @param end end
     * @return le nouveau noeud crée
     */
    private Node constructTree(int beg, int end) {
        if (beg >= end) {
            return new Leaf(W[beg]);
        } else if (beg + 1 == end) {
            return new Node(new Leaf(W[beg]), new Leaf(W[end]));
        } else {
            double step = ExtMath.half(ExtMath.sum(W, beg, end));
            int cpt = beg;

            while (true) {
                step -= W[cpt];
                if (step > 0) ++cpt;
                else break;
            }

            /*
                Par défaut, l'algorithme décale à droite.
                C'est à dire que l'élément qui arrête la boucle ci-dessus sera
                position dans le sous arbre droite.

                Sauf dans les deux cas précédent:
                    Si la valeur de step est plus petite que la précédente
                    Ou si step est égal à 0
                Alors on décale à gauche.
             */
            if (Math.abs(step) < (step + W[cpt]) || step == 0) {
                ++cpt;
            }

            return new Node(constructTree(beg, cpt - 1), constructTree(cpt, end));
        }
    }
}
