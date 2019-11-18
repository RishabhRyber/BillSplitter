import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

class Expences
{
    String expence;
    String paidby;
    double expenceAmount;
    Expences(String paidby, double amount, String expenceName)
    {
        expence = expenceName;
        this.paidby = paidby;
        expenceAmount = amount;
    }
    void showExpences()
    {
        System.out.println(paidby + " paid Rs" + expenceAmount + " for " + expence);
    }
}

class Person implements Comparable<Person>{
    String name;
    double costpaid = 0;
    double moneytoget;
    Scanner scanner = new Scanner(System.in);
    ArrayList<String> mainString = new ArrayList<>();
//-------------------------------------------------------------------------------------------------------------------------- 
    public void getnamedata()
    {
        System.out.println("Enter the name");   
        name = scanner.nextLine();
    }

    void addcost(double money)
    {
        costpaid += money;
    }
    void calcMoneyToGet(double pool)
    {
        moneytoget = costpaid - pool ;
    }
    double getCostPaid(){
        return costpaid;
    }
    double getMoneyToGet(){
        return moneytoget;
    }
    void showdata()
    {
        System.out.println("Name : " + name + "\nCost paid : " + costpaid + "\nHe will get " + moneytoget + " Rupees\n");   
    }
    String getName()
    {
        return name;
    }

    void tt(double k)
    {
        System.out.printf("%n$%.2f", k);
    }
//-----------------------------------------------------------------------------------------------------------------------------

    public int compareTo(Person m)
    {
        if (this.moneytoget - m.moneytoget > 0)
            return 1;
        else if ( this.moneytoget - m.moneytoget < 0 )
            return -1;   
        else
            return 0;     
    }

    void makezero()
    {
        moneytoget = 0;
    }

    void decreaseamount(double x)
    {
        moneytoget = moneytoget - x;
    }

    void putinstring( Person p )
    {
        if( Math.abs(moneytoget) >= p.getMoneyToGet() ){
            mainString.add(this.name + " owes " + p.name + " Rs. " + p.getMoneyToGet());
            moneytoget += p.getMoneyToGet();
            p.makezero();
        }

        else{
            mainString.add(this.name + " owes " + p.name + " Rs. " + Math.abs(moneytoget));
            p.decreaseamount( Math.abs(moneytoget) );
            makezero();
        }
    }

    void showOwning()
    {
        for(int i = 0; i< mainString.size(); i++)
        {
            System.out.println(mainString.get(i));
        }
    }
}

class project
{
    public static void main(String args[])
    {
        Scanner scan = new Scanner(System.in);
        double mainpool = 0;
        int numberofpeople,choice = 0,flag = 0;
        System.out.println("Enter the number of people");
        numberofpeople = scan.nextInt();
        System.out.println("Number of people are " + numberofpeople + "\n");

        ArrayList<Person> arrofpeople = new ArrayList<>(numberofpeople);
        ArrayList<Expences> expencesList = new ArrayList<>();
        //Gathering data about the person
        System.out.println("Enter the names");
        for(int i=0;i<numberofpeople;i++)
        {
            System.out.println("Person " + (i+1));
            arrofpeople.add(new Person());
            arrofpeople.get(i).getnamedata();
            // mainpool += arrofpeople.get(i).getCostPaid();
        }
        //**************************************************************************************
        //Now the menu driven program
        while(choice != 4)
        {
            System.out.println("\nEnter the choice\n1.Add new expence\n2.Show owning\n3.Show Expences\n4.Exit");
            choice = scan.nextInt();

            switch(choice)
            {
                case 1:          
                // for(int i=0;i<numberofpeople;i++)
                {
                    int temp;
                    double tempmoney;
                    String nameofExp;
                    flag = 1;
                    System.out.println("Enter expence name");
                    scan.nextLine();
                    nameofExp = scan.nextLine();
                    System.out.println("Who paid?");
                    for(int i=0;i<numberofpeople;i++)
                    {
                        System.out.println( (i+1)+ ". " +arrofpeople.get(i).getName()); 
                    }
                    temp = scan.nextInt();
                    temp = temp -1 ;
                    System.out.println("Enter the money paid\n");
                    tempmoney = scan.nextInt();
                    arrofpeople.get(temp).addcost(tempmoney);
                    expencesList.add( new Expences( arrofpeople.get(temp).getName(), tempmoney, nameofExp ) );
                    // arrofpeople.get(i).getcostdata();
                    mainpool += tempmoney;
                }
                break;
                
                case 2: 
                if(flag == 0)
                {
                    System.out.println("Enter some cost first\n");
                    break;
                }
                System.out.println("The mainpool is " + mainpool + ". Equal share is " + (mainpool/numberofpeople));
                for(int i=0;i<numberofpeople;i++)
                {
                    arrofpeople.get(i).calcMoneyToGet( mainpool/numberofpeople );
                }  
                Collections.sort(arrofpeople);

                while(arrofpeople.get(numberofpeople - 1).getMoneyToGet() != 0  &&  
                        arrofpeople.get(0).getMoneyToGet() != 0 )
                {

                    // System.out.println("Now the sorted data entered is ");
                    // for(int i=0;i<numberofpeople;i++)
                    // {
                    //     System.out.println(arrofpeople.get(i).getName() + " ");
                    // }
                    // for(int i=0;i<numberofpeople;i++)
                    // {
                    //     System.out.println(arrofpeople.get(i).getMoneyToGet() + " ");
                    // }
                    // System.out.println("Press enter");
                    // scan.nextInt();
                    arrofpeople.get(0).putinstring( arrofpeople.get(numberofpeople-1));
                    Collections.sort(arrofpeople);
                } 

                arrofpeople.get(0).makezero();
                arrofpeople.get(numberofpeople-1).makezero();

                // for(int i=0;i<numberofpeople;i++)
                // {
                //     System.out.println(arrofpeople.get(i).getName() + " ");
                // }
                // for(int i=0;i<numberofpeople;i++)
                // {
                //     System.out.println(arrofpeople.get(i).getMoneyToGet() + " ");
                // }

                for(int i=0;i<numberofpeople;i++)
                {
                    arrofpeople.get(i).showOwning();
                }   
                break;

                case 3:
                for(int i=0;i<expencesList.size();i++)
                {
                    expencesList.get(i).showExpences();
                }
                break;

                case 4:
                System.exit(0); 
            }
        }   
        scan.close();
    }
}