package ui;

import java.util.Scanner;
import java.util.ArrayList;
@SuppressWarnings("unchecked")
public class Seguimiento_1 {

    public static Scanner scan = new Scanner(System.in);
    public static void main(String[] args) {
        Seguimiento_1 s = new Seguimiento_1();
    }

    public Seguimiento_1(){
        boolean check = true;
        int index = 0, backpack = 0;
        ArrayList<Cosa> cosas= new ArrayList<Cosa>(), pack;
        //int hold = 0;
        //String holdo = "";
        while (check){
            System.out.println("What would you like to do?\n\n1) Sum an array\n2)Reverse the order of a string\n3)Average an array\n4)Find the position of a number in an array\n5)Backpack filling simulator\nN)Exit");
            String input = scan.next();
            input = input.toUpperCase();
            switch (input){
                case("1"):
                    System.out.println("input the number of positions of the array");
                    index = scan.nextInt();
                    double[] nums = new double[index];
                    for (int i=0; i<index; i++){
                        System.out.println("input position " + (i+1) + " of the array");
                        nums[i] = scan.nextDouble();
                    }
                    System.out.println("the sum is: " + sum(nums,index-1));
                    break;
                case("2"):
                    String word = "";
                    System.out.println("input a word");
                    word = scan.next();
                    index = word.length();
                    System.out.println("the sum is: " + reverse(word,index-1));
                    break;
                case("3"):
                    System.out.println("input the number of positions of the array");
                    index = scan.nextInt();
                    double[] nums2 = new double[index];
                    for (int i=0; i<index; i++){
                        System.out.println("input position " + (i+1) + " of the array");
                        nums2[i] = scan.nextDouble();
                    }
                    System.out.println("the average is: " + average(nums2,index, index-1));
                    break;
                case("4"):
                    System.out.println("input the number of positions of the array");
                    index = scan.nextInt();
                    double[] nums3 = new double[index];
                    for (int i=0; i<index; i++){
                        System.out.println("input position " + (i+1) + " of the array");
                        nums3[i] = scan.nextDouble();
                    }
                    System.out.println("input the number you will compare");
                    double num = scan.nextDouble();
                    num = coincidence(nums3, num, index-1);
                    if (num<0){
                        System.out.println("no coincidences");
                    }
                    else{
                        System.out.println("the number is in position: " + (num+1));
                    }
                    break;     
                case("5"):/*
                    System.out.println("input the total capacity of the back pack");
                    backpack = scan.nextInt();
                    scan.nextLine();
                    for (int i=0; i<8; i++){
                        System.out.println("input the name of the item #" + (i+1));
                        holdo = scan.next();
                        System.out.println("input the weight of the item #" + (i+1));
                        hold = scan.nextInt();

                        cosas.add(new Cosa(hold, holdo));
                    }*/
                    backpack = 500;
                    cosas.add(new Cosa(250,"lonchera"));
                    cosas.add(new Cosa(200,"computador"));
                    cosas.add(new Cosa(100,"cuaderno"));
                    cosas.add(new Cosa(50,"manzana"));
                    cosas.add(new Cosa(25,"lapiz"));
                    cosas.add(new Cosa(25,"borrador"));
                    cosas.add(new Cosa(25,"sacapuntas"));
                    cosas.add(new Cosa(25,"resaltador"));
                    pack = backpackFillingSimulator(cosas, backpack, cosas.get(0), new ArrayList<Cosa>());
                    
                    if (pack!=null){
                        System.out.println("se ha llenado la maleta, los elementos que tiene son: \n");
                        for (int i=0; i<pack.size(); i++){
                            System.out.println(pack.get(i).getNombre() + ": " + pack.get(i).getPeso());
                        }
                    }
                    else {
                        System.out.println("No hay soluciones.");
                    }
                    break;
                case("N"):
                    check = false;
                    break;   
                default:
                    System.out.println("invalid input");  
            } 
        }
    }

    public double sum(double[] nums, int index){
        if (index==0){
            return nums[0];
        }
        else{
            return nums[index] + sum(nums,index-1);
        }
    }

    public String reverse(String word, int index){
        if (index==0){
            return word.charAt(0)+"";
        }
        else{
            return word.charAt(index) + reverse(word,index-1);
        }
    }

    public double average(double[] nums, int index, int help){
        if (help==0){
            return (nums[0])/index;
        }
        else{
            return (nums[help])/index + average(nums,index, help-1);
        }
    }

    public double coincidence(double[] nums, double num, int index){
        if (index==-1){
            return -1;
        }
        else if(nums[index]==num){
            return index;
        }
        else{
            return coincidence(nums, num, index-1);
        }

    }

    public ArrayList<Cosa> backpackFillingSimulator(ArrayList<Cosa> cosas, int capacidad, Cosa current, ArrayList<Cosa> pack){
        ArrayList<Cosa> hold = (ArrayList<Cosa>)cosas.clone();
        pack.add(current);
        int newCapacidad = 0;
        Cosa hold2;
        newCapacidad = capacidad - current.getPeso(); 
        if (cosas.size() == 0){
            pack.remove(current);
            return null;
        }
        else if (newCapacidad==0){
            return cosas;
        }
        else if (cosas.indexOf(current)==cosas.size()-1){
            pack.remove(current);
            return null;
        }
        else if (newCapacidad<0){
            if (cosas.size()>=2&&cosas.indexOf(current)<cosas.size()-1){
                hold2 = cosas.get(cosas.indexOf(current)+1);
            }
            else {
                hold2 = cosas.get(cosas.indexOf(current));
            }
            pack.remove(current);
            hold.remove(current);
            return backpackFillingSimulator(hold, capacidad, hold2, pack);
        }
        else {
            if (cosas.size()>=2&&cosas.indexOf(current)<cosas.size()-1){
                hold2 = cosas.get(cosas.indexOf(current)+1);
            }
            else {
                hold2 = cosas.get(cosas.indexOf(current));
            }
            hold = backpackFillingSimulator(hold, newCapacidad, hold2, pack);
            if (hold==null){
                hold = (ArrayList<Cosa>)cosas.clone();
                if (cosas.size()>=2){
                    hold2 = cosas.get(cosas.indexOf(current)+1);
                }
                else{
                    hold2 = null;
                }
                hold.remove(current);
                pack.remove(current);
                return backpackFillingSimulator(hold, capacidad, hold2, pack);
            }
            else {
                return pack;
            }
        }
        
    }
}
