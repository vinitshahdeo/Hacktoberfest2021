class Solution {
    public boolean isPalindrome(int x) {
        if(x<0)
            return false;
        String str= Integer.toString(x);
        int l=str.length();
        int i=0;
        while(i<l){
            if(str.charAt(i)!=str.charAt(l-1))
                return false;
            i++;
            l--;
        }
        return true;
        
    }
}
