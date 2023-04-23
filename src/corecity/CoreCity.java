package corecity;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class CoreCity {
    static List<List<Integer>> listDirects;
    static final int MAX = 100001;
    static int[]d = new int[MAX];
    static int[]l = new int[MAX];
    static int[] check = new int[MAX];
    static boolean[] isAp = new boolean[MAX];
    static int step = 0;
    static int count = 0;

    public static void main(String[] args) throws IOException {
        long start = System.currentTimeMillis();
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedReader br = new BufferedReader(new FileReader("E:\\IT\\swtest\\src\\corecity\\corecity_input.txt"));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int max = Integer.parseInt(br.readLine());

        listDirects = new ArrayList<>(MAX);
        for (int i = 0; i <MAX ; i++) {
            listDirects.add(new ArrayList<Integer>());

        }
        for (int t =1; t <=max ; t++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int vertices = Integer.parseInt(st.nextToken());
            int edges = Integer.parseInt(st.nextToken());

            for (int i = 0; i <edges ; i++) {
                st = new StringTokenizer(br.readLine());
                int l = Integer.parseInt(st.nextToken())-1;
                int r = Integer.parseInt(st.nextToken())-1;
                listDirects.get(l).add(r);
                listDirects.get(r).add(l);

            }

            for (int i = 0; i < vertices; i++) {
                if(d[i]==0){
                    dfs(-1,1);
                }
            }
            bw.write("#"+t+ " "+ count);
            for (int i = 0; i < vertices; i++) {
                if(isAp[i]){
                    bw.write(" "+ (i+1));
                }
            }
            bw.write("\n");
            initFunction(vertices);

        }
        bw.flush();
        System.out.println(System.currentTimeMillis()-start);

    }

    private static void initFunction(int vertices) {
        step = 0;
        count = 0;
        for(int i=0; i<=vertices; i++){
            d[i] =0;
            l[i]=0;
            check[i]= 0;
            isAp[i]=false;
            listDirects.set(i, new ArrayList<>());
        }
    }

    private static void dfs(int parrent, int root) {
        d[root] = l[root] = ++step;
        int child = 0;
        for (Integer direct:listDirects.get(root)){
            if(direct==parrent){
                continue;
            }
            if(d[direct]==0){
                child++;
                dfs(root, direct);
                l[root]= Math.min(l[root], l[direct]);
                if(parrent!=1 && l[direct]>=d[root]){
                    isAp[root] = true;
                    if(check[root] ==0){
                        count++;
                        check[root] = 1;
                    }
                }
            }else {
                if(direct != parrent){
                    l[root]= Math.min(l[root], d[direct]);

                }
            }

        }

        //
        if(parrent ==-1 && child>1){
            isAp[root]=true;
            if(check[root]==0){
                count++;
                check[root]=-1;
            }
        }



    }

}
