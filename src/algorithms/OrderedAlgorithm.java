package algorithms;

import com.sun.org.apache.xerces.internal.impl.msg.XMLMessageFormatter_ja;
import data.*;
import helpers.ExtMath;

import java.util.ArrayList;

public class OrderedAlgorithm extends AbstractAlgorithms {

    /**
     * The Weight list.
     */
    private Integer[] W;

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
        addToCounter(W.length, 0, W.length);
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
            addToCounter(5, 3, 1);
            return new Leaf(W[beg]);
        } else if (beg + 1 == end) {
            addToCounter(10, 7, 1);
            return new Node(new Leaf(W[beg]), new Leaf(W[end]));
        } else {
            double step = ExtMath.half(sum); /* 1 opération */
            double leftSum  = step;
            double rightSum = step;
            int cpt = beg;
            addToCounter(4, 1, 0);

            while (true) {
                step -= W[cpt];
                addToCounter(1, 1, 1);
                if (step > 0) {
                    ++cpt;
                    addToCounter(0, 0, 1);
                }
                else break;
            }

            double last = step + W[cpt];
            double absStep = Math.abs(step);
            addToCounter(1, 1, 0);

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

                if (Math.abs(step) < last) comparisonCounter++;
                else comparisonCounter += 2;
                addToCounter(1, 1, 0);
            } else {
                leftSum  -= last;
                rightSum += last;
            }
            addToCounter(2, 0, 4);

            return new Node(constructTree(beg, cpt - 1, (int)leftSum), constructTree(cpt, end, (int)rightSum));
        }
    }
}
