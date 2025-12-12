class Solution {
    public int[] countMentions(int numberOfUsers, List<List<String>> events) {
           events.sort((a, b) -> {
        int t1 = Integer.parseInt(a.get(1));
        int t2 = Integer.parseInt(b.get(1));
        if (t1 != t2) {
            return Integer.compare(t1, t2);
        }
        if (a.get(0).equals("OFFLINE") && b.get(0).equals("MESSAGE")) return -1;
        if (a.get(0).equals("MESSAGE") && b.get(0).equals("OFFLINE")) return 1;
        return 0;
    });
        int ans[] = new int[numberOfUsers];
        Map<Integer, Integer> map = new HashMap<>();
        for(int i=0; i<events.size(); i++){
            List<String> list = events.get(i);
            if(list.get(0).equals("MESSAGE")){
                String s = list.get(2);
                if(s.indexOf("id") != -1){
                    String parts[] = s.split(" ");
                    for(String part: parts){
                        int n = Integer.valueOf(part.substring(2));
                        ans[n]++;
                    }
                    
                } else if(s.equals("ALL")){
                    for(int j=0; j<ans.length; j++){
                        ans[j]++;
                    }
                } else {
                    for(int k=0; k<ans.length; k++){
                        if(!map.containsKey(k)){
                            ans[k]++;
                            continue;
                        }
                        int time = map.get(k);
                        int currTime = Integer.valueOf(list.get(1));
                        if(currTime - time >= 60){
                            ans[k]++;
                        }
                    }
                }
            } else {
                int time = Integer.valueOf(list.get(1));
                int id = Integer.valueOf(list.get(2));
                map.put(id, time);
            } 
        }
        return ans;
    }
}