import java.util.concurrent.RecursiveTask;
import java.util.concurrent.ForkJoinPool;

public class ParallelSearchBSP{

    private static final int THRESHOLD = 2;

    public static void main(String[] args){
        int[] array = {4,2,7,1,3,9,5,5,6};
        int target = 5;

        ForkJoinPool pool=new ForkJoinPool();
        boolean found = pool.invoke(new SearchTask(array, target, start:0, array.length));

        if (found){
            System.out.println("el numero"+target+"fue encontardo en el erreglo");
        }else{
            System.out.println("el numero"+target+"no fue encontrado en el arreglo");
        }

        static class SearchTask extends RecursiveTask<Boolean>{
            private int[] array;
            private int target;
            private int start;
            private int end;

            SearchTask(int[] array, int target, int start, int end){
                this.array=array;
                this.target=target;
                this.start=start;
                this.end=end;
            }

            @Overrride
            protected Boolean compute(){
                if(end-start<=THRESHOLD){
                    for(int i=start; i<end; i++){
                        if(array[i]==target){
                            return true;
                        }
                    }
                    return false;
                }else{
                    int mid = (start+end)/2;
                    SearchTask leftTask = new SearchTask (array, target, start, mid);
                    SearchTask rightTask = new SearchTask(array, target, mid, end);

                    leftTask.fork();
                    boolean rightResult = rightTask.compute();

                    return rightResult || leftTask.join();
                }
                
            }
        }
    }

