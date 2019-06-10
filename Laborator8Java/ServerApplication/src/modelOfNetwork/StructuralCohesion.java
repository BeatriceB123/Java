package modelOfNetwork;

import javafx.util.Pair;

import java.util.ArrayList;
import java.util.List;

public class StructuralCohesion {

    private int numberOfNodes; //numarul de noduri = persoane din G
    private int minGradeOfNode;
    private List <Pair <Integer, Person>> nodes;
    private int[][] adjacentMatrix; //matricea de adiacenta a grafului

    public StructuralCohesion ( SocialNetwork socialNetwork ) {
        this.numberOfNodes = socialNetwork.getPersons ().size ();
        System.out.println (numberOfNodes);

        nodes = new ArrayList <> ();
        for (int i = 0; i < numberOfNodes; ++i)
            nodes.add (new Pair <> (i, socialNetwork.getPersons ().get (i)));

        adjacentMatrix = new int[numberOfNodes][numberOfNodes];
        for (int i = 0; i < numberOfNodes; i++) {
            for (int j = 0; j < numberOfNodes; j++)
                adjacentMatrix[i][j] = 0;
        }

        for (Pair <Integer, Person> person : nodes) {
            for (Person friend : person.getValue ().getFriends ()) {
                Integer friendPosition = getPositionOfPerson (friend);
                assert person.getKey () != null && friendPosition != null;
                adjacentMatrix[person.getKey ()][friendPosition] = 1;
            }
        }

        setMaxAndMinGradeOfNode ();

        printStructure ();

        System.out.println ("Number of conex components: " + numberOfConexComponents ());
    }

    private void printStructure () {
        printMatrix ();
        //printList ();
    }

    private void printMatrix () {
        char noduletChar = '\u2665'; //negru
        char liberChar = '\u2661';   //alb
        for (int i = 0; i < numberOfNodes; i++) {
            for (int j = 0; j < numberOfNodes; j++)
                if ( adjacentMatrix[i][j] == 1 )
                    System.out.print (noduletChar + " ");
                else
                    System.out.print (liberChar + " ");
            System.out.print ("\n");
        }
        //System.out.println ("Cei mai multi prieteni ai unei persoane sunt in numar de " + maxGradeOfNode);
        //System.out.println ("Cei mai putini prieteni ai unei persoane sunt in numar de " + minGradeOfNode);
        int aux = numberOfPersonsToDisconnect ();
        if ( aux > 1 )
            System.out.println ("Se poate deconecta stergandu-se " + aux + " persoane");
        else if ( aux == 1 )
            System.out.println ("Se poate deconecata prin stergerea unei singure persoane");
        else
            System.out.println ("Este deja deconectat");
    }

    private int numberOfPersonsToDisconnect () {//greedy
        int numberToDisconnect = minGradeOfNode; //stergem toti vecinii;
        if ( numberOfConexComponents () > 1 )
            numberToDisconnect = 0;
        return numberToDisconnect;
    }

    private void printList () {
        for (int i = 0; i < nodes.size (); i++)
            System.out.print(i + " " + nodes.get (i).getValue ());
    }

    private Integer getPositionOfPerson ( Person person ) {
        for (Pair <Integer, Person> personI : nodes) {
            if ( Person.theSamePerson (person, personI.getValue ()) )
                return personI.getKey ();
        }
        return null;
    }

    private int numberOfConexComponents () {
        boolean[] marcat = new boolean[numberOfNodes]; //lista cu toate nodurile din structura noastra
        int nrComponenteConexe = 0;
        for (int i = 0; i < numberOfNodes; i++)
            marcat[i] = false;

        int nod = alegeNodStart (marcat); //alegem un nod nevizitat de la care sa pornim
        while (nod != -1) //cat timp mai e de vizitat
        {
            parcurgere (marcat, nod);
            nod = alegeNodStart (marcat);
            nrComponenteConexe++;
        }

        return nrComponenteConexe;
    }

    //o functie ce cauta un nod nevizitat in graf
    //daca returneaza -1 inseamna ca am parcurs toate componentele din graf
    private int alegeNodStart ( boolean[] marcat ) {
        for (int i = 0; i < numberOfNodes; i++)
            if ( !marcat[i] )
                return i;
        return -1;
    }

    private void parcurgere ( boolean[] marcat, int nod_u ) {
        marcat[nod_u] = true;

        //stabilim care sunt vecinii nodului u nevizitati
        int[] vecini = new int[numberOfNodes];
        int nrVecini = 0;
        for (int i = 0; i < numberOfNodes; i++)
            if ( !marcat[i] && adjacentMatrix[nod_u][i] == 1 ) {
                vecini[nrVecini] = i;
                nrVecini++;
            }

        //si facem parcurgere a vecinilor nevizitati
        for (int i = 0; i < nrVecini; i++) {
            parcurgere (marcat, vecini[i]);
        }
    }

    //se primeste o matrice valida
    private void setMaxAndMinGradeOfNode () {
        int[] grade = new int[numberOfNodes];
        int maxGradeOfNode = 0;
        minGradeOfNode = numberOfNodes - 1;

        for (int i = 0; i < numberOfNodes; i++) {
            grade[i] = 0;
            for (int j = 0; j < numberOfNodes; j++)
                grade[i] += adjacentMatrix[i][j];
            minGradeOfNode = Math.min (grade[i], minGradeOfNode);
            maxGradeOfNode = Math.max (grade[i], maxGradeOfNode);
        }
    }
}
