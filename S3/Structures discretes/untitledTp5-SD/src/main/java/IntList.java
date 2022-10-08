/** interface IntList
 */
import java.util.NoSuchElementException;
public interface IntList
{
    /** @return la tête (le premier élément) de la liste si elle est non-vide
     */
    int getHead();
    /** @return la queue de la liste si elle est non-vide
     */
    IntList getTail();
    /** @return vrai si la liste est vide et faux sinon
     */
    boolean isEmpty();
    /** @param n l’entier à ajouter dans la liste
     * @return la liste à laquelle n est ajoutée
     */
    IntList cons(int n);
    /** @return la longueur de la liste
     */
    int length();
}

