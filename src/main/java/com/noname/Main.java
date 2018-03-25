package com.noname;


import java.util.*;

public class Main {

    static List<Integer> arrAns = new ArrayList<>();
    int tmp = 0;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int n = scanner.nextInt();

        int[] arr = new int[9];
        for(int i = 0; i<n; i++){
            arr[i] = scanner.nextInt();
        }
        Map<Integer, String> flag = new HashMap<>();
        createNum(arr, flag, 1);

        Collections.sort(arrAns);
        for(Integer i : arrAns){
            System.out.println(i);
        }

    }

    static void createNum(int[] arr, Map<Integer, String> flag, int tmp){

        int localTmp = tmp;
        for(int i=0; arr[i]!=0; i++){
            if(flag.get(arr[i]) == null){
                if(tmp == 0){
                    tmp = arr[i];
                }else{
                    tmp = tmp*10+arr[i];
                }
                createNum(arr, flag, tmp);
                flag.put(arr[i], null);
            }
        }
        if(tmp == localTmp){
            arrAns.add(tmp);
        }
    }
}



