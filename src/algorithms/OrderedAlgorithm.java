package algorithms;

import data.*;
import helpers.ExtMath;

import java.util.ArrayList;

public class OrderedAlgorithm extends AbstractAlgorithms {

    /**
     * The Weight list.
     */
    private final Integer[] W;

    /**
     * Create the algorithm with the given entry.
     * @param entry weight list
     */
    public OrderedAlgorithm(ArrayList<Integer> entry) {
        W = entry.toArray(new Integer[0]);
    }

    @Override
    public Tree mainFunction() {
        int sum = ExtMath.sum(W, 0, W.length - 1);
        return new Tree(constructTree(0, W.length - 1, sum));
    }

    /**
     * Construit l'arbre en fonction de la liste des poids se trouvant
     * entre le début et la fin donnée.
     * @param beg beginning
     * @param end end
     * @return le nouveau noeud crée
     */
    private Node constructTree(int beg, int end, int sum) {
        if (beg >= end) {
            return new Leaf(W[beg]);
        } else if (beg + 1 == end) {
            return new Node(new Leaf(W[beg]), new Leaf(W[end]));
        } else {
            double step = ExtMath.half(sum);
            double leftSum  = step;
            double rightSum = step;
            int cpt = beg;

            while (true) {
                step -= W[cpt];

                if (step > 0)
                    ++cpt;
                else
                    break;
            }

            double last = step + W[cpt];
            double absStep = Math.abs(step);

            /*
                Par défaut, l'algorithme décale à droite.
                C'est à dire que l'élément qui arrête la boucle ci-dessus sera
                position dans le sous arbre droite.

                Sauf dans les deux cas précédent:
                    Si la valeur de step est plus petite que la précédente
                    Ou si step est égal à 0
                Alors on décale à gauche.
             */
            if (absStep < last || step == 0) {
                ++cpt;

                leftSum  += absStep;
                rightSum -= absStep;
            } else {
                leftSum  -= last;
                rightSum += last;
            }

            return new Node(constructTree(beg, cpt - 1, (int)leftSum), constructTree(cpt, end, (int)rightSum));
        }
    }
}
