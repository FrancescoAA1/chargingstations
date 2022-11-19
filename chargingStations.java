import java.util.ArrayList;

public class chargingStations{
    public static void main(String[] args){

        //array representing the distance from the beginning of the highway of each charging station
        int[] stations = {60, 70, 120, 140};

        //array indicating the revenues (in million dollars) of each charging station
        //Note that there is a correspondence between indexes of stations and weights 
        //such that stations[0] refers to weights[0] and so on.
        int[] weights = {50, 60, 50 ,10};

        //opt will contain the maximal revenue requested and allow backtracing to identify 
        //the charging station obtaining a license 
        //IMPORTANT NOTE: opt has a cell more than all the other arrays
        //since opt[0] = 0
        //thus, opt[1] refers to the case stations[0], p[0], weights[0]
        int[] opt = new int[stations.length +1];

        //p(index) will contain the max index such that stations(i-1) + max <= stations(i)
        int[] p = new int[stations.length];
        int max = 50;

        p = pIndex(p, stations, max);

        for(int i=0; i<p.length; i++){
            System.out.println(p[i]);
        }

        opt = maxRevenue(opt, weights, p);

        for(int i=0; i<opt.length; i++){
            System.out.println(opt[i]);
        }

        System.out.println("Max Revenue is " + opt[opt.length-1]);

        System.out.println("List Maximal Revenue Stations: " + maxStations(opt, stations, p));



    }

    public static ArrayList<Integer> maxStations(int[] opt, int[] stations, int[] p){
            ArrayList<Integer> st = new ArrayList<Integer>();
            for(int i=opt.length-1; i>0; i--){
                if(opt[i] != opt[i-1]){
                    st.add(stations[i-1]);
                    i = p[i-1] +1;
                }
            }

            return st;
    }

    public static int[] maxRevenue(int[] opt, int[] weights, int[] p){
        opt[0] = 0;

        for(int i=1; i< opt.length; i++){
            opt[i] = Math.max(weights[i-1] + opt[p[i-1]], opt[i-1]);
        }

        return opt;
    }

    //foreach index i in range 1-n, the method finds max index such that stations(i-1) + max <= stations(i)
    //and saves this result in p[i]. p[0] = 0 by default
    public static int[] pIndex(int[] p, int[] st, int max){
        p[0] = 0;
        for(int i=1; i<st.length; i++){
            for(int j= i; j>0; j--){
                if(st[j-1] + max <= st[i]){
                    p[i] = j;
                    break;
                }
            }
        }
        return p;
    }
}