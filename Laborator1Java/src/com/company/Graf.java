package com.company;

import java.util.Scanner;
import java.util.*;


//data viitoare, numele clasei va incepe cu majuscula
class Graf {

    //Cu asta putem citi de la tastatura
    //Scanner e o clasa
    private static Scanner userInput = new Scanner(System.in);

    private int n; //numarul de noduri din G
    private int m; //numarul de muchii din G
    private int gradMinim;
    private int gradMaxim;
    private int[] grade; //gradele fiecarui nod
    private int[][] matriceAdiacenta; //matricea de adiacenta a grafului

    private void parcurgere ( boolean[] marcat, int nod_u )
    {
        marcat[nod_u] = true;

        //stabilim care sunt vecinii nodului u nevizitati
        int[] vecini = new int[n];
        int nrVecini = 0;
        for(int i = 0; i < n; i++)
            if(!marcat[i] && matriceAdiacenta[nod_u][i] == 1)
            {
                vecini[nrVecini]=i;
                nrVecini++;
            }

        //si facem parcurgere a vecinilor nevizitati
        for(int i=0; i<nrVecini; i++)
        {
            parcurgere(marcat, vecini[i]);
        }
    }

    //o functie ce cauta un nod nevizitat in graf
    //daca returneaza -1 inseamna ca am parcurs toate componentele din graf
    private int alegeNodStart ( boolean[] marcat )
    {
        for(int i=0; i<n; i++)
            if(!marcat[i])
                return i;
        return -1;
    }

    private int conex ()
    {
        boolean[] marcat = new boolean[n];
        int nrComponenteConexe = 0;
        for(int i=0; i<n; i++)
            marcat[i] = false;

        int nod = alegeNodStart(marcat);
        while(nod != -1)
        {
            parcurgere(marcat, nod);
            nod = alegeNodStart(marcat);
            nrComponenteConexe++;
        }

        return nrComponenteConexe;
    }

    //constructor
    Graf()
    {
        //sa zicem ca aici incepe aplicatia
        long startTime = System.nanoTime();

        n = numarNoduri(); //validat, bun;
        int tipGraf = -1;  //se va alege de la tastatura
        do {
            System.out.print("Introduceti numarul grafului pe care il doriti: " +
                             "complet(0) circuit(1) sau aleatoriu(2) sau arbore(3)\n");
            while (!userInput.hasNextInt()) {
                System.out.printf("%s nu e bun. Introduceti numarul grafului pe care il doriti: "
                        + "complet(0) circuit(1) sau aleatoriu(2) sau arbore(3)\n", userInput.next());
            }
            tipGraf = userInput.nextInt(); //suntem siguri ca avem un int; dar e bun?
        } while(tipGraf != 0 && tipGraf != 1 && tipGraf != 2 && tipGraf != 3);

        if(tipGraf == 0)
            generareMuchiiComplet();
        else if(tipGraf == 1)
            generareMuchiiCircuit();
        else if(tipGraf == 2)
            generareMuchiiRandom();
        else generareMuchiiArbore();

        afisare();

        //si pe aici e gata
        long endTime   = System.nanoTime();
        double totalTime = endTime - startTime;

        System.out.printf("Timpul de rulare al programului %f secunde. \n", totalTime/1_000_000_000.0);

        if(n <= 30000)
        {
            int conn = conex();
            if (conn == 1)
                System.out.println("Graful este conex");
            else
                System.out.printf("Graful are %d componente conexe\n", conn);
        }
    }

    private int numarNoduri()
    {
        boolean validare = false;
        int numar;

        do {
            System.out.println("Introduceti un numar impar de noduri ");
            //dupa ce afiseaza mesajul asteapta sa se introduca ceva si sa se apesa enter.
            //dupa ce s-a introdus acel ceva se verifica conditia din while (daca e un numar intreg)
            while (!userInput.hasNextInt())
            {
                //"citim" acel ceva care nu e numar intreg in String, si afisam mesaj de eroare
                String numberEntered = userInput.next();
                System.out.printf("%s nu e bun\n", numberEntered);
            }

            //am obtinut un numar intreg, iar apoi verificam si celelalte conditii
            numar = userInput.nextInt();
            if(numar%2 == 1 && numar > 0 && numar < 999999)
            {
                validare = true;
            }
        }while(!validare);

        return numar;
    }

   private void afisareFrumoasaMatrice()
  {
     //doua reprezentari pentru 0 si 1 in matricea de adiacenta
      char noduletChar = '\u2665'; //negru
        char liberChar = '\u2661';   //alb

        //String noduletString = Character.toString(noduletChar);
        //System.out.print(matriceA);

        for(int i=0; i<n; i++)
        {
            for(int j=0; j<n; j++)
                if(matriceAdiacenta[i][j] == 1)
                    System.out.print(noduletChar + " ");
                else
                    System.out.print(liberChar + " ");
            System.out.print("\n");
        }
    }

    private void verificareRegulat()
    {
        if(gradMaxim == gradMinim)
            System.out.printf("Este graf %d-regulat\n", gradMinim);
        else
            System.out.println("Nu este graf regulat");
    }

    private void verificareSumaGrade()
    {
        int sumaGrade = 0;
        for(int i = 0; i < n; i++)
            sumaGrade+=grade[i];

        if(sumaGrade == 2*m)
            System.out.println("Suma gradelor este egala cu 2*m");
        else
            System.out.println("Suma gradelor nu este egala cu 2*m");
    }

    private void afisare()
    {
        System.out.println("Numarul de noduri este " + n);
        System.out.println("Numarul de muchii este " + m);

        char deltaMic = '\u03B4';
        char deltaMare = '\u0394';
        System.out.printf( deltaMare + "(G) = %d\n", gradMaxim);
        System.out.printf( deltaMic + "(G) = %d\n", gradMinim);

        verificareRegulat();

        verificareSumaGrade();

        if(n <= 30000)
            afisareFrumoasaMatrice();
    }

    //o functie ce genereaza eficient un arbore random
    //e mai mare probabilitatea ca acesta sa aiba inaltimea mare, decat sa o aiba mica
    //s-ar putea mari probabilitatea de alegere a ultimelor noduri puse pentru a se genera un graf mai condensat.
    private void generareMuchiiArbore()
    {
        initializareMatriceAdiacenta();

        //generam o lista de elemente de la 0 la n-1, neordonate, care
        //ne vor indica in ce ordine sa punem in graf nodurile
        ArrayList<Integer> permutare = new ArrayList<>(); //interfata, tip, clasa.
        for (int i = 0; i < n; i++)
            permutare.add(i);
        Collections.shuffle(permutare);
        System.out.println(permutare);

        //un vector cu nodurile adaugate in arbore
        int[] noduriPuse = new int[n];
        int nrNoduriPuse = 0;

        noduriPuse[0] = permutare.get(0);
        nrNoduriPuse++;

        //cele doua noduri intre care punem muchia
        int nod1, nod2;
        for(int i=1; i<n; i++) //adaugam in graf cele n-1 noduri ramase
        {
            int pozitie = (int)(Math.random()*15486533) % nrNoduriPuse;//un nod de la 0 la nrNoduriPuse-1,  ce ar putea fi vecin
            //sau : pozitie = (15486533) % nrNoduriPuse;//unde ala e un numar prim foarte mare

            nod1 = noduriPuse[pozitie];  //un element random dintre cele existente in graf;
            nod2 = permutare.get(i);     //nodul pe care il adaugam
            //System.out.println(nod1 + " " + nod2 + " Nodul 1 a fost pe " + pozitie);
            noduriPuse[nrNoduriPuse] = nod2;
            nrNoduriPuse++;

            adaugaMuchia(nod1, nod2);
        }
        calculareGrade();
    }

    private void generareMuchiiRandom()
    {
        initializareMatriceAdiacenta();
        double aux_m = (int)(Math.random() * n* (n-1) +1 ); //un numar aproximativ de muchii de la 0(graf nul) la n*(n-1) (graf complet)

        //acum asignam pentru cele n*(n-1) posibile muchiii muchie cu probabilitate de m/(n-1)/n
        for(int i=0; i<n; i++)
        {
            for (int j = i+1; j < n; j++)
            {
                double aux = Math.random();
                if(aux > aux_m/(n*(n-1))) //OBS!! daca aux_m e int, trebuie sa faci cast explicit!!
                    adaugaMuchia(i, j);
            }
        }

        //acum facem vectorul de grade
        calculareGrade();
    }

    private void generareMuchiiComplet()
    {
        initializareMatriceAdiacenta();

        for (int i = 0; i < n; i++)
        {
            for (int j = i + 1; j < n; j++)
            {
                //punem peste tot muchii
                adaugaMuchia(i, j);
            }
        }

        //acum facem vectorul de grade
        calculareGrade();
    }

    private void generareMuchiiCircuit()
    {
        initializareMatriceAdiacenta();

        //vom simula ordinea din circuit intr-un vector de permutare
        ArrayList<Integer> permutare = new ArrayList<>(); //interfata, tip, clasa.
        for (int i = 0; i < n; i++)
            permutare.add(i);
        Collections.shuffle(permutare);

        //acum adaugam in matricea de adiacenta muchiile corecpunzatoare
        for(int i=0; i<n-1; i++)
        {
            adaugaMuchia(permutare.get(i), permutare.get(i+1));
        }
        //si o muchie ce va uni primul si ultimul element din permutare
        adaugaMuchia(permutare.get(n-1), permutare.get(0));

        //acum facem vectorul de grade
        calculareGrade();
    }


    //se primeste o matrice valida
    private void calculareGrade()
    {
        grade = new int[n];
        gradMinim = n-1;
        gradMaxim = 0;

        for(int i = 0; i < n; i++)
        {
            grade[i] = 0;
            for (int j = 0; j < n; j++)
                grade[i]+=matriceAdiacenta[i][j];
            gradMinim = Math.min(grade[i], gradMinim);
            gradMaxim = Math.max(grade[i], gradMaxim);
        }
    }

    private void initializareMatriceAdiacenta()
    {
        matriceAdiacenta = new int[n][n];
        m=0;
        for(int i=0; i<n; i++)
        {
            for (int j = 0; j < n; j++)
                matriceAdiacenta[i][j] = 0;
        }
    }

    private void adaugaMuchia(int ii, int jj)
    {
        matriceAdiacenta[ii][jj] = 1;
        matriceAdiacenta[jj][ii] = 1;
        m++;
    }

}