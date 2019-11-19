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
    public String name;
    double costpaid = 0;
    double moneytoget;
    Scanner scanner = new Scanner(System.in);
    ArrayList<String> mainString = new ArrayList<>();
//-------------------------------------------------------------------------------------------------------------------------- 
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
            finalRes += "\n\n    " + this.name + " owes " + p.name + " Rs. " + p.getMoneyToGet();
            moneytoget += p.getMoneyToGet();
            p.makezero();
        }

        else{
            mainString.add(this.name + " owes " + p.name + " Rs. " + Math.abs(moneytoget));
            p.decreaseamount( Math.abs(moneytoget) );
            makezero();
        }
    }
}

class project
{
    public static void main(String args[])
    {
        Person arrofpeople[] = new Person[4];
        arrofpeople[0].name = "Rahul";
        arrofpeople[1].name = "Rishab";
        arrofpeople[2].name = "Pranav";
        arrofpeople[3].name = "Pandey";
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
                        System.out.println( (i+1)+ ". " +arrofpeople[i].getName()); 
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
                double mainpool = spent[0] + spent[1] + spent[2] + spent[3];
                for(int i=0;i<4;i++)
                {
                    arrofpeople[i].calcMoneyToGet( mainpool/4 );
                }  
                Collections.sort(arrofpeople);

                while(arrofpeople[numberofpeople - 1].getMoneyToGet() != 0  &&  
                        arrofpeople[0].getMoneyToGet() != 0 )
                {
                    finalRes += arrofpeople[0].putinstring( arrofpeople[numberofpeople-1]);
                    Collections.sort(arrofpeople);
                } 

                arrofpeople[0].makezero();
                arrofpeople[numberofpeople-1].makezero(); 
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