import java.util.Stack;

/**
 * Meetings
 */
public class Meetings {
    public static void main(String[] args) {
        int[][] time = new int[][]{{1,9},{2,5},{4,10},{15,20},{20,25},{30,45}};
        int t = 50;
        Stack<int[]>res = meeting(t,time);
        int s = res.size();
        for (int i = 0; i <s ; i++) {
            if(i != s-1){
                System.out.print("["+res.peek()[0]+","+res.pop()[1]+"],");
            }else{
                System.out.print("["+res.peek()[0]+","+res.pop()[1]+"]");
            }
        }
    }

    public static Stack<int[]> meeting(int max, int[][] timeSlots){
        Stack<int[]> times = new Stack<int[]>();
        for (int i = 0; i < timeSlots.length; i++) {
            if(i == 0){
                times.push(timeSlots[i]);
            }else{
                if(times.peek()[1]<timeSlots[i][1] && times.peek()[1]>=timeSlots[i][0]){
                    int[] temp =times.pop();
                    int[] merged = new int[]{temp[0],timeSlots[i][1]};
                    times.push(merged);
                }else if(times.peek()[1]<timeSlots[i][0] && times.peek()[1]<timeSlots[i][1]){
                    times.push(timeSlots[i]);
                }
            }
        }
        int s = times.size();
        Stack<int[]> Results = new Stack<int[]>();
        int[] temp = times.peek();
        for (int i = 0; i < s; i++) {
            if(i==0){
                if(times.peek()[1]<max){
                    
                    int[] adding  = new int[]{temp[1],max};
                    Results.push(adding);
                    times.pop();
                }
            }else if (i==s-1){
                int[] gap = new int[]{times.peek()[1],temp[0]};
                Results.push(gap);

                if(times.peek()[1]>0){
                    int[] temp2 = times.pop();
                    int[] adding  = new int[]{0,temp2[0]};
                    Results.push(adding);
                }  
            }else{
                int[] gap = new int[]{times.peek()[1],temp[0]};
                Results.push(gap);
                temp = times.pop();
            }
        }
        return Results;
    }
    
}