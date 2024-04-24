package utils;

public class Utilities {
    public static boolean validRange(int numberToCheck, int min, int max) {
        return ((numberToCheck >= min) && (numberToCheck <= max));
    }
    public static String truncateString(String stringToTruncate, int length){
        if (stringToTruncate.length() <= length) {
            return stringToTruncate;
        }
        else{
            return stringToTruncate.substring(0, length);
        }
    }
    public static boolean validateStringLength(String strToCheck, int maxLength){
        return strToCheck.length() <= maxLength;
    }
    public static boolean YNtoBoolean(String charToConvert){
        return "y".equalsIgnoreCase(charToConvert) || "Y".equalsIgnoreCase(charToConvert);
    }

    /**
     * This method returns Y if the booleanToConvert value is true. Returns N otherwise.
     *
     * @param booleanToConvert The boolean value that will be used to determine Y/N
     * @return Returns Y if the booleanToConvert value is true. Returns N otherwise.
     */
    public static String booleanToYN(boolean booleanToConvert){
        return booleanToConvert ? "is a verified artist" : " is not a verified artist" ;
    }
}
