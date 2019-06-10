package com.company;

public class Main {

    private static int sumaCifreComplet ( int nr )
    {
        int sum = 0;
        if(nr <= 9 && nr >= 0)
            return nr;
        else if(nr < 0)
        {
            System.out.println("Eroare parametru suma");
            return -1;
        }
        while(nr > 9)
        {
            sum = 0;
            while(nr > 0)
            {
                sum+= nr%10;
                nr/= 10;
            }
            nr = sum;
        }
        return sum;
    }

    public static void operatii()
    {
        String[] languages = {"C", "C++", "C#", "Go", "JavaScript", "Perl",
                "PHP", "Python", "Swift", "Java"};


        //n random
        int n = (int) (Math.random() * 1_000_000);

        //calcule
        n*=3;

        int dinBinar = Integer.parseInt("10101", 2);
        n+=dinBinar;

        int dinHexa = Integer.parseInt("FF", 16);
        n+=dinHexa;

        n*=6;

        int rezultat = sumaCifreComplet(n);
        System.out.printf("n = %d\n", n);
        System.out.println("Willy-nilly, this semester I will learn " + languages[rezultat]);
    }

    public static void inversareString()
    {
        String s = new String("abcdefghijk4567");
        System.out.println("cuvantul : " + s);
        int lungime = s.length();
        String t = new String("");

        for(int i=lungime-1; i>=0; i--)
        {
            t = t.concat(Character.toString(s.charAt(i)));
        }
        System.out.println(t);
    }

    public static void main(String[] elefant) {

        System.out.println();
        Graf G = new Graf();
    }
}