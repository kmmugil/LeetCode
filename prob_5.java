public class prob_5 {
    public static void main(String[] args) {
        String s = "tattarrattat";
        System.out.println(Solution_5.longestPalindrome(s));
    }
}

class Solution_5 {
    public static String longestPalindrome(String s) {
        char[] charArray = s.toCharArray();
        int tempLength, globalLength, tempIndex, globalIndex, tempMid=0;
        tempLength = 1; globalLength = 1; tempIndex = 0; globalIndex = 0;
        boolean initCheck;

        for(int i=0; i < charArray.length; i++) {
            initCheck = true;
            if(tempLength > 1) {
                if(tempIndex-1 >= 0 && i+1 < charArray.length && charArray[tempIndex-1] == charArray[i+1]) {
                    tempLength += 2;
                    tempIndex -= 1;
                    initCheck = false;
                } else {
                    if(tempLength > globalLength) {
                        globalLength = tempLength;
                        globalIndex = tempIndex;
                    }
                    tempLength = 1;
                    tempIndex = 0;
                    i = tempMid+1;
                }
            }
            if(initCheck) {
                tempIndex = i;
                while(true) {
                    if(i+1 < charArray.length && charArray[i+1] == charArray[i]) {
                        tempLength += 1;
                        i++;
                    } else {
                        if(i == charArray.length-1) {
                            if(tempLength > globalLength) {
                                globalLength = tempLength;
                                globalIndex = tempIndex;
                            }
                            tempLength = 1;
                            tempIndex = 0;
                        }
                        tempMid = i;
                        break;
                    }
                }
                if(tempLength < 2) {
                    if(i-1 >= 0 && i+1 < charArray.length && charArray[i-1] == charArray[i+1]) {
                        tempLength = 3;
                        tempIndex = i-1;
                    }
                } else {
                    i--;
                }
            }
            System.out.println("TEMP: "+s.substring(0, tempIndex)+"["+s.substring(tempIndex, tempIndex+tempLength)+"]"+s.substring(tempIndex+tempLength, charArray.length));
            System.out.println("GLOBAL: "+s.substring(0, globalIndex)+"["+s.substring(globalIndex, globalIndex+globalLength)+"]"+s.substring(globalIndex+globalLength,
                    charArray.length));
        }
        return s.substring(globalIndex, globalIndex+globalLength);
    }
}
