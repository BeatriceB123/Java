class ToolsForValidation {
    static boolean isValidCommand ( String string ) {
        if ( isRegisterCommand (string) )
            return true;
        if ( isLoginCommand (string) )
            return true;
        if ( isSendCommand (string) )
            return true;
        if ( isReadCommand (string) )
            return true;
        if ( isFriendCommand (string) )
            return true;
        if ( isExitCommand (string) )
            return true;
        if ( isReadNewCommand (string) )
            return true;
        if ( isSeeNetworkStructure (string) )
            return true;
        if ( isUploadCommand (string) )
            return true;
        if(isStopCommand (string))
            return true;
        return (isHelpCommand (string));
    }

    private static boolean isStopCommand ( String string ) {
        String pattern = "stop";
        return string.matches (pattern);
    }

    private static boolean isUploadCommand ( String string ) {
        String pattern = "upload";
        return string.matches (pattern);
    }

    private static boolean isRegisterCommand ( String string ) {
        String pattern = "register\\s+[a-zA-Z]+";
        return string.matches (pattern);
    }

    private static boolean isLoginCommand ( String string ) {
        String pattern = "login\\s+[a-zA-Z]+";
        return string.matches (pattern);
    }

    private static boolean isSendCommand ( String string ) {
        String pattern = "send\\s+(.)+";
        return string.matches (pattern);
    }

    static boolean isSeeNetworkStructure ( String string ) {
        String pattern = "see";
        return string.matches (pattern);
    }

    private static boolean isReadCommand ( String string ) {
        String pattern = "read";
        return string.matches (pattern);
    }

    static boolean isReadNewCommand ( String string ) {
        String pattern = "read new";
        return string.matches (pattern);
    }

    private static boolean isFriendCommand ( String string ) {
        String pattern = "friend(\\s+[a-zA-Z]+)+";
        return string.matches (pattern);
    }

    static boolean isExitCommand ( String string ) {
        return string.equalsIgnoreCase ("exit");
    }

    static boolean isHelpCommand ( String string ) {
        return string.equalsIgnoreCase ("help");
    }
}
